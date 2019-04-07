package com.lzw.tiles;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.tiles.ComponentContext;
import org.springframework.web.servlet.view.tiles.ComponentControllerSupport;

import com.lzw.dao.Dao;
import com.lzw.model.TbMember;
public class ModifyMemberTilesController extends ComponentControllerSupport {
	protected void doPerform(ComponentContext componentContext,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Dao dao = (Dao) getApplicationContext().getBean("daoProxyFactory");
		TbMember user = null;
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (username != null) {
			user = dao.getUser(username, null);
		}
		componentContext.putAttribute("user", user);
	}
}
