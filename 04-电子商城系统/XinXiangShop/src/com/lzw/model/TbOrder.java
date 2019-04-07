package com.lzw.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TbOrder implements java.io.Serializable {
	private Long orderId;

	private Short bnumber;

	private String username;

	private String truename;

	private String address;

	private String postcode;

	private String tel;

	private String pay;

	private String carry;

	private Float rebate;

	private Date orderDate;

	private Integer enforce;

	private String bz;

	private Set tbOrderDetails = new HashSet(0);

	public TbOrder() {
	}

	public TbOrder(Long orderId, Short bnumber, String username,
			String truename, String address, String postcode, String tel,
			String pay, String carry, Float rebate, Date orderDate) {
		this.orderId = orderId;
		this.bnumber = bnumber;
		this.username = username;
		this.truename = truename;
		this.address = address;
		this.postcode = postcode;
		this.tel = tel;
		this.pay = pay;
		this.carry = carry;
		this.rebate = rebate;
		this.orderDate = orderDate;
	}

	public TbOrder(Long orderId, Short bnumber, String username,
			String truename, String address, String postcode, String tel,
			String pay, String carry, Float rebate, Date orderDate,
			Integer enforce, String bz, Set tbOrderDetails) {
		this.orderId = orderId;
		this.bnumber = bnumber;
		this.username = username;
		this.truename = truename;
		this.address = address;
		this.postcode = postcode;
		this.tel = tel;
		this.pay = pay;
		this.carry = carry;
		this.rebate = rebate;
		this.orderDate = orderDate;
		this.enforce = enforce;
		this.bz = bz;
		this.tbOrderDetails = tbOrderDetails;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Short getBnumber() {
		return this.bnumber;
	}

	public void setBnumber(Short bnumber) {
		this.bnumber = bnumber;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTruename() {
		return this.truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPay() {
		return this.pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getCarry() {
		return this.carry;
	}

	public void setCarry(String carry) {
		this.carry = carry;
	}

	public Float getRebate() {
		return this.rebate;
	}

	public void setRebate(Float rebate) {
		this.rebate = rebate;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getEnforce() {
		return this.enforce;
	}

	public void setEnforce(Integer enforce) {
		this.enforce = enforce;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Set getTbOrderDetails() {
		return this.tbOrderDetails;
	}

	public void setTbOrderDetails(Set tbOrderDetails) {
		this.tbOrderDetails = tbOrderDetails;
	}
}