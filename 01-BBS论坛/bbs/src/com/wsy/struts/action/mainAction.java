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

import com.wsy.struts.bean.ForumBean;
import com.wsy.struts.bean.LoginBean;

public class mainAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DataSource datasource = getDataSource(request,"dataSources");//取struts配置的数据源
		HttpSession session=request.getSession();
		ForumBean forum=new ForumBean();
		LoginBean login=new LoginBean();
		List listforum=forum.getRecord(datasource);
		List listusertotal=login.getTotalRecord(datasource);
		session.setAttribute("listusertotal", listusertotal);
		session.setAttribute("listforum", listforum);
		return mapping.getInputForward();
	}

}
