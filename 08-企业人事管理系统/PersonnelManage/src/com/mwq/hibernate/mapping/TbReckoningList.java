package com.mwq.hibernate.mapping;

public class TbReckoningList implements java.io.Serializable {

	// Fields

	private Integer recordId;

	private TbReckoning tbReckoning;

	private TbRecord tbRecord;

	// Constructors

	public TbReckoningList() {
	}

	// Property accessors

	public TbReckoning getTbReckoning() {
		return this.tbReckoning;
	}

	public void setTbReckoning(TbReckoning tbReckoning) {
		this.tbReckoning = tbReckoning;
	}

	public TbRecord getTbRecord() {
		return this.tbRecord;
	}

	public void setTbRecord(TbRecord tbRecord) {
		this.tbRecord = tbRecord;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

}