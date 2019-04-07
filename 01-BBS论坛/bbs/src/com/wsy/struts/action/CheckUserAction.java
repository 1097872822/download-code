package com.wsy.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.wsy.struts.bean.LoginBean;

public class CheckUserAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionMessages message=new ActionMessages();
		DataSource datasource = getDataSource(request,"dataSources");//取struts配置的数据源
		String name=request.getParameter("username");
		LoginBean login=new LoginBean();
		int i=login.CheckUser(name, datasource);
		if(i==0){
			message.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("check.user.success"));
			saveErrors(request,message);
		}
		System.out.println("checkuser"+i);
		HttpSession session=request.getSession();
		session.setAttribute("checkuser", i+"");
		session.setAttribute("username", name);
		return mapping.getInputForward();
	}
	
}
