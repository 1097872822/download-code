package com.lzw.manager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import com.lzw.dao.Dao;
import com.lzw.model.TbGoods;
import com.lzw.model.VGoods;
public class GoodsDelController extends AbstractFormController {
	private Dao dao;
	public GoodsDelController() {
		setCommandClass(TbGoods.class);
	}
	protected ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		TbGoods form = (TbGoods) command;
		form = dao.getGoods(form.getId());
		dao.delObject(form);
		return new ModelAndView("redirect:indexM.lzw");
	}
	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception {
		String id = request.getParameter("id");
		VGoods goods = null;
		if (id != null)
			goods = dao.getVGoods(Long.valueOf(id));
		return new ModelAndView("goodsDel", "goods", goods);
	}
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
