package com.hjkj.business.usermanage.po;

import java.io.Serializable;

public class HospitalUserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long inner_id;
	
	private String user_id;//登录id
	
	private String user_name;//姓名
	
	private String user_pwd;
	
	private String role_json;
	
	private Long creat_id;
	
	private String creat_time;
	
	private Integer user_status;//1.正常。0注销
	
	private String user_sex;//1.男；0.女
	
	private String user_phone;
	
	private String is_admin;//1是 0不是
	
	private Long hospital_id;
	
	private String hospital_name;

	public Long getInner_id() {
		return inner_id;
	}

	public void setInner_id(Long inner_id) {
		this.inner_id = inner_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getRole_json() {
		return role_json;
	}

	public void setRole_json(String role_json) {
		this.role_json = role_json;
	}

	public Long getCreat_id() {
		return creat_id;
	}

	public void setCreat_id(Long creat_id) {
		this.creat_id = creat_id;
	}

	public String getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(String creat_time) {
		this.creat_time = creat_time;
	}

	public Integer getUser_status() {
		return user_status;
	}

	public void setUser_status(Integer user_status) {
		this.user_status = user_status;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}

	public Long getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(Long hospital_id) {
		this.hospital_id = hospital_id;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	
	

}
