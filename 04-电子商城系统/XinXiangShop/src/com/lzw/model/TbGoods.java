package com.lzw.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TbGoods implements java.io.Serializable {
	private Long id;

	private Integer typeId;

	private String goodsName;

	private String introduce;

	private Double price;

	private Double nowPrice;

	private String picture;

	private Date intime;

	private Integer newGoods;

	private Integer sale;

	private Integer hit;

	private Set tbOrderDetails = new HashSet(0);

	public TbGoods() {
	}

	public TbGoods(Long id, Integer typeId, String goodsName, Double price,
			Date intime, Integer newGoods) {
		this.id = id;
		this.typeId = typeId;
		this.goodsName = goodsName;
		this.price = price;
		this.intime = intime;
		this.newGoods = newGoods;
	}

	public TbGoods(Long id, Integer typeId, String goodsName, String introduce,
			Double price, Double nowPrice, String picture, Date intime,
			Integer newGoods, Integer sale, Integer hit, Set tbOrderDetails) {
		this.id = id;
		this.typeId = typeId;
		this.goodsName = goodsName;
		this.introduce = introduce;
		this.price = price;
		this.nowPrice = nowPrice;
		this.picture = picture;
		this.intime = intime;
		this.newGoods = newGoods;
		this.sale = sale;
		this.hit = hit;
		this.tbOrderDetails = tbOrderDetails;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getNowPrice() {
		return this.nowPrice;
	}

	public void setNowPrice(Double nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getIntime() {
		return this.intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public Integer getNewGoods() {
		return this.newGoods;
	}

	public void setNewGoods(Integer newGoods) {
		this.newGoods = newGoods;
	}

	public Integer getSale() {
		return this.sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}

	public Integer getHit() {
		return this.hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public Set getTbOrderDetails() {
		return this.tbOrderDetails;
	}

	public void setTbOrderDetails(Set tbOrderDetails) {
		this.tbOrderDetails = tbOrderDetails;
	}

}