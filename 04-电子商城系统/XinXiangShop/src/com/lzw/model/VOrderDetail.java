package com.lzw.model;

public class VOrderDetail implements java.io.Serializable {
	private VOrderDetailId id;

	public VOrderDetail() {
	}

	public VOrderDetail(VOrderDetailId id) {
		this.id = id;
	}

	public VOrderDetailId getId() {
		return this.id;
	}

	public void setId(VOrderDetailId id) {
		this.id = id;
	}

}