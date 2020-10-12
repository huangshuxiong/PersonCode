package cn.hsx.demo;

import java.lang.reflect.Proxy;

public class Demo007 implements Math {

	@Override
	public int add(int x, int y) {
		System.out.println("kkkkk");
		return x + y;
	}

	@Override
	public int mul(int x, int y) {
		return x - y;
	}

	public static void main(String[] args) {
		Class<?> cls = Demo007.class;
		Math m = (Math) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), (proxy, method, arg) -> {
			System.out.println("begin...");
			Object invoke = method.invoke(cls.newInstance(), arg);
			System.out.println("end...");

			return invoke;
		});
		System.out.println(m.add(1, 2));
	}

}

interface Math {
	public int add(int x, int y);

	public int mul(int x, int y);

}