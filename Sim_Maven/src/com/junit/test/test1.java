package com.junit.test;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

/**
 * @author Administrator
 *
 */
public class test1 {
public static void main(String[] args) {
	List<Term> termList = HanLP.segment("商品和服务");
	System.out.println(termList);
}
}
