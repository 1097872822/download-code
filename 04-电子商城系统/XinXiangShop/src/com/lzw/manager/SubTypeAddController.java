package com.lzw.manager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.lzw.dao.Dao;
import com.lzw.manager.form.SubTypeAddForm;
import com.lzw.model.TbSubType;
import com.lzw.model.TbSuperType;
public class SubTypeAddController extends SimpleFormController {
	private Dao dao;
	public SubTypeAddController() {
		setCommandClass(SubTypeAddForm.class);
	}
	protected ModelAndView onSubmit(Object command) throws Exception {
		SubTypeAddForm form = (SubTypeAddForm) command;
		String message = null;
		TbSubType tst = dao.getSubType(form.getTypename());
		if (tst != null) {
			message = "该小分类信息已经添加，请添加新的大分类信息！";
			return new ModelAndView(getFormView(), "message", message);
		}
		tst = new TbSubType();
		tst.setTypeName(form.getTypename());
		tst.setTbSuperType(dao.getSuperType(Integer.valueOf(form.getSuperid())));
		if(dao.insertObject(tst))
			message="小分类信息添加成功！";
		else
			message="小分类信息添加失败！";
		return new ModelAndView(getSuccessView(),"message",message);
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
