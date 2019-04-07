package com.lzw.manager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lzw.dao.Dao;
import com.lzw.model.TbOrder;
public class OrderManagerController extends MultiActionController {
	private Dao dao;
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("orderManage");
	}
	public ModelAndView detail(HttpServletRequest request,
			HttpServletResponse response) {
		String id=request.getParameter("id");
		TbOrder order=null;
		if(id!=null&&!id.equals("")){
			Long ID=Long.valueOf(id);
			order = dao.getOrder(ID);
		}
		return new ModelAndView("orderMDetails","order",order);
	}
	// ¶³½á
	public ModelAndView enforce(HttpServletRequest request,
			HttpServletResponse response) {
		if(request.getParameter("id")!=""){
			int ID=Integer.parseInt(request.getParameter("id"));
			dao.updateEnforce(ID);
		}
		return new ModelAndView("redirect:orderManage.lzw");
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
