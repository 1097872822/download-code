package com.mwq.hibernate.mapping;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TbRecord implements java.io.Serializable {

	// Fields

	private int id;

	private String recordNumber;

	private TbNation tbNation;

	private TbNativePlace tbNativePlace;

	private String name;

	private String sex;

	private Date birthday;

	private String photo;

	private String idCard;

	private String marriaged;

	private String address;

	private String postalcode;

	private String partyMember;

	private String schoolAge;

	private String specialty;

	private String foreignLanguage;

	private String grade;

	private TbPersonalInfo tbPersonalInfo;

	private TbDutyInfo tbDutyInfo;

	private TbReckoningList tbReckoningList;

	private TbManager tbManager;

	private Set tbBringUpOntents = new HashSet(0);

	private Set tbTimecardsForRecordId = new HashSet(0);

	private Set tbRewardsAndPunishmentsForRecordId = new HashSet(0);

	// private Set tbRewardsAndPunishmentsForRatifierRecordId = new HashSet(0);

	// private Set tbReckoningLists = new HashSet(0);

	// private Set tbManagers = new HashSet(0);
	//
	//
	// private Set tbRedeploiesForRatifierRecordId = new HashSet(0);
	//
	// private Set tbTimecardsForRatifierRecordId = new HashSet(0);
	//
	// private Set tbRedeploiesForRecordId = new HashSet(0);

	// Constructors

	public TbRecord() {
	}

	// Property accessors

	public TbNation getTbNation() {
		return this.tbNation;
	}

	public void setTbNation(TbNation tbNation) {
		this.tbNation = tbNation;
	}

	public TbNativePlace getTbNativePlace() {
		return this.tbNativePlace;
	}

	public void setTbNativePlace(TbNativePlace tbNativePlace) {
		this.tbNativePlace = tbNativePlace;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMarriaged() {
		return this.marriaged;
	}

	public void setMarriaged(String marriaged) {
		this.marriaged = marriaged;
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

	public String getPartyMember() {
		return this.partyMember;
	}

	public void setPartyMember(String partyMember) {
		this.partyMember = partyMember;
	}

	public String getSchoolAge() {
		return this.schoolAge;
	}

	public void setSchoolAge(String schoolAge) {
		this.schoolAge = schoolAge;
	}

	public String getSpecialty() {
		return this.specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getForeignLanguage() {
		return this.foreignLanguage;
	}

	public void setForeignLanguage(String foreignLanguage) {
		this.foreignLanguage = foreignLanguage;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Set getTbRewardsAndPunishmentsForRecordId() {
		return this.tbRewardsAndPunishmentsForRecordId;
	}

	public void setTbRewardsAndPunishmentsForRecordId(
			Set tbRewardsAndPunishmentsForRecordId) {
		this.tbRewardsAndPunishmentsForRecordId = tbRewardsAndPunishmentsForRecordId;
	}

	// public Set getTbRewardsAndPunishmentsForRatifierRecordId() {
	// return this.tbRewardsAndPunishmentsForRatifierRecordId;
	// }
	//
	// public void setTbRewardsAndPunishmentsForRatifierRecordId(
	// Set tbRewardsAndPunishmentsForRatifierRecordId) {
	// this.tbRewardsAndPunishmentsForRatifierRecordId =
	// tbRewardsAndPunishmentsForRatifierRecordId;
	// }
	//
	// public Set getTbManagers() {
	// return this.tbManagers;
	// }
	//
	// public void setTbManagers(Set tbManagers) {
	// this.tbManagers = tbManagers;
	// }
	//

	//
	// public Set getTbRedeploiesForRatifierRecordId() {
	// return this.tbRedeploiesForRatifierRecordId;
	// }
	//
	// public void setTbRedeploiesForRatifierRecordId(
	// Set tbRedeploiesForRatifierRecordId) {
	// this.tbRedeploiesForRatifierRecordId = tbRedeploiesForRatifierRecordId;
	// }
	//
	//
	// public Set getTbTimecardsForRatifierRecordId() {
	// return this.tbTimecardsForRatifierRecordId;
	// }
	//
	// public void setTbTimecardsForRatifierRecordId(
	// Set tbTimecardsForRatifierRecordId) {
	// this.tbTimecardsForRatifierRecordId = tbTimecardsForRatifierRecordId;
	// }
	//
	// public Set getTbReckoningLists() {
	// return this.tbReckoningLists;
	// }
	//
	// public void setTbReckoningLists(Set tbReckoningLists) {
	// this.tbReckoningLists = tbReckoningLists;
	// }

	public Set getTbBringUpOntents() {
		return this.tbBringUpOntents;
	}

	public void setTbBringUpOntents(Set tbBringUpOntents) {
		this.tbBringUpOntents = tbBringUpOntents;
	}

	public Set getTbTimecardsForRecordId() {
		return this.tbTimecardsForRecordId;
	}

	public void setTbTimecardsForRecordId(Set tbTimecardsForRecordId) {
		this.tbTimecardsForRecordId = tbTimecardsForRecordId;
	}

	//
	// public Set getTbRedeploiesForRecordId() {
	// return this.tbRedeploiesForRecordId;
	// }
	//
	// public void setTbRedeploiesForRecordId(Set tbRedeploiesForRecordId) {
	// this.tbRedeploiesForRecordId = tbRedeploiesForRecordId;
	// }

	public TbDutyInfo getTbDutyInfo() {
		return tbDutyInfo;
	}

	public void setTbDutyInfo(TbDutyInfo tbDutyInfo) {
		this.tbDutyInfo = tbDutyInfo;
	}

	public TbPersonalInfo getTbPersonalInfo() {
		return tbPersonalInfo;
	}

	public void setTbPersonalInfo(TbPersonalInfo tbPersonalInfo) {
		this.tbPersonalInfo = tbPersonalInfo;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	// public Set getTbReckoningLists() {
	// return tbReckoningLists;
	// }
	//
	// public void setTbReckoningLists(Set tbReckoningLists) {
	// this.tbReckoningLists = tbReckoningLists;
	// }

	public TbReckoningList getTbReckoningList() {
		return tbReckoningList;
	}

	public void setTbReckoningList(TbReckoningList tbReckoningList) {
		this.tbReckoningList = tbReckoningList;
	}

	public TbManager getTbManager() {
		return tbManager;
	}

	public void setTbManager(TbManager tbManager) {
		this.tbManager = tbManager;
	}

}