package com.lzw.model;

public class TbMember implements java.io.Serializable {
	private Integer id;

	private String userName;

	private String trueName;

	private String passWord;

	private String city;

	private String address;

	private String postcode;

	private String cardNo;

	private String cardType;

	private Integer grade;

	private Double amount;

	private String tel;

	private String email;

	private Integer freeze;

	public TbMember() {
	}

	public TbMember(Integer id, String userName, String trueName,
			String passWord, String city, String address, String postcode,
			String email) {
		this.id = id;
		this.userName = userName;
		this.trueName = trueName;
		this.passWord = passWord;
		this.city = city;
		this.address = address;
		this.postcode = postcode;
		this.email = email;
	}

	public TbMember(Integer id, String userName, String trueName,
			String passWord, String city, String address, String postcode,
			String cardNo, String cardType, Integer grade, Double amount,
			String tel, String email, Integer freeze) {
		this.id = id;
		this.userName = userName;
		this.trueName = trueName;
		this.passWord = passWord;
		this.city = city;
		this.address = address;
		this.postcode = postcode;
		this.cardNo = cardNo;
		this.cardType = cardType;
		this.grade = grade;
		this.amount = amount;
		this.tel = tel;
		this.email = email;
		this.freeze = freeze;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTrueName() {
		return this.trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getFreeze() {
		return this.freeze;
	}

	public void setFreeze(Integer freeze) {
		this.freeze = freeze;
	}

}