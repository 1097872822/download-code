package com.lzw.tiles;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.springframework.web.servlet.view.tiles.ComponentControllerSupport;

import com.lzw.dao.Dao;

public class SubTypeAddTilesController extends ComponentControllerSupport {
	private Dao dao;
	protected void doPerform(ComponentContext componentContext,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		dao=(Dao) getApplicationContext().getBean("daoProxyFactory");
		List list=dao.getSuperTypes();
		componentContext.putAttribute("superTypes", list);
	}
}
