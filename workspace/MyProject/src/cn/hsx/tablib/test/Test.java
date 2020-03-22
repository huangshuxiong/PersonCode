package cn.hsx.tablib.test;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Test extends SimpleTagSupport {

	private PageContext pageContext;
	private Collection<?> item;
	private String var;

	public void setItem(Collection<?> item) {
		this.item = item;
	}

	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		pageContext = (PageContext) getJspContext();
		for (Object obj : item) {
			pageContext.setAttribute(var, obj);
			getJspBody().invoke(null);

		}
	}

	@org.junit.Test
	public void test001() {
		Locale l = Locale.JAPAN;
		System.out.println(l.getCountry());
		System.out.println(l.getLanguage());
		System.out.println(l.getDisplayCountry());
	}
}
