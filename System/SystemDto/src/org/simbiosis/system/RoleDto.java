package org.simbiosis.system;

import java.io.Serializable;

public class RoleDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4438292259571107965L;
	long id;
	String name;
	String description;
	long company;

	public RoleDto() {
		super();
		id = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCompany() {
		return company;
	}

	public void setCompany(long company) {
		this.company = company;
	}

}
