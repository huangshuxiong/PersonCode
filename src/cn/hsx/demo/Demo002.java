package cn.hsx.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo002 {

	public static void main(String[] args) throws Exception {
		new Demo002().read("E:\\test.txt");
		System.out.println("hot_fix branch ....");
	}

	public void read(String path) throws Exception {
		File file = new File(path);
		long fileSize = file.length();
		byte[] b = new byte[new Long(fileSize).intValue()];
		InputStream is = new FileInputStream(file);
		is.read(b);
		Pattern p = Pattern.compile("(<.*>)",Pattern.DOTALL);
		Matcher m = p.matcher(new String(b));
		while (m.find()) {
			System.out.println(m.group(1));
		}
		is.close();
	}

}
