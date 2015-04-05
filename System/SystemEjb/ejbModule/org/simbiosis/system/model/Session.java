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
@Table(name = "SYS_SESSION")
@NamedQueries({
		@NamedQuery(name = "findSessionByName", query = "select x from Session x where x.valid=1 and x.name=:name"),
		@NamedQuery(name = "findSessionByUser", query = "select x from Session x where x.valid=1 and x.user.id=:userId") })
public class Session {
	@Id
	@Column(name = "SES_ID")
	@GeneratedValue(strategy = TABLE, generator = "gen_sys_session")
	@TableGenerator(name = "gen_sys_session", allocationSize = 1, pkColumnValue = "gen_sys_session")
	long id;
	@Column(name = "SES_NAME", length = 150)
	String name;
	@Column(name = "SES_BEGIN")
	Date begin;
	@Column(name = "SES_END")
	Date end;
	@Column(name = "SES_VALID")
	int valid;

	@ManyToOne
	@JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID")
	User user;

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

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
