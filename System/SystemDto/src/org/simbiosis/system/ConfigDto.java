package org.simbiosis.system;

import java.io.Serializable;

public class ConfigDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8539996916064106236L;
	long id;
	String key;
	String strValue;
	long longValue;
	int intValue;
	double doubleValue;
	long company;

	public ConfigDto() {
		super();
		id = 0;
		longValue = 0;
		intValue = 0;
		doubleValue = 0;
		company = 0;
	}

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

	public long getCompany() {
		return company;
	}

	public void setCompany(long company) {
		this.company = company;
	}

}
