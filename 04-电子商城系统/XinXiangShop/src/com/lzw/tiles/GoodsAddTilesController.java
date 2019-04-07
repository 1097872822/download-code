package com.lzw.tiles;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.springframework.web.servlet.view.tiles.ComponentControllerSupport;

import com.lzw.dao.Dao;

public class GoodsAddTilesController extends ComponentControllerSupport {
	private Dao dao;
	protected void doPerform(ComponentContext componentContext,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		dao = (Dao) getApplicationContext().getBean("daoProxyFactory");
		Map typeList = dao.getTypeList();
		componentContext.putAttribute("typeList", typeList);
		componentContext.putAttribute("typeNameList", typeList.keySet().toArray());
	}
}
