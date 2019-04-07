package com.lzw.model;

import java.util.HashSet;
import java.util.Set;
public class TbSuperType  implements java.io.Serializable {
     private Integer id;
     private String typeName;
     private Set tbSubTypes = new HashSet(0);
    public TbSuperType() {
    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Set getTbSubTypes() {
        return this.tbSubTypes;
    }
    public void setTbSubTypes(Set tbSubTypes) {
        this.tbSubTypes = tbSubTypes;
    }
}