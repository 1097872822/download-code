package com.lzw;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import com.lzw.dao.Dao;
import com.lzw.form.ModifyMemberForm;
public class ModifyMemberController extends SimpleFormController {
	private Dao dao;
	public ModifyMemberController(){
		setCommandClass(ModifyMemberForm.class);
	}
	protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
		ModifyMemberForm form=(ModifyMemberForm) command;
		dao.updateUserInfo(form);
		return new ModelAndView(new RedirectView(""));
	}
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
