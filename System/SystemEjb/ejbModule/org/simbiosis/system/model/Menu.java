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
@Table(name = "SYS_MENU")
@NamedQueries({ @NamedQuery(name = "getMenuByPlace", query = "select x from Menu x where x.place=:place"),
	@NamedQuery(name = "listAllMenu", query = "select x from Menu x where x.level=3 order by x.parent.parent, x.parent")})
public class Menu {
	@Id
	@Column(name = "MEN_ID")
	@GeneratedValue(strategy = TABLE, generator = "gen_sys_menu")
	@TableGenerator(name = "gen_sys_menu", allocationSize = 1, pkColumnValue = "gen_sys_menu")
	long id;

	@Column(name = "MEN_TITLE", length = 100)
	String title;
	@Column(name = "MEN_PLACE", length = 200)
	String place;
	@Column(name = "MEN_MODULE", length = 50)
	String module;
	@Column(name = "MEN_LEVEL")
	int level;
	@ManyToOne
	@JoinColumn(name = "PARENT_ID", referencedColumnName = "MEN_ID")
	Menu parent;

	public Menu() {
		super();
		id = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
}
