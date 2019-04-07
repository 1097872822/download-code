package com.mwq.hibernate.mapping;

import java.util.HashSet;
import java.util.Set;

public class TbDept implements java.io.Serializable {

	// Fields

	private Integer id;

	private String name;

	private TbDept tbDept;

	private Set tbDepts = new HashSet(0);

	private Set tbDutyInfos = new HashSet(0);

	// Constructors

	public TbDept() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getTbDepts() {
		return this.tbDepts;
	}

	public void setTbDepts(Set tbDepts) {
		this.tbDepts = tbDepts;
	}

	public TbDept getTbDept() {
		return this.tbDept;
	}

	public void setTbDept(TbDept tbDept) {
		this.tbDept = tbDept;
	}

	public Set getTbDutyInfos() {
		return this.tbDutyInfos;
	}

	public void setTbDutyInfos(Set tbDutyInfos) {
		this.tbDutyInfos = tbDutyInfos;
	}

}