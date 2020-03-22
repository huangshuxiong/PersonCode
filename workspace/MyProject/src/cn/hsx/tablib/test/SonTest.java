package cn.hsx.tablib.test;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SonTest extends SimpleTagSupport {
	private String test;

	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public void doTag() throws JspException, IOException {
		ParentTest parenttag = (ParentTest) getParent();
		if(!parenttag.getFlag()){
			if (test.equals("true")) {
				parenttag.setFlag(true);
				getJspBody().invoke(null);
			}

		}
	}
}
