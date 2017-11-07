package com.junit.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.java.file.FileHelper;
import com.xxd.similarity.HowNetSimilarty;
import com.xxd.similarity.Similarity;
//知网相似度测试
/**
 * @author Administrator
 *
 */
public class JunitTest2 {
	//综合相似度的测试
	@Test
	public void test1() throws IOException
	{
		String rateFileName="F://评论例子分词无标点.txt";
		String TestSimilaritiesPath="F://实验/实验0/testSimilarities/testSimilarities";		
		Similarity.calRateIntegrateSim(rateFileName, TestSimilaritiesPath);
	}
	public void test2()
	{
		try {
			HowNetSimilarty.getWordHowNetSim("F://TestSimilarities.txt");
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//创建文件
	public void test3() throws IOException
	{
		for(int i=1;i<=14;i++)
		{
			FileHelper.createNewFile("F://testSimilarities/testSimilarities"+i+".txt");
		}
		
	}

	// 生成n个testWord
	public void testGenerateWordFile() throws IOException {
		int count = 1;
		List<String> combinRate = HowNetSimilarty.getRateCombination("F://评论例子分词无标点.txt");
		for (String str : combinRate) {
			String[] array = str.split("###");
			HowNetSimilarty.generateWordFile(array[0], array[1], "F://testWord/testWord" + count + ".txt");
			System.out.println(count);
			count++;
		}
	}

	// 测试一对句子的相似度
	public void test4() throws IOException {
		List<Double> similarities = HowNetSimilarty.getWordHowNetSim("F://testSimilarities/TestSimilarities1.txt");
		Map<String, List<Double>> wordSimMap =HowNetSimilarty.getEachWordSim(
				"这 无 头 大红 虾  个头 大  新鲜 美味  特价 优惠  比 购 活 虾 还 便宜  不 用 去 摘 头 清 内脏  要 赞 好",
				"好 大  看 照片 吧  有 看 解冻 了 弄 来 尝尝 是 什么 味道  快递 员 不错  还 没 送 来 时 我 就 打电话 给 他们 说 要 晚 一点 才 回家  他们 就 准备 了 保温 箱 给 我  晚上 拿 了 东西 就 地 放 好 保温 箱  今天 下班 回家 正好 看到 保温 箱 放 在 他们 的 小 车子 上  赞 一个  ",
				similarities);
		double sentenceSim= HowNetSimilarty.getSentenceHowSim(wordSimMap);
	}

	//重要  批量测试句子的知网相似度
	public void getAllReteSim() throws IOException {
		List<String> combinRate = HowNetSimilarty.getRateCombination("F://评论例子分词无标点.txt");
		int length = combinRate.size();
		for (int i = 1; i <=14; i++) {
			List<Double> similarities = HowNetSimilarty.getWordHowNetSim("F://testSimilarities/TestSimilarities"+ i + ".txt");
			String[] array = combinRate.get(i - 1).split("###");
			System.out.println(array[0] + "###" + array[1]);
			Map<String, List<Double>> wordSimMap = HowNetSimilarty.getEachWordSim(array[0],
					array[1], similarities);
			double sentenceSim= HowNetSimilarty.getSentenceHowSim(wordSimMap);	
		}
		
		
	}
}
