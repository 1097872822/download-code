package com.wsy.struts.tld;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import javax.sql.DataSource;

import com.wsy.struts.bean.LoginBean;
import com.wsy.struts.bean.TopicListBean;
import com.wsy.struts.util.StringTrans;
import com.wsy.struts.util.umlChange;

public class DisplayResponseTag extends TagSupport{
	public int doEndTag() throws JspException
	{
		JspWriter out = pageContext.getOut();
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
	    HttpSession session = pageContext.getSession();
	    
	    try{
	    	if (session.getAttribute("listauthor") != null) {
				List l = (List) session.getAttribute("listauthor");
				StringTrans s=new StringTrans();//转码类
				umlChange umlchange=new umlChange();
				for(int i=0;i<l.size();i++){
					LoginBean login=(LoginBean)l.get(i);
					out.println("<tr>");
					out.println("<td width='1002' height='189' valign='top' background='images/r2.gif'>");
					out.println("<table width='100%' height='171'  border='0'>");
					out.println("<tr>");
					out.println("<td  height='28' colspan='2'>&nbsp;</td>");
					//out.println("<td width='10%' class='zi'>用户名："+login.getUsername()+"</td>");
					out.println("<td width='7%'>&nbsp;</td>");
					out.println("<td width='21%' class='zczi' valign='baseline'>"+login.getEmail()+"</td>");
					out.println("<td width='15%' class='zczi' valign='baseline'>"+login.getOicq()+"</td>");
					out.println("<td width='30%' class='fyzi' valign='baseline'>"+session.getAttribute("submittime")+"</td>");
					out.println("<td class='hfzi'><a href='newtopic.do?type=response&name="+(String)session.getAttribute("name")+"' class='hfzi'>回复</a></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td height='75' colspan='2' align='center'><img src='images/touxiang/"+login.getTx()+"' width='60' height='60'></td>");
					out.println("<td colspan='5' rowspan='2' valign='top' class='zczi'>"+s.tranC(umlchange.Change((String)session.getAttribute("content")))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					String username=login.getUsername();
					out.println("<td height='21'align='center' valign='middle'class='zczi'colspan='2'>作者："+username+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='9%' align='center' valign='top'>&nbsp;</td>");
					out.println("<td width='8%' valign='top'><img src='images/xq/"+session.getAttribute("xq")+"' width='20' height='20'></td>");
					out.println("<td colspan='4'>&nbsp;</td>");
					out.println("<td width='10%'>&nbsp;</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println("</tr>");
				}
				
				List listResponse=(List)session.getAttribute("listResponse");//回复信息
				DataSource datasource=(DataSource)session.getAttribute("DataSource");
				if(listResponse!=null){
					for(int i=0;i<listResponse.size();i++){
					TopicListBean t=(TopicListBean)listResponse.get(i);
					out.println("<tr>");
					out.println("<td width='1002' height='183' valign='top' background='images/r3.gif'>");
					out.println("<table width='100%' height='177'  border='0'>");
					out.println("<tr>");
					LoginBean login=new LoginBean();
					String name=(String)session.getAttribute("name");//登录人
					List listuser=login.getRecordForUser(t.getAuthor(), datasource);
					for(int w=0;w<listuser.size();w++)
						login=(LoginBean)listuser.get(w);
					out.println("<td  height='28' colspan='2'>&nbsp;</td>");
					out.println("<td width='8%'>&nbsp;</td>");

					out.println("<td width='20%' class='zczi' valign='baseline'>"+login.getEmail()+"</td>");
					out.println("<td width='15%' class='zczi' valign='baseline'>"+login.getOicq()+"</td>");
					out.println("<td width='30%' class='fyzi' valign='baseline'>"+t.getSubmittime()+"</td>");
					out.println("<td class='hfzi'><a href='newtopic.do?type=response&name="+name+"' class='hfzi'>回复</a></td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td height='66' colspan='2' align='center'><img src='images/touxiang/"+login.getTx()+"' width='60' height='60'></td>");
					out.println("<td colspan='5' rowspan='2' valign='top' class='zczi'>"+s.tranC(umlchange.Change(t.getContent()))+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					String username=login.getUsername();

					out.println("<td height='27' colspan='2' align='center' valign='middle'class='zczi'>作者："+username+"</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td width='9%' height='42' align='center' valign='top'>&nbsp;</td>");
					out.println("<td width='8%' valign='top'><img src='images/xq/"+t.getXq()+"' width='20' height='20'></td>");
					out.println("<td colspan='4'>&nbsp;</td>");
					out.println("<td width='10%'>&nbsp;</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("</td>");
					out.println("</tr>");
					}
				}
			}
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return super.doEndTag();
	}
}
