package com.lzw;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.lzw.dao.Dao;
import com.lzw.form.CommandForm;
public class TypeController extends AbstractCommandController {
	private Dao dao;
	public TypeController(){
		setCommandClass(CommandForm.class);
	}
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		CommandForm form=(CommandForm) command;
		List list=null;
		if(form.getId()!=null&&!form.getId().equals(""))
			list=dao.getGoodsByType(form.getId());
		return new ModelAndView("typeInfo","typeInfo",list);
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
}