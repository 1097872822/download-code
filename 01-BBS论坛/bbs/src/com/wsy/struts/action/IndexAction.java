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

import com.wsy.struts.bean.ForumBean;

public class IndexAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionMessages message=new ActionMessages();
		DataSource datasource = getDataSource(request,"dataSources");//取struts配置的数据源
		HttpSession session=request.getSession();
		ForumBean f=new ForumBean();
		List list=f.getRecord(datasource);//取出论坛信息表中全部内容
		int ztcount=f.getCount(datasource);//取出主题总数
		int ztAndResponseCount=f.getZtAndResponseCount(datasource);//取总共帖子数
		int todaycount=f.getTodayResponseCount(datasource);
		session.setAttribute("list", list);
		session.setAttribute("ztcount", ztcount+"");
		session.setAttribute("ztAndResponseCount", ztAndResponseCount+"");
		session.setAttribute("todaycount", todaycount+"");
		return mapping.getInputForward();
	
	
	}
	
	
}
