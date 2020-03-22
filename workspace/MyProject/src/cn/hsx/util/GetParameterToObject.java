package cn.hsx.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class GetParameterToObject {
	public static Object getParameter(HttpServletRequest request, Object obj) throws Exception {
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = enu.nextElement();
			BeanUtil.setProperty(obj, name.toUpperCase(), request.getParameter(name));
		}
		return obj;
	}
}
