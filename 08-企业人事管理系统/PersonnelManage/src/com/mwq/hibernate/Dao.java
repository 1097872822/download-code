package com.mwq.hibernate;

import java.util.List;

public class Dao extends BaseDao {

	private static Dao dao = null;

	public static Dao getInstance() {
		if (dao == null)
			dao = new Dao();
		return dao;
	}

	private Dao() {
	}

	// TbDutyInfo
	public List queryDutyInfoOfAccessionDateMax(String date) {
		return this.queryList("from TbDutyInfo where accessionDate <=to_date('"
				+ date + "','yyyy-mm-dd')");
	}

	// AccessionForm
	public List queryAccessionForm() {
		return this.queryList("from TbAccessionForm");
	}

	public Object queryAccessionFormByName(String name) {
		return this.queryObject("from TbAccessionForm where name='" + name
				+ "'");
	}

	public void initAccessionForm() {
		deleteOfBatch("delete from TbAccessionForm where id >2");
	}

	// AccountItem
	public List queryAccountItem() {
		return this.queryList("from TbAccountItem");
	}

	public List queryAccountItemUsedTimecard() {
		return this.queryList("from TbAccountItem where isTimecard='ÊÇ'");
	}

	public Object queryAccountItemByName(String name) {
		return this.queryObject("from TbAccountItem where name='" + name + "'");
	}

	public Object queryAccountItemByNameUnit(String name, String unit) {
		return this.queryObject("from TbAccountItem where name='" + name
				+ "' and unit='" + unit + "'");
	}

	public void initAccountItem() {
		deleteOfBatch("delete from TbAccountItem where id >6");
	}

	// BringUpContent
	public List queryBringUpContent() {
		return this.queryList("from TbBringUpContent");
	}

	public Object queryBringUpContentById(String id) {
		return this.queryObject("from TbBringUpContent where id=" + id);
	}

	public void initBringUpContent() {
		deleteOfBatch("delete from TbBringUpContent");
	}

	// BringUpOntent
	public void initBringUpOntent() {
		deleteOfBatch("delete from TbBringUpOntent");
	}

	// Dept
	public List queryDept() {
		return this.queryList("from TbDept");
	}

	public Object queryDeptById(int id) {
		return this.queryObject("from TbDept where id=" + id);
	}

	public Object queryDeptByName(String name) {
		return this.queryObject("from TbDept where name='" + name + "'");
	}

	public void initDept() {
		deleteOfBatch("delete from TbDept where id>8");
	}

	// Duty
	public List queryDuty() {
		return this.queryList("from TbDuty");
	}

	public Object queryDutyByName(String name) {
		return this.queryObject("from TbDuty where name='" + name + "'");
	}

	public void initDuty() {
		deleteOfBatch("delete from TbDuty where id>4");
	}

	// Record
	public List queryRecord() {
		return this.queryList("from TbRecord");
	}

	public Object queryRecordByNum(String num) {
		return this.queryObject("from TbRecord where record_number='" + num
				+ "'");
	}

	public Object queryRecordOfMaxRecordNum() {
		return this.queryObject("select max(recordNumber) from TbRecord");
	}

	public Object queryRecordOfMinAccessionDate() {
		return this.queryObject("select min(accessionDate) from TbDutyInfo");
	}

	public void initRecord() {
		deleteOfBatch("delete from TbRecord");
	}

	// Reckoning
	public List queryReckoning() {
		return this.queryList("from TbReckoning");
	}

	// Manager
	public List queryManager() {
		return this.queryList("from TbManager");
	}

	public List queryManagerOfNatural() {
		return this.queryList("from TbManager where state='Õý³£'");
	}

	public void initManager() {
		deleteOfBatch("delete from TbManager");
	}

	// Nation
	public List queryNation() {
		return this.queryList("from TbNation");
	}

	public Object queryNationByName(String name) {
		return this.queryObject("from TbNation where name='" + name + "'");
	}

	public void initNation() {
		deleteOfBatch("delete from TbNation where id>4");
	}

	// NativePlace
	public List queryNativePlace() {
		return this.queryList("from TbNativePlace");
	}

	public Object queryNativePlaceByName(String name) {
		return this.queryObject("from TbNativePlace where name='" + name + "'");
	}

	public void initNativePlace() {
		deleteOfBatch("delete from TbNativePlace where id>6");
	}

	// Reckoning
	public void initReckoning() {
		deleteOfBatch("delete from TbReckoning where id>2");
	}

	// ReckoningInfo
	public void initReckoningInfo() {
		deleteOfBatch("delete from TbReckoningInfo");
	}

	// ReckoningList
	public void initReckoningList() {
		deleteOfBatch("delete from TbReckoningList");
	}

	// RewardsAndPunishment
	public void initRewardsAndPunishment() {
		deleteOfBatch("delete from TbRewardsAndPunishment");
	}

	// Timecard
	public void initTimecard() {
		deleteOfBatch("delete from TbTimecard");
	}

	// PersonalInfo
	public void initPersonalInfo() {
		deleteOfBatch("delete from TbPersonalInfo");
	}

	// DutyInfo
	public void initDutyInfo() {
		deleteOfBatch("delete from TbDutyInfo");
	}

}
