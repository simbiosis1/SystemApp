package org.simbiosis.systemui.client;

import org.kembang.module.client.mvp.KembangActivityMapper;
import org.simbiosis.systemui.client.branch.BranchListActivity;
import org.simbiosis.systemui.client.places.Branch;
import org.simbiosis.systemui.client.places.Role;
import org.simbiosis.systemui.client.places.SubBranch;
import org.simbiosis.systemui.client.places.User;
import org.simbiosis.systemui.client.role.RoleListActivity;
import org.simbiosis.systemui.client.subbranch.SubBranchListActivity;
import org.simbiosis.systemui.client.user.UserListActivity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper extends KembangActivityMapper {

	public AppActivityMapper(AppFactory clientFactory) {
		super(clientFactory);
	}

	@Override
	public Activity createActivity(Place place) {
		AppFactory clientFactory = (AppFactory) getClientFactory();
		if (place instanceof User) {
			return new UserListActivity((User) place, clientFactory);
		} else if (place instanceof Role) {
			return new RoleListActivity((Role) place, clientFactory);
		} else if (place instanceof Branch) {
			return new BranchListActivity((Branch) place,
					clientFactory);
		} else if (place instanceof SubBranch) {
			return new SubBranchListActivity((SubBranch) place,
					clientFactory);
		}
		return null;
	}

}
