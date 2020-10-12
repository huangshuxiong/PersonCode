package cn.hsx.demo;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TempDemo001 {
	private static Properties pp = null;
	private static Map<String, String> items = new HashMap<>();
//	static {
//		ResourceBundle rb = ResourceBundle.getBundle("info");
//		Enumeration<String> enu = rb.getKeys();
//		while (enu.hasMoreElements()) {
//			String[] values = enu.nextElement().split("-");
//			//			String value = rb.getString(key);
//			items.put(values[0], values[1]);
//
//		}
//	}

	public static void main(String[] args) throws Exception {
//		Map<String, File> datas = new TempDemo001().getFileInfo("E:/video/jQuery网页开发案例精讲视频");
//		datas.forEach((k1, v1) -> {
//			items.forEach((k2, v2) -> {
//				if (k1.equals(k2)) {
//					v1.renameTo(new File(v1.getParent() + File.separator + k1 + "-" + v2));
//				}
//			});
//		});
		String path="01springｵﾚﾒｻﾌ・ﾎｳﾌｰｲﾅﾅ.mp4";
//		System.out.println(path.substring(0, path.indexOf(".")).split(regex));
	}

	private Map<String, File> getFileInfo(String path) throws Exception {
		Map<String, File> items = new HashMap<>();
		File[] files = new File(path).listFiles();
		Arrays.stream(files).filter(file -> !file.getName().contains("zip")).forEach(ff -> {
			String[] fileName = ff.getName().split("-");
			items.put(fileName[0], ff);
		});
		return items;
	}
}
