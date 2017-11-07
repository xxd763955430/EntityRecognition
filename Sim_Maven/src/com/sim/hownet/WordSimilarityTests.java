/*
 * Copyright (C) 2008 SKLSDE(State Key Laboratory of Software Development and Environment, Beihang University)., All Rights Reserved.
 */
package com.sim.hownet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.sim.hownet.WordSimilarity;


/**
 * DOCUMENT ME!
 *
 * @author xxd
 * @version 1.0
  */
public class WordSimilarityTests extends TestCase {

	public static List<String> getFenci(String line)
	{
		Segment shortestSegment = new ViterbiSegment();
		List<Term> termList=shortestSegment.seg(line);
		List<String> strList=new ArrayList<>();
		for(int i=0;i<termList.size();i++)
		{
			strList.add(termList.get(i).toString());			
		}
		return strList;
	}
	public static double getMaxSim(String word1,List<String> wordList)
	{
		double maxSim=0;
		for(String word2:wordList)
		{
			if(WordSimilarity.simWord(word1, word2)>maxSim)
				maxSim=WordSimilarity.simWord(word1, word2);
		}
		return maxSim;
	}
    @Test
    public void test_simWord() throws IOException{
    	String path="F:/数据引擎/数据导出4/测试词汇.txt";
    	List<String> wordList=new ArrayList<>();
        String word1 = "计算机";
        File file=new File(path);
        InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), "UTF-8");
		BufferedReader reader = new BufferedReader(read);
		String line=null;
		int index=0;
		int maxIndex=index;
		double maxSim=0;
		List<String> dictWord=new ArrayList<>();
        while((line=reader.readLine())!=null)
        {
        	dictWord.add(line);
        	List<String> strList=getFenci(line);
        	double sim=getMaxSim(word1, strList);
        	if(sim>maxSim)
        	{
        		maxSim=sim;
        		maxIndex=index;
        	}     	
        	index++;
        }
        System.out.println(dictWord.get(maxIndex)+" "+maxSim);
    }
    
}
