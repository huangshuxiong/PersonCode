package cn.hsx.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo004 {
	public static void main(String[] args) throws IOException {
		String command = "path G:/pleiades/java/8/bin && cd G:/pleiades/java/8/bin && cd g: && java -jar C:/Users/shuxi/Desktop/demo/demo.jar";
		//String command ="path G:/pleiades/java/8/bin && cd C:/Users/shuxi/Desktop/demo && dir";
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("cmd.exe /c"+command);
		BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		System.out.println(br.readLine());
	}

}
