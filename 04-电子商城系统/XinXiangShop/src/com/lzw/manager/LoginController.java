package com.lzw.manager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.lzw.dao.Dao;
import com.lzw.manager.form.LoginForm;
import com.lzw.model.TbManager;
public class LoginController extends SimpleFormController {
	private Dao dao;
	public LoginController() {
		setCommandClass(LoginForm.class);
	}
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
		LoginForm form = (LoginForm) command;
		TbManager manager = dao.getManager(form.getManager());
		if (manager != null && manager.getPwd().equals(form.getPWD())) {
			request.getSession().removeAttribute("username");
			request.getSession().setAttribute("manager", manager);
		}
		return new ModelAndView(getSuccessView());
	}
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		String action=request.getParameter("action");
		if(action!=null&&action.equals("logout")){
			request.getSession().invalidate();
			return new ModelAndView(new RedirectView("index.jsp"));
		}
		return super.showForm(request, response, errors);
	}
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("gbk");
		return super.handleRequestInternal(request, response);
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
