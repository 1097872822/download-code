package com.lzw.manager;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.lzw.dao.Dao;
import com.lzw.manager.form.SuperTypeForm;

public class PlacardManageController extends SimpleFormController {
	private Dao dao;
	public PlacardManageController() {
		setCommandClass(SuperTypeForm.class);
	}
	protected ModelAndView onSubmit(Object command) throws Exception {
		SuperTypeForm form = (SuperTypeForm) command;
		String[] delids = form.getDelid();
		String message = null;
		if (delids.length > 0) {
			String ID = "";
			for (int i = 0; i < delids.length; i++) {
				ID = ID + delids[i] + ",";
			}
			ID = ID.substring(0, ID.length() - 1);
			if (!dao.delPlacard(ID)) {
				message = "É¾³ýÊ§°Ü!";
			} else {
				message = "É¾³ý³É¹¦!";
			}
		} else {
			message = "ÄúµÄ²Ù×÷ÓÐÎó!";
		}
		return new ModelAndView(getSuccessView(), "message", message);
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}

}
