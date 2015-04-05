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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SYS_ROLEMENU")
@NamedQueries({
	@NamedQuery(name = "listRoleMenu", query = "select x from RoleMenu x where x.role.id=:roleId"),
	@NamedQuery(name = "isRoleMenuExist", query = "select count(x) from RoleMenu x where x.role.id=:roleId and x.menu.id=:menuId"),
	@NamedQuery(name = "isRolePlaceExist", query = "select count(x) from RoleMenu x where x.role.id=:roleId and x.menu.place=:place") })
public class RoleMenu {
	@Id
	@Column(name = "RLM_ID")
	@GeneratedValue(strategy = TABLE, generator = "gen_sys_rolemenu")
	@TableGenerator(name = "gen_sys_rolemenu", allocationSize = 1, pkColumnValue = "gen_sys_rolemenu")
	long id;
	@ManyToOne
	@JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID")
	Role role;
	@OneToOne
	@JoinColumn(name = "MEN_ID", referencedColumnName = "MEN_ID")
	Menu menu;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
