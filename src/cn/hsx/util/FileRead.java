package cn.hsx.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRead {

	public void csvRead(String path, String charsetName) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path), charsetName);
		sc.useDelimiter("\r\n");
		while (sc.hasNext()) {
			if (sc.nextLine().startsWith("#")) {
				continue;
			}
			String[] splitDatas = sc.nextLine().split(",");
		}
	}
}
