package cn.hsx.demo;

import java.util.UUID;

public class Demo008 {
	public static void main(String[] args) {
		Test t = System.out::println;
		t.print("hello world...");
		String string = UUID.randomUUID().toString();
		System.out.println(string.replace("-", ""));
	}
}

@FunctionalInterface
interface Test{
	void print(String msg);
}
