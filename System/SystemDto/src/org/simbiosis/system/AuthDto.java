package org.simbiosis.system;

import java.io.Serializable;
import java.util.Date;

public class AuthDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -854175116953751383L;
	long id;
	String description;
	Date timestamp;
	int level;
	long user;
	String userName;
	long authorizer;
	String authorizerName;
	int active;
	String key;
	long company;
	long branch;
	String branchName;

	public AuthDto() {
		id = 0;
		active = 1;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getAuthorizer() {
		return authorizer;
	}

	public void setAuthorizer(long authorizer) {
		this.authorizer = authorizer;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthorizerName() {
		return authorizerName;
	}

	public void setAuthorizerName(String authorizerName) {
		this.authorizerName = authorizerName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}
