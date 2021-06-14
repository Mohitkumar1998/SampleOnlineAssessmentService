package com.wipro.onlineAssessmentSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="testdata")
public class testdata {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@NotNull
	@Column(name="email")
	private String email;
	@NotNull
	@Column(name="testdate")
	private String testdate;
	@NotNull
	@Column(name="assessment")
	private String assessment;
	@NotNull
	@Column(name="testmarks")
	private int testmarks;
	@NotNull
	@Column(name="totalmarks")
	private int totalmarks;
	@NotNull
	@Column(name="result")
	private String result;
	public testdata(@NotNull String email, @NotNull String testdate, @NotNull String assessment, @NotNull int testmarks,
			@NotNull int totalmarks, @NotNull String result) {
		this.email = email;
		this.testdate = testdate;
		this.assessment = assessment;
		this.testmarks = testmarks;
		this.totalmarks = totalmarks;
		this.result = result;
	}
	public testdata() {
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTestdate() {
		return testdate;
	}
	public void setTestdate(String testdate) {
		this.testdate = testdate;
	}
	public String getAssessment() {
		return assessment;
	}
	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}
	public int getTestmarks() {
		return testmarks;
	}
	public void setTestmarks(int testmarks) {
		this.testmarks = testmarks;
	}
	public int getTotalmarks() {
		return totalmarks;
	}
	public void setTotalmarks(int totalmarks) {
		this.totalmarks = totalmarks;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
