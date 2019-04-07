package com.wsy.struts.tld;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.wsy.struts.bean.TopicListBean;
import com.wsy.struts.util.StringTrans;

public class DisplayTopicTag extends TagSupport{
	public int doEndTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
	    HttpSession session = pageContext.getSession();
	    StringTrans s=new StringTrans();
	    try{
	    	if (session.getAttribute("List") != null) {
				List l = (List) session.getAttribute("List");
				for(int i=0;i<l.size();i++){
					TopicListBean t=(TopicListBean)l.get(i);
					out.println("<tr>");
					out.println("<td width='1002' height='33' background='images/lb3.gif'><table width='100%'  border='0'>");
					out.println("<tr>");
					out.println("<td width='8%'>&nbsp;</td>");
					out.println("<td width='8%' align='center' valign='middle'><img src='images/xq/"+ t.getXq()+"' width='20' height='20'></td>");
					//out.println("<td width='38%' class='zczi' align='center' valign='middle'><a href='responselist.do?name="+session.getAttribute("name")+"&zuozhe="+t.getAuthor()+"&content="+s.tranC(t.getContent())+"&topicid="+t.getId()+"&submittime="+t.getSubmittime()+"&xq="+t.getXq()+"&title="+s.tranC(t.getTitle())+"' class='zczi'>"+s.tranC(t.getTitle())+"</a></td>");
					out.println("<td width='38%' class='zczi' align='center' valign='middle'><a href='responselist.do?name="+session.getAttribute("name")+"&topicid="+t.getId()+"' class='zczi'>"+s.tranC(t.getTitle())+"</a></td>");
					out.println("<td width='12%' class='zczi' align='center' valign='middle'>"+s.tranC(t.getAuthor())+"</td>");
					out.println("<td width='13%' class='zczi' align='center' valign='middle'>"+t.getReCount()+"/"+t.getRq()+"</td>");
					out.println("<td width='21%'class='zczi' align='center' valign='middle'>"+t.getSubmittime()+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println("</tr>");
				}
			}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return super.doEndTag();
	}
}
