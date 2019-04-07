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

import com.wsy.struts.bean.ResponseBean;

public class ResponseTopicAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ResponseBean r=new ResponseBean();
		DataSource datasource = getDataSource(request,"dataSources");//取struts配置的数据源
		HttpSession session=request.getSession();
		List listallrecord=(List)r.getAllRecord(datasource);
		session.setAttribute("listallrecord", listallrecord);
		return mapping.getInputForward();
	}
}
