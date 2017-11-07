package com.java.nlpir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.java.file.FileHelper;
import com.sun.jna.Native;
import com.xxd.similarity.Tool;

/**评论文本分词
 * @author Administrator
 * 
 */
public class Segmentation {
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
	public static void fenci(String source, String destination)
			throws IOException {
		CLibrary instance = (CLibrary) Native.loadLibrary(System.getProperty("user.dir") + "\\source\\NLPIR",CLibrary.class);
		int init_flag = instance.NLPIR_Init("", 1, "0");
		instance.NLPIR_AddUserWord("个头");
		String resultString = null;
		if (0 == init_flag) {
			resultString = instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！\n" + resultString);
			return;
		}
		List<String> rateList = FileHelper.readList(source);
		List<String> fenciList = new ArrayList<>();
		FileHelper.createNewFile(destination);
		try {
			for (String rate : rateList) {
				resultString = instance.NLPIR_ParagraphProcess(rate, 0);// 1为带词性，0为不带词性
				System.out.println(resultString);
				fenciList.add(resultString);
			}
			FileHelper.writeList(fenciList, destination);
			instance.NLPIR_Exit();
		} catch (Exception e) {
			System.out.println("错误信息：");
			e.printStackTrace();
		}
		/*
		 * instance.NLPIR_AddUserWord("金刚圈 n");添加用户自定义词
		 * instance.NLPIR_AddUserWord("左宽右窄"); resultString =
		 * instance.NLPIR_ParagraphProcess(sInput, 1);
		 * System.out.println("增加用户词典后分词结果为：\n" + resultString);
		 * 
		 * instance.NLPIR_DelUsrWord("左宽右窄"); resultString =
		 * instance.NLPIR_ParagraphProcess(sInput, 1);
		 * System.out.println("删除用户词典后分词结果为：\n" + resultString);
		 * 
		 * int nCount = CLibrary.Instance.NLPIR_ImportUserDict(
		 * "D:\\NetBeansProjects\\CWordSeg\\file\\adduserdict.txt",true);
		 * System.out.println(String.format("已导入%d个用户词汇", nCount)); nativeBytes
		 * = CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
		 * System.out.println("导入词典后结果为： " + nativeBytes);
		 * 
		 * instance.NLPIR_ImportUserDict(System.getProperty("user.dir")+
		 * "\\source\\userdic.txt"); resultString =
		 * instance.NLPIR_ParagraphProcess(sInput, 1);
		 * System.out.println("导入用户词典文件后分词结果为：\n" + resultString);
		 * 
		 * resultString = instance.NLPIR_GetKeyWords(sInput,10,false);
		 * System.out.println("从段落中提取的关键词：\n" + resultString);
		 * 
		 * resultString = instance.NLPIR_GetNewWords(sInput, 10, false);
		 * System.out.println("新词提取结果为：\n" + resultString);
		 * 
		 * Double d = instance.NLPIR_FileProcess("d:\\1.txt", "d:\\2.txt", 1);
		 * 
		 * System.out.println("对文件内容进行分词的运行速度为： " ); if(d.isInfinite())
		 * System.out.println("无结果"); else{ BigDecimal b = new BigDecimal(d);
		 * System.out.println(b.divide(new BigDecimal(1000), 2,
		 * BigDecimal.ROUND_HALF_UP)+"秒"); } resultString =
		 * instance.NLPIR_GetFileKeyWords("D:\\3.txt", 10,false);
		 * System.out.println("从文件中提取关键词的结果为：\n" + resultString);
		 */
	}
	/**
	 * 
	* @Description:生成分词无标点的txt
	* @date 2017-6-15 下午5:35:11
	* @param :评论例子文件名，评论分词文件名，无标点评论分词文件名
	* @return void
	 */
	public static void fenciWithoutPunc(String rateSampleName,String rateSegName,String noPuncFenciName) throws IOException
	{
		Segmentation.fenci(rateSampleName, rateSegName);
		List<String> list=FileHelper.readList(rateSegName);
		List<String> newList=Tool.remove_Punc(list);
		FileHelper.createNewFile(noPuncFenciName);
		FileHelper.writeList(newList,noPuncFenciName);
	}
}
