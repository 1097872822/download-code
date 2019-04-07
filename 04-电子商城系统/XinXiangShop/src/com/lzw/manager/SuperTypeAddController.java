package com.lzw.manager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.lzw.dao.Dao;
import com.lzw.manager.form.SuperTypeAddForm;
import com.lzw.model.TbSuperType;
public class SuperTypeAddController extends SimpleFormController {
	private Dao dao;
	public SuperTypeAddController() {
		setCommandClass(SuperTypeAddForm.class);
	}
	protected ModelAndView onSubmit(Object command) throws Exception {
		SuperTypeAddForm form = (SuperTypeAddForm) command;
		String message = null;
		TbSuperType tst = dao.getSuperType(form.getTypename());
		if (tst != null) {
			message = "该大分类信息已经添加，请添加新的大分类信息！";
			return new ModelAndView(getFormView(), "message", message);
		}
		tst = new TbSuperType();
		tst.setTypeName(form.getTypename());
		dao.insertObject(tst);
		return new ModelAndView(getSuccessView());
	}
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		arg0.setCharacterEncoding("GBK");
		return super.handleRequestInternal(arg0, arg1);
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
