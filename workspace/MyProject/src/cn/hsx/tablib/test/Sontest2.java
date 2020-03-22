package cn.hsx.tablib.test;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Sontest2 extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		ParentTest parenttag = (ParentTest) getParent();
		if (!parenttag.getFlag()) {
			parenttag.setFlag(true);
			getJspBody().invoke(null);
		}
	}
}