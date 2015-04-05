package org.simbiosis.system;

import java.io.Serializable;

public class RoleMenuDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8899077355546889588L;
	long id;
	long roleId;
	long menuId;

	public RoleMenuDto() {
		super();
		id = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

}
