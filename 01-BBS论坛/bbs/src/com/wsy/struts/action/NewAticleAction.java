/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wsy.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.wsy.struts.bean.LoginBean;
import com.wsy.struts.bean.TopicListBean;
import com.wsy.struts.datasource.DB;
import com.wsy.struts.form.NewtopicForm;
import com.wsy.struts.util.StringTrans;

/** 
 * MyEclipse Struts
 * Creation date: 11-09-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class NewAticleAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int i=0;//判断是否插入成功的变量i
		int j=0;//判断将数据插入tb_response表中是否成功
		List listresponsetitle=null;//取回复用户的相关信息
		NewtopicForm newtopicForm = (NewtopicForm) form;// TODO Auto-generated method stub
		ActionMessages error=new ActionMessages();
		HttpSession session=request.getSession();
		StringTrans s=new StringTrans();
		String title=newtopicForm.getTitle();
		String content=newtopicForm.getContent();
		String xq=newtopicForm.getXq();
		String name=(String)session.getAttribute("name");
		String forumid=(String)session.getAttribute("forumId");
		String forumname=(String)session.getAttribute("forumName");
		
		String type=(String)session.getAttribute("type");
		String topicid=(String)session.getAttribute("topicid");
		String topicname=(String)session.getAttribute("title");
		//System.out.println("type"+type);
		System.out.println("topicname"+forumname);		
		TopicListBean t=new TopicListBean();
		DataSource datasource = getDataSource(request,"dataSources");//取struts配置的数据源
		if(type==null){
			i=t.InsertData(title, content, xq, name, forumid, forumname, datasource);//如果是发表主题，将内容插入tb_topic表中
			System.out.println("插入主题结果"+i);
		}
		else{ 
			LoginBean l=new LoginBean();
			j=t.InsertDataResponse(title, content, xq, name, topicid,topicname,datasource);//如果是回复主题，将数据插入tb_response表中
			listresponsetitle=l.getRecordForUser(name, datasource);//取出此作者的相关信息
			System.out.println("j"+j);
		}
		
		if(i==1){
			try{
				List l = t.search(datasource, 0, forumid);//查询该论坛的主题
				session.setAttribute("List", l);
				System.out.println("new atrticleaction"+l);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			return mapping.findForward("success");
		}
		if(j==1){
			session.setAttribute("listreponsetitle", listresponsetitle);
			TopicListBean topic=new TopicListBean();
			List listResponse=(List)topic.getRecordResponse(topicid, datasource);
			session.setAttribute("listResponse", listResponse);
			return mapping.findForward("responsesuccess");
		}
		return mapping.getInputForward();
	}
}