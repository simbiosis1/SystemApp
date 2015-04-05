package org.simbiosis.system.model;

import static javax.persistence.GenerationType.TABLE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SYS_CURRENCY")
@NamedQueries({ @NamedQuery(name = "listCurrency", query = "select x from Currency x") })
public class Currency {
	@Column(name = "CUR_ID")
	@Id
	@GeneratedValue(strategy = TABLE, generator = "gen_acc_currency")
	@TableGenerator(name = "gen_acc_currency", allocationSize = 1, pkColumnValue = "gen_acc_currency")
	long id;
	@Column(name = "CUR_CODE", length = 30)
	String code;
	@Column(name = "CUR_NAME", length = 100)
	String name;

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

}
