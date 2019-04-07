package com.lzw;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.lzw.dao.Dao;
import com.lzw.form.CommandForm;
import com.lzw.model.TbGoods;
public class GoodsDetailsController extends AbstractCommandController {
	private Dao dao;
	private String view;
	public GoodsDetailsController(){
		setCommandClass(CommandForm.class);
	}
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		CommandForm form=(CommandForm) command;
		TbGoods goods=null;
		if(form.getId()!=null&&!form.getId().equals(""))
			goods=dao.getGoods((long)form.getId());
		return new ModelAndView(getView(),"goods",goods);
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
}
