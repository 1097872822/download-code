package com.wsy.struts.tld;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.wsy.struts.bean.LoginBean;

public class DisplaySearchUserTag extends TagSupport{
	public int doEndTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
	        HttpSession session = pageContext.getSession();
	        List listsearchname=(List)session.getAttribute("listsearchname");
	        try{
	        	if(listsearchname!=null){
	        		for(int i=0;i<listsearchname.size();i++){
		        		LoginBean login=(LoginBean)listsearchname.get(i);{
		        			out.println("<tr>");
							out.println("<td width='804' height='67' valign='top' background='images/um_03.gif'>");
							out.println("<table width='100%'  border='0' height='67'>");
							out.println("<tr align='center'>");
							out.println("<td width='16%' align='center' valign='top'><img src='images/touxiang/"+login.getTx()+"' width='60' height='58'></td>");
							out.println("<td width='11%' class='zczi'>"+login.getUsername()+"</td>");
							if(login.getGrade().equals("admin"))
								out.println("<td width='24%' class='zczi'>管理员</td>");
							if(login.getGrade().equals("user"))
								out.println("<td width='24%' class='zczi'>普通用户</td>");
							if(login.getGrade().equals("bz"))
								out.println("<td width='24%' class='zczi'>斑竹</td>");
							out.println("<td width='32%' class='zczi'><a href='userdeit.do?id="+login.getId()+"&name="+login.getUsername()+"' class='zczi'>编辑</a></td>");
							out.println("<td width='17%' class='zczi'><a href='userdelete.do?id="+login.getId()+"&name="+login.getUsername()+"' class='zczi'>删除</a></td>");
							out.println("</tr>");
							out.println("</table></td></tr>");
		        		}
	        		}
	        	}
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	        return super.doEndTag();
	}
}
