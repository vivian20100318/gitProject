package com.hjkj.business.usermanage.po;

import java.io.Serializable;

public class CaseMailMenuInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int resource_id;
	
	private String resource_name;
	
	private int parent_id;
	
	private String access_path;
	
	private int resource_grade;
	
	private int resource_type;

	public int getResource_id() {
		return resource_id;
	}

	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getAccess_path() {
		return access_path;
	}

	public void setAccess_path(String access_path) {
		this.access_path = access_path;
	}

	public int getResource_grade() {
		return resource_grade;
	}

	public void setResource_grade(int resource_grade) {
		this.resource_grade = resource_grade;
	}

	public int getResource_type() {
		return resource_type;
	}

	public void setResource_type(int resource_type) {
		this.resource_type = resource_type;
	}
	
	
}
