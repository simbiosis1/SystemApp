package org.simbiosis.system.model;

import static javax.persistence.GenerationType.TABLE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SYS_AUTH")
@NamedQueries({
		@NamedQuery(name = "getAuthByKey", query = "select x from Auth x where x.key=:key and x.active=1"),
		@NamedQuery(name = "listAuthByCompany", query = "select x from Auth x where x.company.id=:company and x.level>=:level and x.active=1 and x.authorizer is null order by x.branch.id"),
		@NamedQuery(name = "listAuthByBranch", query = "select x from Auth x where x.branch.id=:branch and x.level>=:level and x.active=1 and x.authorizer is null order by x.branch.id") })
public class Auth {
	@Id
	@Column(name = "AUT_ID")
	@GeneratedValue(strategy = TABLE, generator = "gen_sys_auth")
	@TableGenerator(name = "gen_sys_auth", allocationSize = 1, pkColumnValue = "gen_sys_auth")
	long id;
	@Column(name = "AUT_DESCRIPTION", length = 500)
	String description;
	@Column(name = "AUT_TIMESTAMP")
	Date timestamp;
	@ManyToOne
	@JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID")
	User user;
	@Column(name = "AUT_LEVEL")
	int level;
	@Column(name = "AUT_ACTIVE")
	int active;
	@Column(name = "AUT_KEY", length = 100)
	String key;
	@ManyToOne
	@JoinColumn(name = "AUT_AUTHORIZER", referencedColumnName = "USR_ID")
	User authorizer;
	@ManyToOne
	@JoinColumn(name = "COM_ID", referencedColumnName = "COM_ID")
	Company company;
	@ManyToOne
	@JoinColumn(name = "BRN_ID", referencedColumnName = "BRN_ID")
	Branch branch;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getAuthorizer() {
		return authorizer;
	}

	public void setAuthorizer(User authorizer) {
		this.authorizer = authorizer;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

}
