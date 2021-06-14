package com.wipro.onlineAssessmentSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user")
public class user {
	@Id
	@NotNull
	@Column(name="email")
	private String email;
	@NotNull
	@Column(name="password")
	private String password;
	@NotNull
	@Column(name="role")
	private String role;
	public user(@NotNull String email, @NotNull String password, @NotNull String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public user() {
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "user [email=" + email + ", password=" + password + ", role=" + role + "]";
	}
	
}
