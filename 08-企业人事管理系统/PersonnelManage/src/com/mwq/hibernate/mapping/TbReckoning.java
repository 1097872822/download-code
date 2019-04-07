package com.mwq.hibernate.mapping;

import java.util.HashSet;
import java.util.Set;

public class TbReckoning implements java.io.Serializable {

	// Fields

	private Integer id;

	private String name;

	private String explain;

	private Set tbReckoningInfos = new HashSet(0);

	private Set tbReckoningLists = new HashSet(0);

	// Constructors

	public TbReckoning() {
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

	public String getExplain() {
		return this.explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Set getTbReckoningInfos() {
		return this.tbReckoningInfos;
	}

	public void setTbReckoningInfos(Set tbReckoningInfos) {
		this.tbReckoningInfos = tbReckoningInfos;
	}

	public Set getTbReckoningLists() {
		return this.tbReckoningLists;
	}

	public void setTbReckoningLists(Set tbReckoningLists) {
		this.tbReckoningLists = tbReckoningLists;
	}

}