package com.mwq.hibernate.mapping;

import java.util.Date;

public class TbRewardsAndPunishment implements java.io.Serializable {

	// Fields

	private Integer id;

	private TbDept tbDept;

	private TbRecord tbRecordByRecordId;

	private TbRecord tbRecordByRatifierRecordId;

	private String type;

	private String reason;

	private String content;

	private Integer money;

	private Date startDate;

	private Date endDate;

	private Date ratifierDate;

	// Constructors

	public TbRewardsAndPunishment() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbDept getTbDept() {
		return this.tbDept;
	}

	public void setTbDept(TbDept tbDept) {
		this.tbDept = tbDept;
	}

	public TbRecord getTbRecordByRecordId() {
		return this.tbRecordByRecordId;
	}

	public void setTbRecordByRecordId(TbRecord tbRecordByRecordId) {
		this.tbRecordByRecordId = tbRecordByRecordId;
	}

	public TbRecord getTbRecordByRatifierRecordId() {
		return this.tbRecordByRatifierRecordId;
	}

	public void setTbRecordByRatifierRecordId(
			TbRecord tbRecordByRatifierRecordId) {
		this.tbRecordByRatifierRecordId = tbRecordByRatifierRecordId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMoney() {
		return this.money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getRatifierDate() {
		return this.ratifierDate;
	}

	public void setRatifierDate(Date ratifierDate) {
		this.ratifierDate = ratifierDate;
	}

}