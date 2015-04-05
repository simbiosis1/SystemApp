package org.simbiosis.systemui.shared;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RoleDv implements IsSerializable {

	Integer nr;
	Long id;
	String name;
	String description;

	List<RoleMenuDv> roleMenus = new ArrayList<RoleMenuDv>();

	public RoleDv() {
		nr = 0;
		id = 0L;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RoleMenuDv> getRoleMenus() {
		return roleMenus;
	}

	public void setRoleMenus(List<RoleMenuDv> roleMenus) {
		this.roleMenus = roleMenus;
	}

}
