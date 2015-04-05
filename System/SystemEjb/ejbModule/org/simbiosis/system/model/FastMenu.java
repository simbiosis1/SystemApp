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
@Table(name = "SYS_FASTMENU")
@NamedQueries({ @NamedQuery(name = "listFastMenuByUser", query = "select x from FastMenu x where x.user.id=:userId") })
public class FastMenu {
	@Id
	@GeneratedValue(strategy = TABLE, generator = "gen_sys_fastmenu")
	@TableGenerator(name = "gen_sys_fastmenu", allocationSize = 1, pkColumnValue = "gen_sys_fastmenu")
	@Column(name = "FSM_ID")
	long id;
	@ManyToOne
	@JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID")
	User user;
	@OneToOne
	@JoinColumn(name = "MEN_ID", referencedColumnName = "MEN_ID")
	Menu menu;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
