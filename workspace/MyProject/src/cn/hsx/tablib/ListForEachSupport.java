package cn.hsx.tablib;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ListForEachSupport extends SimpleTagSupport {

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
		if (item != null) {
			for (Object obj : item) {
				pageContext.setAttribute(var, obj);
				getJspBody().invoke(null);

			}

		}
	}

}
