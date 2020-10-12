package cn.hsx.demo;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo005 {
	public static void main(String[] args) throws Exception {
		System.out.println(Demo005.class.getSimpleName());
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
				// TODO 閾ｪ蜍慕函謌舌＆繧後◆ catch 繝悶Ο繝�繧ｯ
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
	 * @param name 繧ｻ繝�繝医☆繧� name
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
	 * @param age 繧ｻ繝�繝医☆繧� age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	private Integer age;

	public Student() {
		// TODO 閾ｪ蜍慕函謌舌＆繧後◆繧ｳ繝ｳ繧ｹ繝医Λ繧ｯ繧ｿ繝ｼ繝ｻ繧ｹ繧ｿ繝�
	}

	private String getInfo() {
		return this.name + this.age.toString();
	}
}