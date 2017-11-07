package com.xxd.similarity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.java.nlpir.Fenci2Sentence;
import com.sim.hownet.WordSimilarity;

public class Similarity2 {
	private static String[] highTfAdjWord=new String[]{"好",	"不错","大","新鲜","快","好吃","小","多","满意","赞","红","值得","棒","便宜","好评","方便","实惠","鲜"};
	@Test
	public void test1() throws IOException
	{
		String sentence1="用户在店铺停留时间";//停留时长
		String sentence2="用户在店铺驻留时间";//驻留市场
		List<String> newList1=Fenci2Sentence.fenci(sentence1);	
		List<String> newList2=Fenci2Sentence.fenci(sentence2);
		double howSim=getSenHowSim(newList1,newList2);
		System.out.println("字段相似度"+howSim);
	}
	public static double getSenLenSim(List<String> sentence1,List<String> sentence2)
	{
		int length1=sentence1.size();
		int length2=sentence2.size();
		double maxLength,sim;
		maxLength=length1>length2?length1:length2;
		sim=1-Math.abs(length1-length2)/maxLength;
		return sim;	
	}
	public static double getSenHowSim(List<String> sentence1,List<String> sentence2)
	{
		double sigmaMaxWordSim=0.0;
		for(String word1:sentence1)
		{
			double maxWordSim=0.0;
			for(String word2:sentence2)
			{			
				double wordSim=WordSimilarity.simWord(word1, word2);
				//System.out.println(word1+"  "+word2+wordSim);
				if(wordSim>maxWordSim)maxWordSim=wordSim;
			}
			//System.out.println("Max"+maxWordSim);
			sigmaMaxWordSim+=maxWordSim;
		}

		int length1=sentence1.size();
		int length2=sentence2.size();
		double maxLength=length1>length2?length1:length2;
		double senHowSim=sigmaMaxWordSim/maxLength;	
		//System.out.println(sigmaMaxWordSim);
		return senHowSim;
	}
	//计算y=a的x次方,公共词相似度
	public static double getComWordSim(List<String> sentence1,List<String> sentence2)
	{
		int x=getHighTFWordCount(sentence1, sentence2);
		double comWordSim=0;
		double y=Math.pow(4, x);
		comWordSim=y/(x+y);
		return comWordSim;
	}
	/**
	 * 公共形容词个数
	 * @param sentence1
	 * @param sentence2
	 * @return
	 */
    public static int getHighTFWordCount(List<String> sentence1,List<String> sentence2)
	{
    	List<String> comWord=new ArrayList<>();	
		for(String str1:sentence1)
			for(String str2:sentence2)
			{
				if(str1.equals(str2))
				{
					comWord.add(str1);	
								
				}
			}
    	int count=0;	
		for(String word:comWord)
		{
			if(Arrays.asList(highTfAdjWord).contains(word))
				count++;		
		}
		return count;
	}
}
