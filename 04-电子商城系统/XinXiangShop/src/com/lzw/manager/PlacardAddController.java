package com.lzw.manager;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.lzw.dao.Dao;
import com.lzw.manager.form.SuperTypeAddForm;
import com.lzw.model.TbBbs;
import com.lzw.model.TbSuperType;
public class PlacardAddController extends SimpleFormController {
	private Dao dao;
	public PlacardAddController() {
		setCommandClass(TbBbs.class);
	}
	protected ModelAndView onSubmit(Object command) throws Exception {
		TbBbs form = (TbBbs) command;
		System.out.println(getFormView());
		System.out.println(getSuccessView());
		
		String message = null;
		TbBbs tbb = dao.getBBS(form.getTitle());
		if (tbb != null) {
			message = "该公告信息已经添加，请添加新的公告信息！";
			return new ModelAndView(getFormView(), "message", message);
		}
		tbb = new TbBbs();
		tbb.setTitle(form.getTitle());
		tbb.setContent(form.getContent());
		tbb.setIntime(new Date());
		dao.insertObject(tbb);
		return new ModelAndView(getSuccessView());
	}
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("GBK");
		return super.handleRequestInternal(request, response);
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
