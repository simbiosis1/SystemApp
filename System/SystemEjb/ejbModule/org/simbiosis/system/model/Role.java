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
@Table(name = "SYS_ROLE")
@NamedQueries({
	@NamedQuery(name = "listRoles", query = "select x from Role x where x.company.id=:company") })
public class Role {
	@Id
	@Column(name = "ROL_ID")
	@GeneratedValue(strategy = TABLE, generator = "gen_sys_role")
	@TableGenerator(name = "gen_sys_role", allocationSize = 1, pkColumnValue = "gen_sys_role")
	long id;
	@Column(name = "ROL_NAME", length = 50)
	String name;
	@Column(name = "ROL_DESCRIPTION", length = 100)
	String description;
	@ManyToOne
	@JoinColumn(name = "COM_ID", referencedColumnName = "COM_ID")
	Company company;

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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
