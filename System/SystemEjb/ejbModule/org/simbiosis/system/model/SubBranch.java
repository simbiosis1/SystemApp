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
@Table(name = "SYS_SUBBRANCH")
@NamedQueries({
		@NamedQuery(name = "listSubBranch1", query = "select x from SubBranch x where x.company=:company"),
		@NamedQuery(name = "listSubBranch2", query = "select x from SubBranch x where x.branch.id=:branch") })
public class SubBranch {
	@Id
	@Column(name = "SBR_ID")
	@GeneratedValue(strategy = TABLE, generator = "gen_sys_subbranch")
	@TableGenerator(name = "gen_sys_subbranch", allocationSize = 1, pkColumnValue = "gen_sys_subbranch")
	long id;
	@Column(name = "SBR_CODE", length = 30)
	String code;
	@Column(name = "SBR_NAME", length = 50)
	String name;
	@Column(name = "SBR_ADDRESS", length = 100)
	String address;

	@Column(name = "COM_ID")
	long company;
	
	@ManyToOne
	@JoinColumn(name = "BRN_ID", referencedColumnName = "BRN_ID")
	Branch branch;

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

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public long getCompany() {
		return company;
	}

	public void setCompany(long company) {
		this.company = company;
	}

}
