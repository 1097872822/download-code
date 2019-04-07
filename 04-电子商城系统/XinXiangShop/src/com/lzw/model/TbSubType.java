package com.lzw.model;

public class TbSubType implements java.io.Serializable {
	private Integer id;

	private TbSuperType tbSuperType;

	private String typeName;

	public TbSubType() {
	}

	public TbSubType(Integer id, TbSuperType tbSuperType, String typeName) {
		this.id = id;
		this.tbSuperType = tbSuperType;
		this.typeName = typeName;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbSuperType getTbSuperType() {
		return this.tbSuperType;
	}

	public void setTbSuperType(TbSuperType tbSuperType) {
		this.tbSuperType = tbSuperType;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}