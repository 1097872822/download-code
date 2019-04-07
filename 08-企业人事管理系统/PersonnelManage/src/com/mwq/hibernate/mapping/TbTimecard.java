package com.mwq.hibernate.mapping;

import java.util.Date;


public class TbTimecard  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private TbDept tbDept;
     private TbRecord tbRecordByRecordId;
     private TbRecord tbRecordByRatifierRecordId;
     private TbAccountItem tbAccountItem;
     private String explain;
     private Date startDate;
     private Date endDate;
     private Date ratifierDate;


    // Constructors

    public TbTimecard() {
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public TbDept getTbDept() {
        return this.tbDept;
    }
    
    public void setTbDept(TbDept tbDept) {
        this.tbDept = tbDept;
    }

    public TbRecord getTbRecordByRecordId() {
        return this.tbRecordByRecordId;
    }
    
    public void setTbRecordByRecordId(TbRecord tbRecordByRecordId) {
        this.tbRecordByRecordId = tbRecordByRecordId;
    }

    public TbRecord getTbRecordByRatifierRecordId() {
        return this.tbRecordByRatifierRecordId;
    }
    
    public void setTbRecordByRatifierRecordId(TbRecord tbRecordByRatifierRecordId) {
        this.tbRecordByRatifierRecordId = tbRecordByRatifierRecordId;
    }

    public TbAccountItem getTbAccountItem() {
        return this.tbAccountItem;
    }
    
    public void setTbAccountItem(TbAccountItem tbAccountItem) {
        this.tbAccountItem = tbAccountItem;
    }

    public String getExplain() {
        return this.explain;
    }
    
    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRatifierDate() {
        return this.ratifierDate;
    }
    
    public void setRatifierDate(Date ratifierDate) {
        this.ratifierDate = ratifierDate;
    }

}