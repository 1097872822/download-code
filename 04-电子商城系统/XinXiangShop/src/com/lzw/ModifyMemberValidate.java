package com.lzw;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lzw.dao.Dao;
import com.lzw.form.ModifyMemberForm;

public class ModifyMemberValidate implements Validator {
	private Dao dao;
	public boolean supports(Class clazz) {
		return clazz.equals(ModifyMemberForm.class);
	}

	public void validate(Object target, Errors errors) {
		ModifyMemberForm form = (ModifyMemberForm) target;
		if (form.getUsername().trim().equals("")) {
			errors.rejectValue("username", null, "请输入用户名");
		}
		if (form.getTruename().trim().equals("")) {
			errors.rejectValue("truename", null, "请输入真实姓名");
		}
		if (form.getPwd().equals("")) {
			errors.rejectValue("pwd", null, "请输入密码");
		}
		if (form.getPwd1().equals("")) {
			errors.rejectValue("pwd1", null, "请输入密码");
		}
		if (form.getOldpwd() != null && form.getOldpwd().trim().equals("")) {
			errors.rejectValue("oldpwd", null, "请输入原密码");
		}
		if (form.getCardno().trim().equals("")) {
			errors.rejectValue("cardno", null, "请输入证件号码");
		} else {
			String str = "1234567890";
			byte[] bytes = form.getCardno().trim().getBytes();
			for (byte ch : bytes) {
				if (str.indexOf((char) ch) < 0) {
					errors.rejectValue("cardno", null, "以数字格式输入证件号码");
					break;
				}
			}

		}
		if (form.getEmail().trim().equals("")) {
			errors.rejectValue("email", null, "请输入Email");
		} else if (form.getEmail().indexOf("@") < 3
				|| form.getEmail().indexOf(".") == -1) {
			errors.rejectValue("email", null, "请输入正确的Email格式");
		}
		if (!form.getPwd().equals(form.getPwd1())) {
			errors.rejectValue("pwd", "ddd", "两次密码不一致，请重新输入。");
			errors.rejectValue("pwd1", "ddd", "两次密码不一致，请重新输入。");
		}
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
