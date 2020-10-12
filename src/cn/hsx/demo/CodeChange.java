package cn.hsx.demo;

public class CodeChange {
	public static String codeCon(String path) {
		StringBuffer sb = new StringBuffer();
		char[] cc = path.toCharArray();
		for (char c : cc) {
			if (c == ' ') {
				sb.append(String.valueOf("&nbsp;"));
			} else if (c == '<') {
				sb.append(String.valueOf("&lt"));
			} else {
				sb.append(c);
			}

		}

		return sb.toString();
	}
}
