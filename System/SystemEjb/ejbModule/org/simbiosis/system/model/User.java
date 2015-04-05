package org.simbiosis.system.model;

import static javax.persistence.GenerationType.TABLE;

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
@Table(name = "SYS_USER")
@NamedQueries({
		@NamedQuery(name = "getUserMatch", query = "select x from User x where x.name=:user and x.password=:password and x.active=1"),
		@NamedQuery(name = "listUsers1", query = "select x from User x where x.company.id=:company order by x.branch,x.name"),
		@NamedQuery(name = "listUsers2", query = "select x from User x where x.branch.id=:branch order by x.name"),
		@NamedQuery(name = "listActiveUsers1", query = "select x from User x where x.active=1 and x.company.id=:company order by x.branch,x.name"),
		@NamedQuery(name = "listActiveUsers2", query = "select x from User x where x.active=1 and x.branch.id=:branch order by x.name") })
public class User {
	@Id
	@Column(name = "USR_ID")
	@GeneratedValue(strategy = TABLE, generator = "gen_sys_user")
	@TableGenerator(name = "gen_sys_user", allocationSize = 1, pkColumnValue = "gen_sys_user")
	long id;
	@Column(name = "USR_NAME", length = 50)
	String name;
	@Column(name = "USR_REALNAME", length = 150)
	String realName;
	@Column(name = "USR_PASSWORD", length = 100)
	String password;
	@Column(name = "USR_EMAIL", length = 100)
	String email;
	@Column(name = "USR_LEVEL")
	int level;
	@ManyToOne
	@JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID")
	Role role;
	@ManyToOne
	@JoinColumn(name = "COM_ID", referencedColumnName = "COM_ID")
	Company company;
	@ManyToOne
	@JoinColumn(name = "BRN_ID", referencedColumnName = "BRN_ID")
	Branch branch;
	@ManyToOne
	@JoinColumn(name = "SBR_ID", referencedColumnName = "SBR_ID")
	SubBranch subBranch;
	@Column(name = "USR_ACTIVE")
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public SubBranch getSubBranch() {
		return subBranch;
	}

	public void setSubBranch(SubBranch subBranch) {
		this.subBranch = subBranch;
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
}
