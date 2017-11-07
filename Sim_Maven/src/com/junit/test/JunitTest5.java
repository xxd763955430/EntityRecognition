package com.junit.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat.Encoding;

import org.junit.Test;

import com.java.nlpir.Segmentation;
import com.xxd.similarity.HowNetSimilarty;
import com.xxd.similarity.Similarity;

/**
 * @author Administrator
 * 
 */
public class JunitTest5 {
	
	public void test1() {
		double how=0.75,len=0.6,com=0.75;
		System.out.println(how*0.4+len*0.1+com*0.5);
	}
	
	public void test2() {
		for(int x=1;x<=3;x++)
		{
		double sim=Similarity.getComWordSim(x);
		System.out.println(sim);
		}
	}
	//生成n个testWord
	public void testGenerateWordFile() throws IOException {
			int count = 1;
			List<String> combinRate = HowNetSimilarty.getRateCombination("F://实验/实验2/rateSegNoPunc.txt");
			for (String str : combinRate) {
				String[] array = str.split("###");
				HowNetSimilarty.generateWordFile(array[0], array[1], "F://实验/实验2/testWord/testWord "+ count + ".txt");
				System.out.println(count);
				count++;
			}
		}
	@Test
	public void testF() {
		
		double P=83.33;
		double R=68.33;
		double F=2/(1/P+1/R);
		System.out.println(F);
		
	}
}
