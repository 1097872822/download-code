package com.lzw.manager;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.lzw.dao.Dao;
import com.lzw.manager.form.SuperTypeForm;

public class SubTypeController extends SimpleFormController {
	private Dao dao;
	public SubTypeController() {
		setCommandClass(SuperTypeForm.class);
	}
	protected ModelAndView onSubmit(Object command) throws Exception {
		SuperTypeForm form = (SuperTypeForm) command;
		String[] delids = form.getDelid();
		String message = null;
		if (delids.length > 0) {
			String subTypeID = "";
			for (int i = 0; i < delids.length; i++) {
				subTypeID = subTypeID + delids[i] + ",";
			}
			subTypeID = subTypeID.substring(0, subTypeID.length() - 1);
			if (!dao.delSubType(subTypeID)) {
				message = "С������Ϣɾ��ʧ��!";
			} else {
				message = "С������Ϣɾ���ɹ�!";
			}
		} else {
			message = "���Ĳ�������!";
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
