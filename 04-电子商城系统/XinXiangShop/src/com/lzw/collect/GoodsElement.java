package com.lzw.collect;

public class GoodsElement {
    public long ID;    //��ƷID
    public String goodsName;	//��Ʒ����
    public double nowPrice;    //�ּ�
    public int number;     //����
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
