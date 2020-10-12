package cn.hsx.demo;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo005 {
	public static void main(String[] args) throws Exception {
		Class<Student> cls = Student.class;
		Student stu = cls.newInstance();

		Arrays.stream(cls.getDeclaredMethods()).forEach((m) -> {
			if (m.getParameterCount() > 0) {
				String str = "";
				List<String> datas = new ArrayList<>();
				for (int i = 0; i < m.getParameterCount(); i++) {
					datas.add(m.getParameterTypes()[i].getSimpleName() + " " + m.getParameters()[i].getName());
				}
				str = String.join(",", datas);
				System.out.println(Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " " + m.getName()
						+ "(" + str + ")");
			} else {
				System.out.println(Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " " + m.getName()
						+ "(" + ")");
			}
			try {
				int cnt = 0;
				Arrays.stream(m.getParameterTypes()).forEach(c -> {
					//					System.out.println(Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " " + m.getName()
					//							+ "(" + c.getSimpleName() + " " + "arg" + cnt);
				});
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		});

	}
}

class Student {
	private String name;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name, String name1) {
		this.name = name;
	}

	/**
	 * @return age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age セットする age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	private Integer age;

	public Student() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	private String getInfo() {
		return this.name + this.age.toString();
	}
}