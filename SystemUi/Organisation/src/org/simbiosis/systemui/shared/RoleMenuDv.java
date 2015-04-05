package org.simbiosis.systemui.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RoleMenuDv implements IsSerializable {
	Integer nr;
	Long id;
	Long role;
	Long menu;
	String name;
	Boolean status;

	public RoleMenuDv() {
		nr = 0;
		id = 0L;
		role = 0L;
		menu = 0L;
		status = false;
	}

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public Long getMenu() {
		return menu;
	}

	public void setMenu(Long menu) {
		this.menu = menu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
