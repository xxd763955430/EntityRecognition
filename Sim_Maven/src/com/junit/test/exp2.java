package com.junit.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.java.file.FileHelper;
import com.java.nlpir.Segmentation;
import com.xxd.similarity.HowNetSimilarty;
import com.xxd.similarity.Similarity;

/**
 * @author Administrator
 *
 */
public class exp2 {
	//测试文本综合相似度
	public void testInteSim2()
	{
		String rateFileName="F://实验/实验2/评论例子分词无标点.txt";
		String TestSimilaritiesPath="F://实验/实验2/testSimilarities/testSimilarities";
		try {
			Similarity.calRateIntegrateSim(rateFileName, TestSimilaritiesPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//创建文件
		public void test3() throws IOException
		{
			for(int i=1;i<=49;i++)
			{
				FileHelper.createNewFile("F://实验/实验2/testSimilarities/testSimilarities"+i+".txt");
			}
			
		}
		
	//生成n个testWord
	public void testGenerateWordFile() throws IOException {
			int count = 1;
			List<String> combinRate = HowNetSimilarty.getRateCombination("F://实验/实验2/评论例子分词无标点.txt");
			for (String str : combinRate) {
				String[] array = str.split("###");
				HowNetSimilarty.generateWordFile(array[0], array[1], "F://实验/实验2/testWord/testWord "+ count + ".txt");
				System.out.println(count);
				count++;
			}
		}	
	@Test
		public void testJaccard() throws IOException
		{
			String rateFileName="F://实验/实验1/评论例子分词无标点.txt";
			int size=FileHelper.readList(rateFileName).size()-1;//配对评论上限	
			List<String> combinRate = HowNetSimilarty.getRateCombination(rateFileName);
			List<Double> JarList=new ArrayList<>();
			for (int i = 1; i <=size; i++) {
				
				String[] array = combinRate.get(i - 1).split("###");//分隔两个句子
				double JarccardSimilarities = Similarity.getJarccad(array[0], array[1]);
				JarList.add(JarccardSimilarities);			
			}
			System.out.println("Jarccard");
			for(double sim:JarList)
				System.out.println(sim);


		}
}
