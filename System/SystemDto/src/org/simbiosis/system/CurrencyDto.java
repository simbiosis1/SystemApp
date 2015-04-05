package org.simbiosis.system;

import java.io.Serializable;

public class CurrencyDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5531517814598274743L;
	long id;
	String code;
	String name;

	public CurrencyDto() {
		id = 0;
	}

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
