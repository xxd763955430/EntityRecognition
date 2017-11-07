package com.xxd.similarity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import com.java.file.FileHelper;


/*好/a=2810
大/a=2011
不错/a=1578
新鲜/a=811
好吃/a=547
快/a=506
红/a=389
小/a=250
多/a=156
真/a=120
便宜/a=114
棒/a=104
实惠/an=103
鲜/a=101*/
//计算综合相似度
public class Similarity {
	private static String[] highTfAdjWord=new String[]{"好",	"不错","大","新鲜","快","好吃","小","多","满意","赞","红","值得","棒","便宜","好评","方便","实惠","鲜"};
	/**
	 * 
	* @Description:计算综合相似度
	* @date 2017-6-2 下午3:00:47
	* @param :长度相似度列表，知网相似度列表，公共词相似度列表
	* @return void
	 */
	public static List<Double> calIntegrateSim(List<Double> lenSimList, List<Double> howSimList,
			List<Double> comWordSimList) {
		List<Double> IntegrateSimList=new ArrayList<>();
		int maxSize=howSimList.size();//循环上限
		for (int i = 0; i < maxSize; i++) {		
			double IntegrateSim = 0.15* lenSimList.get(i)+0.35* howSimList.get(i) + + 0.5* comWordSimList.get(i);
			IntegrateSimList.add(IntegrateSim);
		}
		return IntegrateSimList;
	}
	//批量计算公共词相似度
	public static List<Double> getComWordSimList(List<Integer> comAdjWordCountList,List<Double> howSimList)
	{
		double comWordSim=0;
		int i=0;
		List<Double> comWordSimList=new ArrayList<>();
		for(int num:comAdjWordCountList)
		{
			switch (num) {
			case 0:
				comWordSim = howSimList.get(i)*0.9;
				break;
			default:
				comWordSim = getComWordSim(num);
				break;
			}
			i++;
			comWordSimList.add(comWordSim);
		}
		return comWordSimList;
	}
	//计算y=a的x次方,公共词相似度
	public static double getComWordSim(double x)
	{
		double comWordSim=0;
		double y=Math.pow(4, x);
		comWordSim=y/(x+y);
		return comWordSim;
	}
	//计算所有句子长度相似度
	public static List<Double> getAllLenSim(String uri) throws IOException
	{
		List<Double> lenSimList=new ArrayList<>();
		List<String> rateList=FileHelper.readList(uri);
		int size=rateList.size();
		for(int i=0;i<size-1;i++)
		{
			for (int j = i + 1; j < size; j++) {		
				lenSimList.add(getLenSim(rateList.get(i),rateList.get(j)));
			}
		}
		return lenSimList;
	}
	/**
	 * 
	* @Description:计算两个句子长度相似度
	* @date 2017-6-2 下午2:43:50
	* @param :
	* @return double
	 */
	public static double getLenSim(String sentence1,String sentence2)
	{
		int length1=sentence1.split("\\s{1,}").length;
		int length2=sentence2.split("\\s{1,}").length;
		double maxLength,sim;
		maxLength=length1>length2?length1:length2;
		sim=1-Math.abs(length1-length2)/maxLength;
		return sim;	
	}

	/**
	 * 
	* @Description:获取公共词中属于高频词的个数
	* @date 2017-6-2 下午2:37:27
	* @param :
	* @return int
	 */
    public static int getHighTFWordCount(List<String> comWord)
	{
    	int count=0;
    	System.out.println("公共词的个数"+comWord.size());
   /* 	for (int i = 0; i < comWord.size(); i++)//去除公共词中存在的重复词
        {
    		
            for (int j = comWord.size() - 1 ; j > i; j--)  
            {
                if (comWord.get(i) == comWord.get(j))
                {
                	comWord.remove(j);
                }
            }
        } */  	
		for(String str1:comWord)
		{
			if(Arrays.asList(highTfAdjWord).contains(str1))
				count++;		
		}
		return count;
	}
    /**
     * 
    * @Description:计算句子的公共词
    * @date 2017-6-2 下午2:27:28
    * @param :
    * @return List<String>
     */
	public static List<String>  getCommomWord(String sentence1,String sentence2)
	{
		List<String> comWord=new ArrayList<>();
		String array1[]=sentence1.split("\\s{1,}");
		String array2[]=sentence2.split("\\s{1,}");
		System.out.println(sentence1+"###"+sentence2);		
		for(String str1:array1)
			for(String str2:array2)
			{
				if(str1.equals(str2))
				{
					comWord.add(str1);	
					System.out.println("公共词为："+str1);					
				}
			}
		return comWord;
	}
	/**
	 * 计算jarccard
	* @Description:
	* @date 2017-6-19 下午8:42:30
	* @param :
	* @return double
	 */
	public static double  getJarccad(String sentence1,String sentence2)
	{
		List<String> comWord=new ArrayList<>();
		String array1[]=sentence1.split("\\s{1,}");
		String array2[]=sentence2.split("\\s{1,}");
		System.out.println(sentence1+"###"+sentence2);		
		for(String str1:array1)
			for(String str2:array2)
			{
				if(str1.equals(str2))
				{
					comWord.add(str1);	
					System.out.println("公共词为："+str1);					
				}
			}
		System.out.println(comWord.size()+"  "+array1.length+"  "+array2.length);
		return (double)comWord.size()/(array1.length+array2.length-comWord.size());
	}
	/**
	 * 
	* @Description:计算评论的综合相似度
	* @date 2017-6-15 下午5:15:07
	* @param :评论文本的路径，知网TestSimilarities的路径
	* @return void
	* @throws IOException 
	 */
	public static void calRateIntegrateSim(String rateFileName,String TestSimilaritiesPath) throws IOException
	{
			int size=FileHelper.readList(rateFileName).size()-1;//配对评论上限
			List<Double> lenSimList=getAllLenSim(rateFileName);//长度相似度列表
			List<Double> howSimList=new ArrayList<>();//知网相似度列表
			List<String> combinRate = HowNetSimilarty.getRateCombination(rateFileName);
			List<Integer> comAdjCountList=new ArrayList<>();//公共tf词相似度列表
			for (int i = 1; i <=size; i++) {
				List<Double> similarities = HowNetSimilarty.getWordHowNetSim(TestSimilaritiesPath+ i + ".txt");
				String[] array = combinRate.get(i - 1).split("###");//分隔两个句子
				Map<String, List<Double>> wordSimMap = HowNetSimilarty.getEachWordSim(array[0],
						array[1], similarities);//获取单词相似度map
				List<String> comWord=getCommomWord(array[0],array[1]);//获取公共的词
				int comAdjCount=getHighTFWordCount(comWord);//公共形容词个数
				comAdjCountList.add(comAdjCount);//构造公共形容词个数列表
				double sentenceSim= HowNetSimilarty.getSentenceHowSim(wordSimMap);
				howSimList.add(sentenceSim);//构造知网相似度列表
			}
			List<Double> comWordSimList=getComWordSimList(comAdjCountList,howSimList);//初始化公共词相似度列表
			List<Double> IntegrateSimList=calIntegrateSim(lenSimList,howSimList,comWordSimList);//计算综合相似度	
			System.out.println("知网相似度");
			for (int i = 0; i < size; i++) {					
				System.out.println(howSimList.get(i));
			}
			System.out.println("公共词相似度");
			for (int i = 0; i < size; i++) {					
				System.out.println(comWordSimList.get(i));
			}
			System.out.println("长度相似度");
			for (int i = 0; i < size; i++) {					
				System.out.println(lenSimList.get(i));
			}
			System.out.println("综合相似度");
			for (int i = 0; i < size; i++) {		
				System.out.println(IntegrateSimList.get(i));
			}
	}
}
