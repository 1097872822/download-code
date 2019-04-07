package com.lzw;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.lzw.dao.Dao;
import com.lzw.form.UserLoginForm;
import com.lzw.model.TbMember;
public class LoginController extends SimpleFormController {
	private Dao dao;
	public LoginController(){
		setCommandClass(UserLoginForm.class);
	}
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		HttpSession session = request.getSession();
		UserLoginForm form = (UserLoginForm) command;
		TbMember user = dao.getUser(form.getUsername(), form.getPassword());
		if(user!=null){
			session.removeAttribute("manager");
			session.setAttribute("username", form.getUsername());
			session.setAttribute("userTrueName", user.getTrueName());
			return new ModelAndView(new RedirectView("index.lzw"));
		}else{
			return new ModelAndView(new RedirectView("index.lzw"),"loger","error");
		}
	}
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
		HttpSession session=request.getSession();
		String loginOut=request.getParameter("loginOut");
		if(loginOut!=null&&loginOut.equalsIgnoreCase("true")){
			session.invalidate();
		}
		return new ModelAndView(new RedirectView("index.lzw"));
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
