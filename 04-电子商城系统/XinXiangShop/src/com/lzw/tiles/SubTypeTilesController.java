package com.lzw.tiles;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.tiles.ComponentControllerSupport;

import com.lzw.dao.Dao;
import com.lzw.manager.form.SuperTypeForm;

public class SubTypeTilesController extends ComponentControllerSupport {
	private Dao dao;
	protected void doPerform(ComponentContext componentContext, HttpServletRequest request, HttpServletResponse response) throws Exception {
		dao=(Dao) getApplicationContext().getBean("daoProxyFactory");
		List list=dao.getSubTypes();
		componentContext.putAttribute("list", list);
	}
}
