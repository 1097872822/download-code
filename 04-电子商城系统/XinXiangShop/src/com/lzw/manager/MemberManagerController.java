package com.lzw.manager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.lzw.dao.Dao;
import com.lzw.model.TbMember;
public class MemberManagerController extends MultiActionController {
	private Dao dao;
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("memManage");
	}
	public ModelAndView detail(HttpServletRequest request,
			HttpServletResponse response) {
		String id=request.getParameter("id");
		TbMember member=null;
		if(id!=null&&!id.equals("")){
			Integer ID=Integer.valueOf(id);
			member = dao.getMember(ID);
		}
		return new ModelAndView("memDetails","member",member);
	}
	// ¶³½á
	public ModelAndView freeze(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		String action = (String) request.getAttribute("action");
		if (id != null && !id.equals("")) {
			int ID = Integer.parseInt(id);
			if (action == null)
				dao.updateFreeze(ID);
			else if (action.equals("thaw"))
				dao.updateThaw(ID);
		}
		return new ModelAndView("redirect:memberManage.lzw");
	}
	// ½â¶³
	public ModelAndView thaw(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("action", "thaw");
		return freeze(request,response);
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
