package com.vblearning.processget.handler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class NumberHandler extends SimpleTagSupport {

	/**
	 * Called by the container to invoke this tag. The implementation of this method
	 * is provided by the tag library developer, and handles all tag processing,
	 * body iteration, etc.
	 */
	@Override
	public void doTag() throws JspException {
		JspWriter out = getJspContext().getOut();

		try {
			StringBuilder output = new StringBuilder("<select name=uid>");

			int i = 1;
			while (i < 10) {
				output.append("<option value=").append(String.valueOf(i)).append(">").append(String.valueOf(i * 10))
						.append("</option>");
				i++;
			}
			output.append("</select>");
			out.println(output.toString());

		} catch (Exception ex) {
			throw new JspException("Error in UsersHandler tag", ex);
		}
	}
}
