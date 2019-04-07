package com.mwq.hibernate.mapping;

public class TbReckoningInfo implements java.io.Serializable {

	// Fields

	private Integer id;

	private TbReckoning tbReckoning;

	private TbAccountItem tbAccountItem;

	private Integer money;

	// Constructors

	public TbReckoningInfo() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbReckoning getTbReckoning() {
		return this.tbReckoning;
	}

	public void setTbReckoning(TbReckoning tbReckoning) {
		this.tbReckoning = tbReckoning;
	}

	public TbAccountItem getTbAccountItem() {
		return this.tbAccountItem;
	}

	public void setTbAccountItem(TbAccountItem tbAccountItem) {
		this.tbAccountItem = tbAccountItem;
	}

	public Integer getMoney() {
		return this.money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

}