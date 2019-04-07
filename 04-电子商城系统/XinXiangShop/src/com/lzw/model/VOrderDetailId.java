package com.lzw.model;

public class VOrderDetailId implements java.io.Serializable {
	private long orderId;

	private String goodsId;

	private String goodsName;

	private double price;

	private Integer number;

	public VOrderDetailId() {
	}

	public VOrderDetailId(long orderId, String goodsId, String goodsName,
			double price, Integer number) {
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.price = price;
		this.number = number;
	}

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VOrderDetailId))
			return false;
		VOrderDetailId castOther = (VOrderDetailId) other;

		return (this.getOrderId() == castOther.getOrderId())
				&& ((this.getGoodsId() == castOther.getGoodsId()) || (this
						.getGoodsId() != null
						&& castOther.getGoodsId() != null && this.getGoodsId()
						.equals(castOther.getGoodsId())))
				&& ((this.getGoodsName() == castOther.getGoodsName()) || (this
						.getGoodsName() != null
						&& castOther.getGoodsName() != null && this
						.getGoodsName().equals(castOther.getGoodsName())))
				&& (this.getPrice() == castOther.getPrice())
				&& ((this.getNumber() == castOther.getNumber()) || (this
						.getNumber() != null
						&& castOther.getNumber() != null && this.getNumber()
						.equals(castOther.getNumber())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getOrderId();
		result = 37 * result
				+ (getGoodsId() == null ? 0 : this.getGoodsId().hashCode());
		result = 37 * result
				+ (getGoodsName() == null ? 0 : this.getGoodsName().hashCode());
		result = 37 * result + (int) this.getPrice();
		result = 37 * result
				+ (getNumber() == null ? 0 : this.getNumber().hashCode());
		return result;
	}

}