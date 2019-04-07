package com.mwq.hibernate.mapping;

public class TbBringUpOntent implements java.io.Serializable {

	// Fields

	private Integer id;

	private TbBringUpContent tbBringUpContent;

	private TbRecord tbRecord;


	// Constructors

	public TbBringUpOntent() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbBringUpContent getTbBringUpContent() {
		return this.tbBringUpContent;
	}

	public void setTbBringUpContent(TbBringUpContent tbBringUpContent) {
		this.tbBringUpContent = tbBringUpContent;
	}

	public TbRecord getTbRecord() {
		return this.tbRecord;
	}

	public void setTbRecord(TbRecord tbRecord) {
		this.tbRecord = tbRecord;
	}

}