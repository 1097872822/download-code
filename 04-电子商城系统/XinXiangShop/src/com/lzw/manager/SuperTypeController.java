package com.lzw.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.lzw.dao.Dao;
import com.lzw.manager.form.SuperTypeForm;

public class SuperTypeController extends AbstractFormController {
	private Dao dao;
	public SuperTypeController() {
		setCommandClass(SuperTypeForm.class);
	}
	protected ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		SuperTypeForm form=(SuperTypeForm) command;
		String[] delids=form.getDelid();
		String message=null;
		if (delids.length>0){
			String superTypeID="";
			for(int i=0;i<delids.length;i++){
				superTypeID=superTypeID+delids[i]+",";
			}
			superTypeID=superTypeID.substring(0,superTypeID.length()-1);
			if(!dao.delSuperType(superTypeID)){
				message="false";
			}else{
				message="true";
			}
		}else{
			message="no";
		}
		return new ModelAndView(new RedirectView("superType.lzw"),"message",message);
	}

	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception {
		List list=dao.getSuperTypes();
		return new ModelAndView("superType","list",list);
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

}
