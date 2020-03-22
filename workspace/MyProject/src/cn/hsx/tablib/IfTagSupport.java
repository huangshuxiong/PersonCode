package cn.hsx.tablib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IfTagSupport extends SimpleTagSupport {
	private String test;

	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public void doTag() throws JspException, IOException {
		if (this.test.equals("true")||test!=null) {
			getJspBody().invoke(null);
		}
	}
}
