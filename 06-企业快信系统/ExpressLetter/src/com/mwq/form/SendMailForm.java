package com.mwq.form;


public class SendMailForm {
	private String content;
	private String title;
	private String addressee;
	private String adjunct;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddressee() {
		return addressee;
	}
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getAdjunct() {
		return adjunct;
	}
	public void setAdjunct(String adjunct) {
		this.adjunct = adjunct;
	}
}