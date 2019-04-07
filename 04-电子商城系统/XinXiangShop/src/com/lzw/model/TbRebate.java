package com.lzw.model;

public class TbRebate implements java.io.Serializable {
	private String grade;

	private Double amount;

	private Float rebate;

	public TbRebate() {
	}

	public TbRebate(String grade, Double amount, Float rebate) {
		this.grade = grade;
		this.amount = amount;
		this.rebate = rebate;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Float getRebate() {
		return this.rebate;
	}

	public void setRebate(Float rebate) {
		this.rebate = rebate;
	}

}