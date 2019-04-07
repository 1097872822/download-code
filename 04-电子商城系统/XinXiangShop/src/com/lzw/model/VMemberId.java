package com.lzw.model;

public class VMemberId implements java.io.Serializable {
	private Integer id;

	private String trueName;

	private String city;

	private String address;

	private String postcode;

	private String username;

	private String cardNo;

	private String cardType;

	private Integer grade;

	private double amount;

	private String tel;

	private String email;

	private float rebate;

	public VMemberId() {
	}

	public VMemberId(Integer id, String trueName, String city, String address,
			String postcode, String username, String email, float rebate) {
		this.id = id;
		this.trueName = trueName;
		this.city = city;
		this.address = address;
		this.postcode = postcode;
		this.username = username;
		this.email = email;
		this.rebate = rebate;
	}

	public VMemberId(Integer id, String trueName, String city, String address,
			String postcode, String username, String cardNo, String cardType,
			Integer grade, double amount, String tel, String email, float rebate) {
		this.id = id;
		this.trueName = trueName;
		this.city = city;
		this.address = address;
		this.postcode = postcode;
		this.username = username;
		this.cardNo = cardNo;
		this.cardType = cardType;
		this.grade = grade;
		this.amount = amount;
		this.tel = tel;
		this.email = email;
		this.rebate = rebate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrueName() {
		return this.trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
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

	public float getRebate() {
		return this.rebate;
	}

	public void setRebate(float rebate) {
		this.rebate = rebate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VMemberId))
			return false;
		VMemberId castOther = (VMemberId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getTrueName() == castOther.getTrueName()) || (this
						.getTrueName() != null
						&& castOther.getTrueName() != null && this
						.getTrueName().equals(castOther.getTrueName())))
				&& ((this.getCity() == castOther.getCity()) || (this.getCity() != null
						&& castOther.getCity() != null && this.getCity()
						.equals(castOther.getCity())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null
						&& castOther.getAddress() != null && this.getAddress()
						.equals(castOther.getAddress())))
				&& ((this.getPostcode() == castOther.getPostcode()) || (this
						.getPostcode() != null
						&& castOther.getPostcode() != null && this
						.getPostcode().equals(castOther.getPostcode())))
				&& ((this.getUsername() == castOther.getUsername()) || (this
						.getUsername() != null
						&& castOther.getUsername() != null && this
						.getUsername().equals(castOther.getUsername())))
				&& ((this.getCardNo() == castOther.getCardNo()) || (this
						.getCardNo() != null
						&& castOther.getCardNo() != null && this.getCardNo()
						.equals(castOther.getCardNo())))
				&& ((this.getCardType() == castOther.getCardType()) || (this
						.getCardType() != null
						&& castOther.getCardType() != null && this
						.getCardType().equals(castOther.getCardType())))
				&& ((this.getGrade() == castOther.getGrade()) || (this
						.getGrade() != null
						&& castOther.getGrade() != null && this.getGrade()
						.equals(castOther.getGrade())))
				&& (this.getAmount() == castOther.getAmount())
				&& ((this.getTel() == castOther.getTel()) || (this.getTel() != null
						&& castOther.getTel() != null && this.getTel().equals(
						castOther.getTel())))
				&& ((this.getEmail() == castOther.getEmail()) || (this
						.getEmail() != null
						&& castOther.getEmail() != null && this.getEmail()
						.equals(castOther.getEmail())))
				&& (this.getRebate() == castOther.getRebate());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getTrueName() == null ? 0 : this.getTrueName().hashCode());
		result = 37 * result
				+ (getCity() == null ? 0 : this.getCity().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result
				+ (getPostcode() == null ? 0 : this.getPostcode().hashCode());
		result = 37 * result
				+ (getUsername() == null ? 0 : this.getUsername().hashCode());
		result = 37 * result
				+ (getCardNo() == null ? 0 : this.getCardNo().hashCode());
		result = 37 * result
				+ (getCardType() == null ? 0 : this.getCardType().hashCode());
		result = 37 * result
				+ (getGrade() == null ? 0 : this.getGrade().hashCode());
		result = 37 * result + (int) this.getAmount();
		result = 37 * result
				+ (getTel() == null ? 0 : this.getTel().hashCode());
		result = 37 * result
				+ (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37 * result + (int) this.getRebate();
		return result;
	}

}