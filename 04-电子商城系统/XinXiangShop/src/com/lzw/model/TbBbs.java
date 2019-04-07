package com.lzw.model;
import java.util.Date;
public class TbBbs  implements java.io.Serializable {
     private Integer id;
     private String title;
     private String content;
     private Date intime;
    public TbBbs() {
    }
    public TbBbs(Integer id, String content) {
        this.id = id;
        this.content = content;
    }
    public TbBbs(Integer id, String title, String content, Date intime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.intime = intime;
    }
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Date getIntime() {
        return this.intime;
    }
    
    public void setIntime(Date intime) {
        this.intime = intime;
    }
   








}