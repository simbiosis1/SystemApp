package org.simbiosis.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name = "SYS_COMPANY")
@NamedQueries({
		@NamedQuery(name = "listCompany", query = "select x from Company x"),
		@NamedQuery(name = "findCompanyByCode", query = "select x from Company x where x.code=:code") })
public class Company {
	@Id
	@Column(name = "COM_ID")
	@GeneratedValue(strategy = TABLE, generator = "gen_sys_company")
	@TableGenerator(name = "gen_sys_company", allocationSize = 1, pkColumnValue = "gen_sys_company")
	long id;
	@Column(name = "COM_CODE", length = 30)
	String code;
	@Column(name = "COM_NAME", length = 50)
	String name;
	@Column(name = "COM_ADDRESS", length = 100)
	String address;
	@Column(name = "COM_DATAPATH", length = 100)
	String datapath;

	// @OneToMany(mappedBy = "company")
	// List<Branch> branches = new ArrayList<Branch>();

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

	// public List<Branch> getBranches() {
	// return branches;
	// }
	//
	// public void setBranches(List<Branch> branches) {
	// this.branches = branches;
	// }

	public String getDatapath() {
		return datapath;
	}

	public void setDatapath(String datapath) {
		this.datapath = datapath;
	}
}
