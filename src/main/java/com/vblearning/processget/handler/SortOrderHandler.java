package com.vblearning.processget.handler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.vblearning.processget.service.constants.SortOrderType;

public class SortOrderHandler extends SimpleTagSupport {

	/**
	 * Called by the container to invoke this tag. The implementation of this method
	 * is provided by the tag library developer, and handles all tag processing,
	 * body iteration, etc.
	 */
	@Override
	public void doTag() throws JspException {
		JspWriter out = getJspContext().getOut();

		try {
			StringBuilder output = new StringBuilder("<select name=sOrderType>");

			for (SortOrderType sType : SortOrderType.values()) {
				output.append("<option value=").append(sType.getNumberType()).append(">").append(sType.getDesc())
						.append("</option>");

			}
			output.append("</select>");
			out.println(output.toString());

		} catch (Exception ex) {
			throw new JspException("Error in UsersHandler tag", ex);
		}
	}
}
