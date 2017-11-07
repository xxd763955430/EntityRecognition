/*
 * Copyright (C) 2008 SKLSDE(State Key Laboratory of Software Development and Environment, Beihang University)., All Rights Reserved.
 */
package com.junit.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.sim.hownet.WordSimilarity;


/**
 * DOCUMENT ME!
 *
 * @author xxd
 * @version 1.0
  */
public class WordSimilarityTests extends TestCase {

    @Test
    public void test_simWord() throws IOException{
    	System.out.println(WordSimilarity.simWord("男人", "电脑"));
    }
    
}
