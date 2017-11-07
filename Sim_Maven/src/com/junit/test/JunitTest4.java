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
import com.xxd.similarity.Tool;

/**
 * @author Administrator
 *
 */
public class JunitTest4 {
	
//重新分词
public void test1() throws IOException{
	for(int i=1;i<=5;i++)
	{
		String raw=String.format("F://分词/%d.txt", i);
		String finish=String.format("F://分词/分词无标点%d.txt", i);
		Segmentation.fenci(raw,finish);
	}
}

//去除分词后的其他符号
public void test2() throws IOException
{
	for(int i=1;i<=5;i++)
	{
	String finish=String.format("F://分词/分词无标点%d.txt", i);
	List<String> list=FileHelper.readList(finish);
	List<String> newList=Tool.remove_Punc(list);
	FileHelper.createNewFile(finish);
	FileHelper.writeList(newList, finish);
	}
}

//分词放在一起
public void test3() throws IOException{
	for(int i=1;i<=5;i++)
	{
	String finish=String.format("F://分词/分词无标点%d.txt", i);
	String last="F://分词/分词集合.txt";
	List<String> list=FileHelper.readList(finish);
	List<String> newList=Tool.remove_Punc(list);
	FileHelper.writeList(newList, last);
	}
}
@Test
//重要：统计词频
public void test4() throws IOException{
	List<String> commentList=FileHelper.readList("F://分词/分词集合.txt");
	List<String> wordList=Tool.getAllWord(commentList);	
	Tool.getWordFrequency(wordList);
}
//好 5651 不错3390 大3022 新鲜1764 快1149 好吃1074 小709 多644 满意581 赞480 红448 值得421 便宜378
public void test5()
{
	for(int i=1;i<=5;i++)
	{
		System.out.println(Similarity.getComWordSim(i));
	}
}
}
