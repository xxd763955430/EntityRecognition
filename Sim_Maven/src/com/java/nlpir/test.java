package com.java.nlpir;

import org.junit.Test;

import com.sun.jna.Native;

public class test {
	@Test
public void test1(){
	CLibrary instance = (CLibrary) Native.loadLibrary("NLPIR",CLibrary.class);
	int init_flag = instance.NLPIR_Init("", 1, "0");
	instance.NLPIR_AddUserWord("个头");
	String resultString = null;
	if (0 == init_flag) {
		resultString = instance.NLPIR_GetLastErrorMsg();
		System.err.println("初始化失败！\n" + resultString);
		return;
	}
	try {
		    String rate="上海市徐汇区桂林路";
			resultString = instance.NLPIR_ParagraphProcess(rate, 1);// 1为带词性，0为不带词性
			System.out.println(resultString);
			instance.NLPIR_Exit();
		}
catch (Exception e) {
		System.out.println("错误信息：");
		e.printStackTrace();
	}
}
}
