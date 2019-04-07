package com.lzw;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.lzw.dao.Dao;
import com.lzw.form.ModifyMemberForm;
import com.lzw.model.TbMember;
public class RegisterController extends SimpleFormController {
	private Dao dao;
	public RegisterController() {
		setCommandClass(ModifyMemberForm.class);
	}
	protected ModelAndView onSubmit(Object command, BindException errors)
			throws Exception {
		ModifyMemberForm form = (ModifyMemberForm) command;
		TbMember user = new TbMember();
		user.setAddress(form.getAddress());
		user.setCardNo(form.getCardno());
		user.setCardType(form.getCardtype());
		user.setCity(form.getCity());
		user.setEmail(form.getEmail());
		user.setPassWord(form.getPwd());
		user.setPostcode(form.getPostcode());
		user.setTel(form.getTel());
		user.setTrueName(form.getTruename());
		user.setUserName(form.getUsername());
		user.setFreeze(0);
		dao.insertObject(user);
		return new ModelAndView("register", "info", "注册成功。");
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
	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		ModifyMemberForm form = (ModifyMemberForm) command;
		TbMember user = dao.getUser(form.getUsername(), null);
		if (user != null) {
			errors.rejectValue("username", null, "用户以存在。");
		}
	}
}