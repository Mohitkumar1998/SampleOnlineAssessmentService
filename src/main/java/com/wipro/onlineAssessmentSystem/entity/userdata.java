package com.wipro.onlineAssessmentSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="userdata")
public class userdata {
	@Id
	@NotNull
	@Column(name="email")
	private String email;
	@NotNull
	@Column(name="firstname")
	private String firstname;
	@NotNull
	@Column(name="lastname")
	private String lastname;
	@NotNull
	@Column(name="role")
	private String role;
	public userdata() {
	}
	public userdata(@NotNull String email, @NotNull String firstname, @NotNull String lastname, @NotNull String role) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
