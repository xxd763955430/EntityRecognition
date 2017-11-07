package com.xxd.similarity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.java.file.FileHelper;

/**
 * 知网相似度
 * @author Administrator
 * 
 */
public class HowNetSimilarty {

	public void test3() throws IOException {
		List<String> combinRate = getRateCombination("F://评论例子分词无标点.txt");
		int length = combinRate.size();
		for (int i = 1; i < length; i++) {
			List<Double> similarities = getWordHowNetSim("F://testSimilarities/TestSimilarities"
					+ i + ".txt");
			String[] array = combinRate.get(i - 1).split("###");
			System.out.println(array[0] + "###" + array[1]);
			Map<String, List<Double>> wordSimMap = getEachWordSim(array[0],
					array[1], similarities);
			
		}
	}
	/**
	 * 
	* @Description:获得评论例句的两两匹配
	* @date 2017-6-2 下午1:07:30
	* @param :
	* @return List<String>
	 */
	public static List<String> getRateCombination(String uri) throws IOException {
		List<String> combinRate = new ArrayList<String>();
		List<String> rateList = FileHelper.readList(uri);
		for (int i = 0; i < rateList.size() - 1; i++) {
			for (int j = i + 1; j < rateList.size(); j++) {		
				combinRate.add(rateList.get(i) + "###" + rateList.get(j));
			}
		}
		return combinRate;
	}
	/**
	 * 
	* @Description:每对句子的知网相似度
	* @date 2017-6-2 下午1:26:06
	* @param :每个词的相似度map
	* @return Map<String,Double>
	 */
	public static double getSentenceHowSim(Map<String, List<Double>> wordSimMap) {
		Double maxValue = 0.0,sumSim = 0.0;
		int minLength=0;
		Map<String, Double> wordMaxSimMap = new HashMap<String, Double>();
		for (Entry<String, List<Double>> entry : wordSimMap.entrySet()) {
			String word = (String) entry.getKey();
			List<Double> simList = (List<Double>) entry.getValue();
			Collections.sort(simList, Collections.reverseOrder());
			maxValue = simList.get(0);
			wordMaxSimMap.put(word, maxValue);
			minLength++;
		}
		for (Entry<String, Double> entry : wordMaxSimMap.entrySet()) {
			sumSim += entry.getValue();
		}
		System.out.println("所有词最大相似度之和："+sumSim);
		System.out.println("较短的句子长度："+minLength);
		System.out.println("句子相似度："+sumSim/minLength);
		return sumSim/minLength;
	}

	/**
	 * 
	* @Description:获取句子1与句子2对比的每个词语的两两相似度
	* @date 2017-6-2 下午12:57:24
	* @param :两个句子，两个句子所有词相似度列表
	* @return Map<String,List<Double>>
	 */
	public static Map<String, List<Double>> getEachWordSim(String sentence1,
			String sentence2, List<Double> wordSimilarities) {
		String[] temp=new String[]{};
		int length1, length2, count = 0;
		Map<String, List<Double>> wordSimMap = new HashMap<String, List<Double>>();
		String[] wordArray1 = sentence1.split("\\s{1,}");
		String[] wordArray2 = sentence2.split("\\s{1,}");
		if (wordArray1.length > wordArray2.length)// 交换字符串保证wordArray1最短
		{
			temp = wordArray1;
			wordArray1 = wordArray2;
			wordArray2 = temp;
		}
		length1 = wordArray1.length;
		length2 = wordArray2.length;
		for (int i = 0; i < length1; i++) {
			List<Double> wordSim = new ArrayList<>();
			for (int j = 0; j < length2; j++) {
				if (count > length1 * length2)
					break;
				wordSim.add(wordSimilarities.get(count));
				count++;
			}
			wordSimMap.put(i + wordArray1[i], wordSim);
		}
		//for(Entry entry:wordSimMap.entrySet())
		//System.out.println(entry.getKey()+""+entry.getValue());
		return wordSimMap;
	}

	/**
	 * 
	* @Description:获取所有词语的知网相似度
	* @date 2017-6-2 上午11:47:03
	* @param :文件路径
	* @return List<Double>
	 */
	public static List<Double> getWordHowNetSim(String uri) throws IOException {
		List<Double> wordSimilarities = new ArrayList<Double>();
		List<String> list = FileHelper.readList(uri);
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)
					.equals("------------------------------------------")) {
				String line = list.get(i + 1);
				Pattern p = Pattern.compile(":(.*)");
				Matcher m = p.matcher(line);
				if (m.find()) {					
					wordSimilarities.add(Double.parseDouble(m.group(1)));
					count++;
				} else {
					wordSimilarities.add(1.0);
					count++;
				}
			}

		}
		System.out.println("所有词的对数："+count);
		return wordSimilarities;
	}
	/**
	 * 
	 * @Description:生成两个句子的比较文件，短句在前长句在后，句子为去除标点后的句子
	 * @date 2017-6-2 上午11:27:07
	 * @param :两个句子，目标文件路径
	 * @return void
	 */
	public static void generateWordFile(String sentence1, String sentence2,
			String fileName) throws IOException {
		String[] wordArray1 = sentence1.split("\\s{1,}");
		String[] wordArray2 = sentence2.split("\\s{1,}");
		FileHelper.createNewFile(fileName);
		String[] tempArray = new String[] {};
		if (wordArray1.length > wordArray2.length) {
			tempArray = wordArray1;
			wordArray1 = wordArray2;
			wordArray2 = tempArray;
		}
		for (String word1 : wordArray1)
			for (String word2 : wordArray2)
				FileHelper.writeWord(fileName, word1, word2);
	}
}
