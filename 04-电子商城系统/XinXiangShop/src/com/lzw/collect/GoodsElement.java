package com.lzw.collect;

public class GoodsElement {
    public long ID;    //商品ID
    public String goodsName;	//商品名称
    public double nowPrice;    //现价
    public int number;     //数量
	public long getID() {
		return ID;
	}
	public void setID(long id) {
		ID = id;
	}
	public double getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(double nowprice) {
		this.nowPrice = nowprice;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
  }
