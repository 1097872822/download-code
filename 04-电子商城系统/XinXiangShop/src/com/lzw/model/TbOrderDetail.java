package com.lzw.model;
public class TbOrderDetail implements java.io.Serializable {
	private Long id;
	private TbOrder tbOrder;
	private TbGoods tbGoods;
	private Double price;
	private Integer number;
	public TbOrderDetail() {
	}
	public TbOrderDetail(Long id, TbOrder tbOrder, TbGoods tbGoods,
			Double price, Integer number) {
		this.id = id;
		this.tbOrder = tbOrder;
		this.tbGoods = tbGoods;
		this.price = price;
		this.number = number;
	}
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbOrder getTbOrder() {
		return this.tbOrder;
	}

	public void setTbOrder(TbOrder tbOrder) {
		this.tbOrder = tbOrder;
	}

	public TbGoods getTbGoods() {
		return this.tbGoods;
	}

	public void setTbGoods(TbGoods tbGoods) {
		this.tbGoods = tbGoods;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}