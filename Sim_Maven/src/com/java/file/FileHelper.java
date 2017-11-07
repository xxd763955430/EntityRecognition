package com.java.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//文件读写帮助类
public class FileHelper {
	@Test
	public void delete()
	{
		String path="F:/个词相似度";
		File file=new File(path);
		deleteDir(file);
	}
	public void createTest()
	{
		for(int i=1;i<=105;i++)
			try {
				createNewFile("F://个词相似度/TestSimilarities"+i+".txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	/**
	 * 
	* @Description:
	* @date 2017年11月7日 下午3:16:08
	* @param :
	* @return void
	 */
	public static void deleteDir(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles())
                deleteDir(f);
        }
        file.delete();
    }
	/**
	 * 
	* @Description:把需要比较相似度的两个词按行写入到文件中
	* @date 2017-6-2 上午10:50:44
	* @param :文件位置,两个词语
	* @return void
	 */
	public static void writeWord(String uri, String word1, String word2) {
		Path filepath = Paths.get(uri);
		BufferedWriter out = null;
		try {
			if (!Files.exists(filepath))
				Files.createFile(filepath);
			out = Files.newBufferedWriter(filepath, StandardCharsets.UTF_8,
					StandardOpenOption.APPEND);
			out.write(word1);
			out.newLine();
			out.write(word2);
			out.newLine();
			out.newLine();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 
	* @Description:写列表到文件
	* @date 2017-6-2 上午10:47:41
	* @param :评论，文件位置
	* @return void
	 */
    public static void writeList(List<String> rates,String uri) throws IOException
    {
    	Path filepath = Paths.get(uri);
    	if(!Files.exists(filepath))
    		Files.createFile(filepath);
		Files.write(filepath, rates,StandardCharsets.UTF_8,StandardOpenOption.APPEND);	
    }
    /**
     * 
    * @Description:读文件到列表
    * @date 2017-6-2 上午10:46:47
    * @param :
    * @return List<String>
     */
    public static List<String> readList(String uri) throws IOException
    {
    	Path filepath = Paths.get(uri);
    	List<String> lists=new ArrayList<>();	
		lists = Files.readAllLines(filepath,StandardCharsets.UTF_8);
		return lists;
    }
    /**
     * 
    * @Description:创建新文件
    * @date 2017-6-2 上午10:48:26
    * @param :
    * @return void
     */
    public static void createNewFile(String uri) throws IOException
    {
    	Path filepath = Paths.get(uri);
    	Files.deleteIfExists(filepath);	
		Files.createFile(filepath);
    }
}