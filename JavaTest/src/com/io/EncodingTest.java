package com.io;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/9/12 22:34
 * description:
 * 将一个GBK编码的文本文件转存为一个UTF-8编码的文本文件。
 */
public class EncodingTest {
	public static void main(String[] args) throws IOException {
		changeEncoding("GBK","UTF-8","test.txt","test.txt");
	}

	private static void changeEncoding(String inEncoding, String outEncoding,String inFileName, String outFileName) throws IOException {
		InputStreamReader isr = new InputStreamReader(new FileInputStream(inFileName), Charset.forName(inEncoding));
		BufferedReader reader = new BufferedReader(isr);

		OutputStreamWriter ops = new OutputStreamWriter(new FileOutputStream(outFileName),Charset.forName(outEncoding));
		BufferedWriter bfw = new BufferedWriter(ops); //BufferedWriter输出的UTF-8文件是无BOM格式编码的

		String s = "";
		while((s = reader.readLine()) != null){
			bfw.write(s,0,s.length());
			bfw.newLine();
		}
		bfw.flush();
		bfw.close();
		reader.close();
	}
}
