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
			errors.rejectValue("username", null, "�������û���");
		}
		if (form.getTruename().trim().equals("")) {
			errors.rejectValue("truename", null, "��������ʵ����");
		}
		if (form.getPwd().equals("")) {
			errors.rejectValue("pwd", null, "����������");
		}
		if (form.getPwd1().equals("")) {
			errors.rejectValue("pwd1", null, "����������");
		}
		if (form.getOldpwd() != null && form.getOldpwd().trim().equals("")) {
			errors.rejectValue("oldpwd", null, "������ԭ����");
		}
		if (form.getCardno().trim().equals("")) {
			errors.rejectValue("cardno", null, "������֤������");
		} else {
			String str = "1234567890";
			byte[] bytes = form.getCardno().trim().getBytes();
			for (byte ch : bytes) {
				if (str.indexOf((char) ch) < 0) {
					errors.rejectValue("cardno", null, "�����ָ�ʽ����֤������");
					break;
				}
			}

		}
		if (form.getEmail().trim().equals("")) {
			errors.rejectValue("email", null, "������Email");
		} else if (form.getEmail().indexOf("@") < 3
				|| form.getEmail().indexOf(".") == -1) {
			errors.rejectValue("email", null, "��������ȷ��Email��ʽ");
		}
		if (!form.getPwd().equals(form.getPwd1())) {
			errors.rejectValue("pwd", "ddd", "�������벻һ�£����������롣");
			errors.rejectValue("pwd1", "ddd", "�������벻һ�£����������롣");
		}
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}
}
