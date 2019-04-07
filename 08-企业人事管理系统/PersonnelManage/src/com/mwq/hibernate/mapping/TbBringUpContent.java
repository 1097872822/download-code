package com.mwq.hibernate.mapping;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TbBringUpContent implements java.io.Serializable {

	// Fields

	private Integer id;

	private String name;

	private String content;

	private String object;

	private Date startDate;

	private Date endDate;

	private String unit;

	private String lecturer;

	private String place;

	private Set tbBringUpOntents = new HashSet(0);

	// Constructors

	/** default constructor */
	public TbBringUpContent() {
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
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

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getLecturer() {
		return this.lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Set getTbBringUpOntents() {
		return this.tbBringUpOntents;
	}

	public void setTbBringUpOntents(Set tbBringUpOntents) {
		this.tbBringUpOntents = tbBringUpOntents;
	}

}