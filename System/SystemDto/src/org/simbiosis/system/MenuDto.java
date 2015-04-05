package org.simbiosis.system;

import java.io.Serializable;

public class MenuDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3584264981436579558L;
	long id;
	long parentId;
	String title;
	String parentTitle;
	String grandParentTitle;
	String link;
	String module;
	String place;
	int level;
	long otherId;
	int active;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

	public String getParentTitle() {
		return parentTitle;
	}

	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}

	public String getGrandParentTitle() {
		return grandParentTitle;
	}

	public void setGrandParentTitle(String grandParentTitle) {
		this.grandParentTitle = grandParentTitle;
	}

	public long getOtherId() {
		return otherId;
	}

	public void setOtherId(long otherId) {
		this.otherId = otherId;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}
