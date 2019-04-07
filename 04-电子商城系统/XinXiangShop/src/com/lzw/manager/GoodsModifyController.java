package com.lzw.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.lzw.dao.Dao;
import com.lzw.model.TbGoods;
import com.lzw.model.TbSubType;

public class GoodsModifyController extends SimpleFormController {
	private Dao dao;
	private Date inDate=null;
	public GoodsModifyController() {
		setCommandClass(TbGoods.class);
	}
	protected void doSubmitAction(Object command) throws Exception {
		TbGoods form=(TbGoods) command;
		form.setIntime(inDate);
		inDate=null;
		dao.insertOrUpdate(form);
		super.doSubmitAction(command);
	}
	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception {
		String ids = request.getParameter("id");
		String superSelect = request.getParameter("superSelect");
		Map model = new HashMap();
		Long id = Long.valueOf(ids);
		TbGoods goods = dao.getGoods(id);
		inDate=goods.getIntime();
		TbSubType subType = dao.getSubType(goods.getTypeId());
		model.put("goods", goods);
		if (superSelect != null)
			model.put("superSelect", superSelect);
		else
			model.put("superTypeName", subType.getTbSuperType().getTypeName());
		return new ModelAndView(getFormView(), model);
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
