package com.wsy.struts.tld;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.wsy.struts.bean.LoginBean;

public class DisplayUserMessageTag extends TagSupport{
	public int doEndTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
	    HttpSession session = pageContext.getSession();
	    try{
	    	if (session.getAttribute("listresponse") != null) {
				List l = (List) session.getAttribute("listresponse");
				for(int i=0;i<l.size();i++){
					LoginBean t=(LoginBean)l.get(i);
					out.println("<table width='150' border='0' cellpadding='0' cellspacing='0'>");
					out.println("<tr>"); 
					out.println("<td height='15'>&nbsp;</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table width='150' height='80' border='0' align='center' cellpadding='0' cellspacing='0'>");
					out.println("<tr>"); 
					out.println("<td><div align='center'><img src='images/head/7.gif' /></div></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table width='150' height='20' border='0' align='center' cellpadding='0' cellspacing='0'>");
					out.println("<tr>"); 
					out.println("<td><div align='center'>");
					out.println("mr_friend</div></td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<table width='150' border='0' align='center' cellpadding='0' cellspacing='0'>");
					out.println("<tr>"); 
					out.println("<td height='20'><div align='center'><img src='images/email.gif' width='45' height='16' alt='noGive@163.com'/><img src='images/qq.gif' width='45' height='16' alt='3642159'/><img src='images/ip.gif' width='55' height='16' alt='127.0.0.1'/></div></td>");
					out.println("</tr>");
					out.println("</table>");
				}
			}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return super.doEndTag();
	}
}
