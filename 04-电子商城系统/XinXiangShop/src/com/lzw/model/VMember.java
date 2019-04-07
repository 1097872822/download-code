package com.lzw.model;

public class VMember implements java.io.Serializable {
	private VMemberId id;

	public VMember() {
	}

	public VMember(VMemberId id) {
		this.id = id;
	}

	public VMemberId getId() {
		return this.id;
	}

	public void setId(VMemberId id) {
		this.id = id;
	}
}