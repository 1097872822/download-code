package com.mwq.hibernate.mapping;

import java.util.Date;

public class TbPersonalInfo implements java.io.Serializable {

	// Fields

	private Integer id;

	private String qq;

	private String EMail;

	private String handset;

	private String telephone;

	private String address;

	private String postalcode;

	private String secondSchoolAge;

	private String secondSpecialty;

	private String graduateSchool;

	private Date graduateDate;

	private Date partyMemberDate;

	private String computerGrade;

	private String likes;

	private String onesStrongSuit;

	private TbRecord tbRecord;

	// Constructors

	public TbPersonalInfo() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}

	public String getHandset() {
		return this.handset;
	}

	public void setHandset(String handset) {
		this.handset = handset;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getSecondSchoolAge() {
		return this.secondSchoolAge;
	}

	public void setSecondSchoolAge(String secondSchoolAge) {
		this.secondSchoolAge = secondSchoolAge;
	}

	public String getSecondSpecialty() {
		return this.secondSpecialty;
	}

	public void setSecondSpecialty(String secondSpecialty) {
		this.secondSpecialty = secondSpecialty;
	}

	public String getGraduateSchool() {
		return this.graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public Date getGraduateDate() {
		return this.graduateDate;
	}

	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}

	public Date getPartyMemberDate() {
		return this.partyMemberDate;
	}

	public void setPartyMemberDate(Date partyMemberDate) {
		this.partyMemberDate = partyMemberDate;
	}

	public String getComputerGrade() {
		return this.computerGrade;
	}

	public void setComputerGrade(String computerGrade) {
		this.computerGrade = computerGrade;
	}

	public String getLikes() {
		return this.likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getOnesStrongSuit() {
		return this.onesStrongSuit;
	}

	public void setOnesStrongSuit(String onesStrongSuit) {
		this.onesStrongSuit = onesStrongSuit;
	}

	public TbRecord getTbRecord() {
		return tbRecord;
	}

	public void setTbRecord(TbRecord tbRecord) {
		this.tbRecord = tbRecord;
	}

}