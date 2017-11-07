package com.java.nlpir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.java.file.FileHelper;
import com.sun.jna.Native;
import com.xxd.similarity.Tool;

/**评论文本分词
 * @author Administrator
 * 
 */
public class Fenci2Sentence {
	/**
	 * 
	* @Description:添加用户自定义词
	* @date 2017-6-2 上午10:59:31
	* @param :CLibrary对象 
	* @return void
	 */
	public static void addUserWord(CLibrary instance,List<String> userDefWord) {	
		for (String word : userDefWord) {
			instance.NLPIR_AddUserWord(word);
		}
	}
	/**
	 * 
	* @Description:对评论文本进行切词
	* @date 2017-6-2 上午11:00:01
	* @param :未分词的文件路径，已分词的文件路径
	* @return void
	 */
	public static List<String> fenci(List<String> sentenceList)
			throws IOException {
		List<String> fenciList=new ArrayList<>();
		CLibrary instance = (CLibrary) Native.loadLibrary(System.getProperty("user.dir") + "\\source\\NLPIR",CLibrary.class);
		System.out.println(System.getProperty("user.dir"));
		int init_flag = instance.NLPIR_Init("", 1, "0");
		instance.NLPIR_AddUserWord("个头");
		String resultString = null;
		if (0 == init_flag) {
			resultString = instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！\n" + resultString);
			return null;
		}	
		try {
			for (String rate : sentenceList) {
				resultString = instance.NLPIR_ParagraphProcess(rate, 0);// 1为带词性，0为不带词性
				fenciList.add(resultString);
			}
			instance.NLPIR_Exit();
			
		} catch (Exception e) {
			System.out.println("错误信息：");
			e.printStackTrace();
		}
		List<String> newList=Tool.remove_Punc(fenciList);
		return newList;
	}
	/**
	 * 对句子进行分词
	 * @param sentence
	 * @return
	 * @throws IOException
	 */
	public static List<String> fenci(String sentence)
			throws IOException {
		List<String> fenciList=new ArrayList<>();
		CLibrary instance = (CLibrary) Native.loadLibrary(System.getProperty("user.dir") + "\\source\\NLPIR",CLibrary.class);
		int init_flag = instance.NLPIR_Init("", 1, "0");
		instance.NLPIR_AddUserWord("个头");
		String resultString = null;
		if (0 == init_flag) {
			resultString = instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！\n" + resultString);
			return null;
		}	
		try {		
			resultString = instance.NLPIR_ParagraphProcess(sentence, 0);// 1为带词性，0为不带词性
			resultString=Tool.remove_Punc(resultString);
			String[] wordArray=resultString.split("\\s{1,}");
			for(String word:wordArray)
			{
				fenciList.add(word);
			}		
			instance.NLPIR_Exit();
			
		} catch (Exception e) {
			System.out.println("错误信息：");
			e.printStackTrace();
		}
		return fenciList;
	}
}
