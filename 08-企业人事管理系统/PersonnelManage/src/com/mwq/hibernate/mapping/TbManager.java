package com.mwq.hibernate.mapping;

public class TbManager implements java.io.Serializable {

	// Fields

	private Integer id;

	private TbRecord tbRecord;

	private String password;

	private String purview;

	private String state;

	// Constructors

	public TbManager() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbRecord getTbRecord() {
		return this.tbRecord;
	}

	public void setTbRecord(TbRecord tbRecord) {
		this.tbRecord = tbRecord;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPurview() {
		return purview;
	}

	public void setPurview(String purview) {
		this.purview = purview;
	}

}