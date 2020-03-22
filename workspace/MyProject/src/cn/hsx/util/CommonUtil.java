package cn.hsx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtil {

	private static Properties pp;
	static {
		InputStream is = CommonUtil.class.getResourceAsStream("/cn/hsx/propertyfile/errormessage.properties");
		pp = new Properties();
		try {
			pp.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * パラメータnullチェック
	 * 一つでもnullなら、True
	 * @param args
	 * @return
	 */
	public static boolean isNull(Object... args) {
		boolean flag = false;
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				if (args[i] == null || args[i].toString().length() == 0) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 曖昧検索用パラメータ変換
	 * @param str
	 * @return
	 */
	public static String paramChange(String str) {
		if (str == null) {
			return "%%";
		}
		return "%" + str + "%";
	}

	public static String getMessage(String messageId) {
		return pp.getProperty(messageId);
	}

	/**
	 * テスト用メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(CommonUtil.getMessage("repeat"));
	}
}
