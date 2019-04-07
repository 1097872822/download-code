package com.wsy.struts.tld;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.wsy.struts.bean.LoginBean;
import com.wsy.struts.util.StringTrans;

public class DisplayUsermanagerTag extends TagSupport{
	public int doEndTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
	        HttpSession session = pageContext.getSession();
	        List listuser=(List)session.getAttribute("listuser");
	        StringTrans s=new StringTrans();
	        
	        try{
	        	if(listuser!=null){
	        		for(int i=0;i<listuser.size();i++){
	        		LoginBean login=(LoginBean)listuser.get(i);
						out.println("<tr>");
						out.println("<td width='804' height='67' valign='top' background='images/um_03.gif'>");
						out.println("<table width='100%'  border='0' height='67'>");
						out.println("<tr align='center'>");
						out.println("<td width='16%' align='center' valign='top'><img src='images/touxiang/"+login.getTx()+"' width='60' height='58'></td>");
						out.println("<td width='11%' class='zczi'>"+s.tranC(login.getUsername())+"</td>");
						if(login.getGrade().equals("admin"))
							out.println("<td width='24%' class='zczi'>管理员</td>");
						if(login.getGrade().equals("user"))
							out.println("<td width='24%' class='zczi'>普通用户</td>");
						if(login.getGrade().equals("bz"))
							out.println("<td width='24%' class='zczi'>斑竹</td>");
						//out.println("<td width='32%' class='zczi'><a href='useredit.do?id="+login.getId()+"&name="+login.getUsername()+"' class='zczi'>编辑</a></td>");
						//out.println("<td width='32%' class='zczi'><a href='#' onclick=window.open('useredit.do?id="+login.getId()+"&name="+login.getUsername()+"') class='zczi'>编辑</a></td>");
						if(login.getUsername().equals("TSoft")){//保证系统始终有个超级用户，使名称为TSoft的用户不可编辑，删除。
							out.println("<td width='32%' class='zczi'>编辑</td>");
							out.println("<td width='17%' class='zczi'>删除</td>");
						}
						else{
							session.setAttribute("grade",login.getGrade());
							out.println("<td width='32%' class='zczi'><a href='#' onclick=window.open('useredit.do?id="+login.getId()+"&name="+login.getUsername()+"&grade="+login.getGrade()+"','newwindow','width=276,height=174,top=400,left=500') class='zczi'>编辑</a></td>");
							out.println("<td width='17%' class='zczi'><a href='#' onclick=window.open('userdelete.do?id="+login.getId()+"&name="+login.getUsername()+"','newwindow','width=276,height=174,top=400,left=500') class='zczi'>删除</a></td>");
						}
						out.println("</tr>");
						out.println("</table></td></tr>");
	        		}
	        	}
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	        return super.doEndTag();    	
	}
	
}
