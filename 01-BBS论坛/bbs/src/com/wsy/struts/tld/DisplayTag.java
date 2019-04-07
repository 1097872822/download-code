package com.wsy.struts.tld;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.wsy.struts.bean.ForumBean;
import com.wsy.struts.util.StringTrans;



public class DisplayTag extends TagSupport{
	public int doEndTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
	        HttpSession session = pageContext.getSession();
	        StringTrans s=new StringTrans();
	    try{
	    	
	    	String name=(String)session.getAttribute("name");
	    	//session.setAttribute("name", name);
	    	List l=new ArrayList();
	    	if(session.getAttribute("list")!=null){
	    		l=(List)session.getAttribute("list");	
	    	}
	    	//else {
	    		//ForumBean forum=new ForumBean();
	    		//l=forum.getRecord();
	    	//}
	    	String iflogin=(String)session.getAttribute("i");
	    	//System.out.println("iflogin"+iflogin);
	        	for(int i=0;i<l.size();i++){
	        		ForumBean f=(ForumBean)l.get(i);	

	        		  
	        		out.println("<tr>");
					out.println("<td colspan='2'  height='64'>");
					if(i%2==0){
						out.println("<table width='100%' height='64' border='0' background='images/06.gif'>");
					}
					else
						out.println("<table width='100%' height='64' border='0' background='images/061.gif'>");
					out.println("<tr>");
					out.println("<td></td>");
					if(iflogin==null)
						out.println("<td  colspan='2'><span class='zczi'>科技公司出版的</span><span class='zczi'>[<a href=topicList.do?forumid="+ f.getId() + "&forumname=" + s.tranC(f.getForumnname()) +"&iflogin="+iflogin+" class='zczi' onClick=alert('请您首先登录')>"+s.tranC(f.getForumnname())+"</a> ]</span><span class='zczi'>语言类专区</span></td>");
					else
						out.println("<td  colspan='2'><span class='zczi'>科技公司出版的</span><span class='zczi'>[<a href=topicList.do?forumid="+ f.getId() + "&forumname=" + s.tranC(f.getForumnname()) +"&iflogin="+iflogin+" class='zczi'>"+s.tranC(f.getForumnname())+"</a> ]</span><span class='zczi'>语言类专区</span></td>");	
					out.println("<td width='13%' valign='bottom' class='zi'>"+f.getCreatetime()+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='11%' height='21'>&nbsp;</td>");
					out.println("<td width='5%'  class='zczi'>版主：</td>");
	        		if(f.getManager()!=null){
	        			out.println("<td width='71%'  class='zczi'>"+s.tranC(f.getManager())+"</td>");
	        		}
	        		else{
	        			out.println("<td width='71%'  class='zczi'>此论坛暂时没有斑竹</td>");
	        		  }
					out.println("<td class='zi' valign='bottom'>"+f.getCount()+"</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println("</tr>");
	        		  
	        	}
	    	

		}catch(Exception e){
			e.printStackTrace();
		}
	    
		return super.doEndTag();
	}
	
}