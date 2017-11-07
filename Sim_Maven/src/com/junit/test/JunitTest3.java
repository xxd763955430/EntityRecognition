package com.junit.test;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.java.file.FileHelper;
import com.xxd.similarity.Similarity;

/**
 * @author Administrator
 *
 */
//综合相似度测试
public class JunitTest3 {

	public void test1() throws IOException
	{
		List<String> ratelist=FileHelper.readList("F://评论例子分词无标点.txt");
		for(int i=1;i<ratelist.size();i++)
		{
			List<String> comWord=Similarity.getCommomWord(ratelist.get(0),ratelist.get(i));
			System.out.println("公共词个数："+comWord.size());
			int count=Similarity.getHighTFWordCount(comWord);
			System.out.println("高频词个数："+count);
		}
		
	}

	public void test2()
	{
		String sentence1="这 无 头 大红 虾  个头 大  新鲜 美味  特价 优惠  比 购 活 虾 还 便宜  不 用 去 摘 头 清 内脏  要 赞 好";
		String sentence2="好 大  看 照片 吧  有 看 解冻 了 ";
		double sim=Similarity.getLenSim(sentence1, sentence2);
		System.out.println("两个句子的长度相似度"+sim);
	}
	@Test
	public void test3()
	{
		for(int i=1;i<10;i++)
		{
		double y=Similarity.getComWordSim(i);
		System.out.println("x="+i+",y="+y);
		}
	}

	public void test4()
	{
		try {
			System.out.println(Similarity.getAllLenSim("F://评论例子分词无标点.txt").size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
