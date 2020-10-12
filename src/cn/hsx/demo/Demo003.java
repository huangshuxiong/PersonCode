package cn.hsx.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Demo003 {

	public static <T> void doSanitizer(T obj, String... strings) throws Exception, IllegalArgumentException, InvocationTargetException {

		Class<?> cls = obj.getClass();
		Arrays.stream(cls.getDeclaredMethods()).filter(x -> x.getName().substring(3).equalsIgnoreCase(strings[0])).forEach(x -> {
			try {
				Object value =  x.invoke(obj);
				value = CodeChange.codeCon((String)value);
				x.invoke((Object)obj, value);
			} catch (IllegalAccessException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		});
	}

	public static void main(String[] args) throws IllegalArgumentException, InvocationTargetException, Exception {
		Customer c = new Customer();
		c.setUserName(" jim");
//		Demo003.doSanitizer(c, "username");

		Class<?> cls = c.getClass();
		Method m = cls.getDeclaredMethod("getUserName");
		Object value = m.invoke(c);
		value = CodeChange.codeCon((String)value);
		Method m1 = cls.getDeclaredMethod("setUserName",String.class);
		m1.invoke(c, value);


		System.out.println(c.getUserName());
	}
}
