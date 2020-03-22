package cn.hsx.util;

import java.lang.reflect.Field;

public class BeanUtil {

	/**
	 *
	 * @param obj
	 * @param columnName
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setProperty(Object obj, String columnName, Object value)
			throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.getName().equalsIgnoreCase(columnName)) {
				field.set(obj, value);
			}
		}
	}
}
