package org.simbiosis.system.model;

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
import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name = "SYS_BRANCH")
@NamedQueries({
		@NamedQuery(name = "listBranchByCompany", query = "select x from Branch x where x.company.id=:companyId"),
		@NamedQuery(name = "findBranchByCode", query = "select x from Branch x where x.company.id=:companyId and x.code=:code") })
public class Branch {
	@Id
	@Column(name = "BRN_ID")
	@GeneratedValue(strategy = TABLE, generator = "gen_sys_branch")
	@TableGenerator(name = "gen_sys_branch", allocationSize = 1, pkColumnValue = "gen_sys_branch")
	long id;
	@Column(name = "BRN_CODE", length = 30)
	String code;
	@Column(name = "BRN_NAME", length = 50)
	String name;
	@Column(name = "BRN_ADDRESS", length = 100)
	String address;
	@Column(name = "BRN_PARAM1")
	long param1;
	@Column(name = "BRN_PARAM2")
	long param2;
	@Column(name = "BRN_PARAM3")
	long param3;
	@Column(name = "BRN_PARAM4")
	long param4;

	@ManyToOne
	@JoinColumn(name = "COM_ID", referencedColumnName = "COM_ID")
	Company company;

	// @OneToMany(mappedBy = "branch")
	// List<SubBranch> subBranches = new ArrayList<SubBranch>();

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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	// public List<SubBranch> getSubBranches() {
	// return subBranches;
	// }
	//
	// public void setSubBranches(List<SubBranch> subBranches) {
	// this.subBranches = subBranches;
	// }

	public long getParam1() {
		return param1;
	}

	public void setParam1(long param1) {
		this.param1 = param1;
	}

	public long getParam2() {
		return param2;
	}

	public void setParam2(long param2) {
		this.param2 = param2;
	}

	public long getParam3() {
		return param3;
	}

	public void setParam3(long param3) {
		this.param3 = param3;
	}

	public long getParam4() {
		return param4;
	}

	public void setParam4(long param4) {
		this.param4 = param4;
	}
}
