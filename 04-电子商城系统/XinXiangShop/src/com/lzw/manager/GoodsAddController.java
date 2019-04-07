package com.lzw.manager;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.lzw.dao.Dao;
import com.lzw.model.TbGoods;
public class GoodsAddController extends SimpleFormController {
	private Dao dao;
	public GoodsAddController() {
		setCommandClass(TbGoods.class);
	}
	protected ModelAndView onSubmit(Object command) throws Exception {
		TbGoods form=(TbGoods) command;
		form.setIntime(new Date());
		form.setNowPrice(form.getPrice());
		dao.insertObject(form);
		return super.onSubmit(null);
	}
	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors, Map controlModel)
			throws Exception {
		String superSelect=request.getParameter("superSelect");
		return new ModelAndView(getFormView(),"superSelect",superSelect);
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
