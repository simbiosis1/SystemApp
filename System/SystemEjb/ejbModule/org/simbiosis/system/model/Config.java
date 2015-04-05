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
@Table(name = "SYS_CONFIG")
@NamedQueries({ @NamedQuery(name = "getConfigValue", query = "select x from Config x where x.company.id=:company and x.key=:key") })
public class Config {
	@Id
	@Column(name = "CFG_ID")
	@GeneratedValue(strategy = TABLE, generator = "gen_sys_config")
	@TableGenerator(name = "gen_sys_config", allocationSize = 1, pkColumnValue = "gen_sys_config")
	long id;
	@Column(name = "CFG_KEY", length = 100)
	String key;
	@Column(name = "CFG_STRVALUE", length = 500)
	String strValue;
	@Column(name = "CFG_LONGVALUE")
	long longValue;
	@Column(name = "CFG_INTVALUE")
	int intValue;
	@Column(name = "CFG_DOUBLEVALUE")
	double doubleValue;

	@ManyToOne
	@JoinColumn(name = "COM_ID", referencedColumnName = "COM_ID")
	Company company;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
