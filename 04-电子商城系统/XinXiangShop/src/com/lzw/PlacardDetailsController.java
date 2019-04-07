package com.lzw;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.lzw.dao.Dao;
import com.lzw.form.CommandForm;
import com.lzw.model.TbBbs;
public class PlacardDetailsController extends AbstractCommandController {
	private Dao dao;
	public PlacardDetailsController(){
		setCommandClass(CommandForm.class);
	}
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		CommandForm form=(CommandForm) command;
		TbBbs placard=null;
		if(form.getId()!=null&&!form.getId().equals(""))
			placard=dao.getPlacard(form.getId());
		return new ModelAndView("placardDetails","placard",placard);
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
