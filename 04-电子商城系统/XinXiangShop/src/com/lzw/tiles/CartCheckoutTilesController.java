package com.lzw.tiles;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.tiles.ComponentContext;
import org.springframework.web.servlet.view.tiles.ComponentControllerSupport;

import com.lzw.dao.Dao;
import com.lzw.model.TbMember;
public class CartCheckoutTilesController extends ComponentControllerSupport {
	private Dao dao;
	protected void doPerform(ComponentContext componentContext,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		dao = (Dao) getApplicationContext().getBean("daoProxyFactory");
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("username");
		if (userName != null && !userName.equals("")) {
			TbMember user = dao.getUser(userName, null);
			componentContext.putAttribute("userInfo", user);
		}
	}
}
