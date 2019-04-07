package com.lzw.model;

public class VType implements java.io.Serializable {
	private VTypeId id;

	public VType() {
	}

	public VType(VTypeId id) {
		this.id = id;
	}

	public VTypeId getId() {
		return this.id;
	}

	public void setId(VTypeId id) {
		this.id = id;
	}

}