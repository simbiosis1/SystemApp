package org.simbiosis.system;

import java.io.Serializable;

public class SubBranchDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6118417056491573036L;
	long id;
	String code;
	String name;
	String address;
	long company;
	long branch;
	String branchName;

	public SubBranchDto() {
		super();
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

	public long getBranch() {
		return branch;
	}

	public void setBranch(long branch) {
		this.branch = branch;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public long getCompany() {
		return company;
	}

	public void setCompany(long company) {
		this.company = company;
	}

}
