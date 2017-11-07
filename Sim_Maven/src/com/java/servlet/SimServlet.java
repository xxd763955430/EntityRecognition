package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.java.nlpir.Fenci2Sentence;
import com.sim.hownet.WordSimilarity;
import com.xxd.similarity.Similarity;
import com.xxd.similarity.Similarity2;

/**
 * @author Administrator
 *
 */
public class SimServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sentence1=URLDecoder.decode(request.getParameter("sentence1").toString(),"UTF-8");
		String sentence2=URLDecoder.decode(request.getParameter("sentence2").toString(),"UTF-8");
		List<String> newList1=Fenci2Sentence.fenci(sentence1);	
		List<String> newList2=Fenci2Sentence.fenci(sentence2);
		System.out.println(newList1);
		System.out.println(newList2);
		double howSim= Similarity2.getSenHowSim(newList1, newList2);
		System.out.println("howsim"+howSim);
		double lenSim=Similarity2.getSenLenSim(newList1,newList2);
		double comWordSim=Similarity2.getComWordSim(newList1, newList2);
		if(comWordSim==1)comWordSim=0.9*howSim;
		double sim=0.4*howSim+0.45*comWordSim+0.15*lenSim;
		JsonObject jsonObj=new JsonObject();
		jsonObj.addProperty("howSim", howSim);
		jsonObj.addProperty("comSim", comWordSim);
		jsonObj.addProperty("lenSim", lenSim);
		jsonObj.addProperty("sim", sim);
		PrintWriter out=response.getWriter();
		out.print(jsonObj);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void init() throws ServletException {
		
	}

}
