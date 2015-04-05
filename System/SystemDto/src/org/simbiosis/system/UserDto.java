package org.simbiosis.system;

import java.io.Serializable;

public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5781698460601015265L;
	long id;
	String name;
	String realName;
	String password;
	boolean changePassword;
	String email;
	int level;
	long company;
	long branch;
	String branchName;
	long subBranch;
	String subBranchName;
	long hir1;
	long hir2;
	long hir3;
	long hir4;
	long hir5;
	long role;
	String roleName;
	int active;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}

	public long getHir1() {
		return hir1;
	}

	public void setHir1(long hir1) {
		this.hir1 = hir1;
	}

	public long getHir2() {
		return hir2;
	}

	public void setHir2(long hir2) {
		this.hir2 = hir2;
	}

	public long getHir3() {
		return hir3;
	}

	public void setHir3(long hir3) {
		this.hir3 = hir3;
	}

	public long getHir4() {
		return hir4;
	}

	public void setHir4(long hir4) {
		this.hir4 = hir4;
	}

	public long getHir5() {
		return hir5;
	}

	public void setHir5(long hir5) {
		this.hir5 = hir5;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCompany() {
		return company;
	}

	public void setCompany(long company) {
		this.company = company;
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

	public long getSubBranch() {
		return subBranch;
	}

	public void setSubBranch(long subBranch) {
		this.subBranch = subBranch;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getSubBranchName() {
		return subBranchName;
	}

	public void setSubBranchName(String subBranchName) {
		this.subBranchName = subBranchName;
	}

}
