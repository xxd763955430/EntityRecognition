package com.xxd.similarity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java.file.FileHelper;
//文本处理工具类
public class Tool {
	/**
	 * 
	* @Description:把评论语句中的所有词加入到词列表中
	* @date 2017-6-8 下午4:38:23
	* @param :
	* @return List<String>
	 */
	public static List<String> getAllWord(List<String> commentList)
	{
		List<String> wordList=new ArrayList<>();
		for(String comment:commentList)
		{
			String[] wordArray=comment.split("\\s{1,}");
			for(String str:wordArray)
				wordList.add(str);
		}
		return wordList;
	}
	//词频统计并降序排列
	public static List<Map.Entry<String, Integer>> getWordFrequency(List<String> list)
	{
		Map<String,Integer> wordFrequency=new HashMap<>();
		for(String str:list)
		{
			Integer wordCount=wordFrequency.get(str);
			if(wordCount!=null)
				wordFrequency.put(str,wordCount+1);
			else
				wordFrequency.put(str,1);			
		}
		List<Map.Entry<String, Integer>> mHashMapEntryList=new ArrayList<Map.Entry<String,Integer>>(wordFrequency.entrySet());
		Collections.sort(mHashMapEntryList, new Comparator<Map.Entry<String,Integer>>() {

			@Override
			public int compare(Map.Entry<String,Integer> firstMapEntry, 
							   Map.Entry<String,Integer> secondMapEntry) {
				return secondMapEntry.getValue().compareTo(firstMapEntry.getValue());
			}
		});		
		System.out.println("词频顺序");
		for (int i = 0; i < mHashMapEntryList.size(); i++) {
			 System.out.println(mHashMapEntryList.get(i));
		}
		return mHashMapEntryList;
	}
	//带词性的分词去除标点符号
	public static String removePunc(String str)
	{
		str=str.replaceAll("，/wd","").replaceAll("。/wj", "").replaceAll("！/wt", "").replaceAll("“/wyz", "").replaceAll("”/wyz", "").replaceAll("‘/wyz", "").replaceAll("’/wyy", "").replaceAll("：/wp", "");
		str=str.replaceAll(",/wd","").replaceAll("./wj", "").replaceAll("!/wt", "").replaceAll("\"/wyz", "").replaceAll("'/wyz", "").replaceAll(":/wp", "");
		str=str.replaceAll("～/ws","").replaceAll("、/wn","").replaceAll("~/w", "");
		return str;
	}
	//不带词性的分词去除非中文和数字以外的字符
	public static List<String> remove_Punc(List<String> list)
	{
		List<String> newList=new ArrayList<>();
		for(String str:list)
		{
			str = str.replaceAll("[^\u4e00-\u9fa5\\s{1,}0-9]","");
			newList.add(str);
		}
		return newList;
	}	
	public static String remove_Punc(String sentence)
	{	
		sentence = sentence.replaceAll("[^\u4e00-\u9fa5\\s{1,}0-9]","");
		return sentence;
	}	
}
