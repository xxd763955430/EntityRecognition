package com.junit.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.java.file.FileHelper;
import com.java.nlpir.Segmentation;
import com.xxd.similarity.Tool;
//分词测试
public class JunitTest1 {
	public void test11()
	{
		List<String> list=new ArrayList<>();
		list.add("z");
		list.add("x");
		list.add("c");
		list.add("eee");
		list.add("eee");
		list.add("eee");
		list.add("z");
		list.add("x");
		list.add("a");
		List<Map.Entry<String, Integer>> sortedList=Tool.getWordFrequency(list);
	}
	public void test2() throws IOException
	{
		Segmentation.fenci("F://评论例子.txt", "F://评论例子分词.txt");
		List<String> list=FileHelper.readList("F://评论例子分词.txt");
		List<String> newList=Tool.remove_Punc(list);
		FileHelper.createNewFile("F://评论例子分词无标点.txt");
		FileHelper.writeList(newList, "F://评论例子分词无标点.txt");
	}
	
	public void testSentenceLength()
	{
		String uri="F://fenci1.txt";
		try {
			List<String> list=FileHelper.readList(uri);
			List<Integer> lengthList=new ArrayList<>();		
			for(String str:list)
			{
				str=Tool.removePunc(str);		
				String[] wordArray= str.split("\\s{1,}");
				lengthList.add(wordArray.length);
				System.out.println(wordArray.length);
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	public void testfenci()
	{
		String[] sources=new String[]{"F://1.txt","F://2.txt","F://3.txt","F://4.txt","F://5.txt"};
		int i=1;
		for(String source:sources)
		{		
			try {
				Segmentation.fenci(source, "F://fenci"+i+".txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}		
	}

	public void test3()
	{
		String str="好 大  看 照片 吧  有 看 解冻 了 弄 来 尝尝 是 什么 味道  快递 员 不错  还 没 送 来 时 我 就 打电话 给 他们 说 要 晚 一点 才 回家  他们 就 准备 了 保温 箱 给 我  晚上 拿 了 东西 就 地 放 好 保温 箱  今天 下班 回家 正好 看到 保温 箱 放 在 他们 的 小 车子 上  赞 一个  ";
		System.out.println(str.split("\\s{1,}").length);
	}
}