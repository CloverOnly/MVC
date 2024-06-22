package com.example.member;

import java.sql.Date;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


@Component("memberVO")
public class MemberVO {
	@NotEmpty
	private String id;
	
	@NotEmpty
	private String pwd;
	
	@NotBlank(message = "이름은 필수입니다")
	private String name;
	
	@NotBlank(message = "email형식이어야 합니다")
	@Email
	private String email;
	
	
	private Date joinDate;
	
	public MemberVO() {
		System.out.println("MemberVO 생성자 생성");
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}
