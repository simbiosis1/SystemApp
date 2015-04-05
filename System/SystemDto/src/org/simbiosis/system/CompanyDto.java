package org.simbiosis.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompanyDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3284291497748670181L;
	long id;
	String code;
	String name;
	String address;
	List<BranchDto> branches = new ArrayList<BranchDto>();

	public CompanyDto() {
		id = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<BranchDto> getBranches() {
		return branches;
	}

	public void setBranches(List<BranchDto> branches) {
		this.branches = branches;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
