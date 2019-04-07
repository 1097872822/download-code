package com.lzw.model;

public class VTypeId implements java.io.Serializable {
	private Integer id;

	private String superType;

	private String subType;

	private Integer subId;

	public VTypeId() {
	}

	public VTypeId(Integer id, String superType, String subType, Integer subId) {
		this.id = id;
		this.superType = superType;
		this.subType = subType;
		this.subId = subId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSuperType() {
		return this.superType;
	}

	public void setSuperType(String superType) {
		this.superType = superType;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public Integer getSubId() {
		return this.subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VTypeId))
			return false;
		VTypeId castOther = (VTypeId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getSuperType() == castOther.getSuperType()) || (this
						.getSuperType() != null
						&& castOther.getSuperType() != null && this
						.getSuperType().equals(castOther.getSuperType())))
				&& ((this.getSubType() == castOther.getSubType()) || (this
						.getSubType() != null
						&& castOther.getSubType() != null && this.getSubType()
						.equals(castOther.getSubType())))
				&& ((this.getSubId() == castOther.getSubId()) || (this
						.getSubId() != null
						&& castOther.getSubId() != null && this.getSubId()
						.equals(castOther.getSubId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getSuperType() == null ? 0 : this.getSuperType().hashCode());
		result = 37 * result
				+ (getSubType() == null ? 0 : this.getSubType().hashCode());
		result = 37 * result
				+ (getSubId() == null ? 0 : this.getSubId().hashCode());
		return result;
	}

}