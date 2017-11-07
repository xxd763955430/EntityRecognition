package com.junit.test;

import java.io.IOException;

import org.junit.Test;

import com.xxd.similarity.Similarity;

/**
 * @author Administrator
 *
 */
public class exp1 {
	//综合相似度的测试
		@Test
		public void test1() throws IOException
		{
			String rateFileName="F://实验/实验1/评论例子分词无标点.txt";
			String TestSimilaritiesPath="F://实验/实验1/testSimilarities/testSimilarities";		
			Similarity.calRateIntegrateSim(rateFileName, TestSimilaritiesPath);
		}
}
