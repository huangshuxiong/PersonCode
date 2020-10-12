package cn.hsx.demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo006 {
	public static void main(String[] args) throws Exception {
		String data = Demo006.read("F:/job/kou_git/vscode");
		Pattern pt = Pattern.compile("'(.*)':");
		Matcher mc = pt.matcher(data);
		while(mc.find()){
			System.out.println(mc.group(1));
		}
	}

	public static String read(String path) throws Exception {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		long fileSize = file.length();
		byte[] b = new byte[new Long(fileSize).intValue()];
		fis.read(b);
		String result = new String(b);
		fis.close();
		return result;
	}
}
