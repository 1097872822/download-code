package com.lzw.model;

public class TbManager implements java.io.Serializable {
	private Integer id;

	private String manager;

	private String pwd;

	public TbManager() {
	}

	public TbManager(Integer id, String manager, String pwd) {
		this.id = id;
		this.manager = manager;
		this.pwd = pwd;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}