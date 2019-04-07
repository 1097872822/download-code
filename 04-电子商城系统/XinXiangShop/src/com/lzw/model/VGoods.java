package com.lzw.model;
public class VGoods implements java.io.Serializable {
     private long id;
     private Integer superId;
     private String supertype;
     private Integer subId;
     private String subtype;
     private String goodsName;
     private String introduce;
     private double price;
     private double nowPrice;
     private String picture;
     private Integer newGoods;
     private Integer sale;
    public VGoods() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Integer getSuperId() {
        return this.superId;
    }
    public void setSuperId(Integer superId) {
        this.superId = superId;
    }
    public String getSupertype() {
        return this.supertype;
    }
    public void setSupertype(String supertype) {
        this.supertype = supertype;
    }
    public Integer getSubId() {
        return this.subId;
    }
    public void setSubId(Integer subId) {
        this.subId = subId;
    }
    public String getSubtype() {
        return this.subtype;
    }
    public void setSubtype(String subtype) {
        this.subtype = subtype;
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
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getNowPrice() {
        return this.nowPrice;
    }
    public void setNowPrice(double nowPrice) {
        this.nowPrice = nowPrice;
    }
    public String getPicture() {
        return this.picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
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
   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VGoods) ) return false;
		 VGoods castOther = ( VGoods ) other; 
         
		 return (this.getId()==castOther.getId())
 && ( (this.getSuperId()==castOther.getSuperId()) || ( this.getSuperId()!=null && castOther.getSuperId()!=null && this.getSuperId().equals(castOther.getSuperId()) ) )
 && ( (this.getSupertype()==castOther.getSupertype()) || ( this.getSupertype()!=null && castOther.getSupertype()!=null && this.getSupertype().equals(castOther.getSupertype()) ) )
 && ( (this.getSubId()==castOther.getSubId()) || ( this.getSubId()!=null && castOther.getSubId()!=null && this.getSubId().equals(castOther.getSubId()) ) )
 && ( (this.getSubtype()==castOther.getSubtype()) || ( this.getSubtype()!=null && castOther.getSubtype()!=null && this.getSubtype().equals(castOther.getSubtype()) ) )
 && ( (this.getGoodsName()==castOther.getGoodsName()) || ( this.getGoodsName()!=null && castOther.getGoodsName()!=null && this.getGoodsName().equals(castOther.getGoodsName()) ) )
 && ( (this.getIntroduce()==castOther.getIntroduce()) || ( this.getIntroduce()!=null && castOther.getIntroduce()!=null && this.getIntroduce().equals(castOther.getIntroduce()) ) )
 && (this.getPrice()==castOther.getPrice())
 && (this.getNowPrice()==castOther.getNowPrice())
 && ( (this.getPicture()==castOther.getPicture()) || ( this.getPicture()!=null && castOther.getPicture()!=null && this.getPicture().equals(castOther.getPicture()) ) )
 && ( (this.getNewGoods()==castOther.getNewGoods()) || ( this.getNewGoods()!=null && castOther.getNewGoods()!=null && this.getNewGoods().equals(castOther.getNewGoods()) ) )
 && ( (this.getSale()==castOther.getSale()) || ( this.getSale()!=null && castOther.getSale()!=null && this.getSale().equals(castOther.getSale()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getId();
         result = 37 * result + ( getSuperId() == null ? 0 : this.getSuperId().hashCode() );
         result = 37 * result + ( getSupertype() == null ? 0 : this.getSupertype().hashCode() );
         result = 37 * result + ( getSubId() == null ? 0 : this.getSubId().hashCode() );
         result = 37 * result + ( getSubtype() == null ? 0 : this.getSubtype().hashCode() );
         result = 37 * result + ( getGoodsName() == null ? 0 : this.getGoodsName().hashCode() );
         result = 37 * result + ( getIntroduce() == null ? 0 : this.getIntroduce().hashCode() );
         result = 37 * result + (int) this.getPrice();
         result = 37 * result + (int) this.getNowPrice();
         result = 37 * result + ( getPicture() == null ? 0 : this.getPicture().hashCode() );
         result = 37 * result + ( getNewGoods() == null ? 0 : this.getNewGoods().hashCode() );
         result = 37 * result + ( getSale() == null ? 0 : this.getSale().hashCode() );
         return result;
   }   
}