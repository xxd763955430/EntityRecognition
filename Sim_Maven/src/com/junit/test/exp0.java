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
public class exp0 {
	//综合相似度的测试
	@Test
	public void test1() throws IOException
	{
		String rateFileName="F://实验/实验0/评论例子分词无标点.txt";
		String TestSimilaritiesPath="F://实验/实验0/testSimilarities/testSimilarities";		
		Similarity.calRateIntegrateSim(rateFileName, TestSimilaritiesPath);
	}
}
