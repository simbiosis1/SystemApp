package org.simbiosis.systemui.client;

import org.kembang.module.client.mvp.KembangClientFactoryImpl;
import org.simbiosis.systemui.client.branch.BranchList;
import org.simbiosis.systemui.client.branch.IBranch;
import org.simbiosis.systemui.client.role.IRoleList;
import org.simbiosis.systemui.client.role.RoleList;
import org.simbiosis.systemui.client.subbranch.ISubBranch;
import org.simbiosis.systemui.client.subbranch.SubBranchList;
import org.simbiosis.systemui.client.user.IUserList;
import org.simbiosis.systemui.client.user.UserList;

public class AppFactoryImpl extends KembangClientFactoryImpl implements
		AppFactory {

	static final UserList USER_EDITOR = new UserList();
	static final RoleList ROLE_LIST = new RoleList();
	static final BranchList BRANCH = new BranchList();
	static final SubBranchList SUBBRANCH = new SubBranchList();

	@Override
	public IUserList getUserEditor() {
		return USER_EDITOR;
	}

	@Override
	public IRoleList getRoleEditor() {
		return ROLE_LIST;
	}

	@Override
	public IBranch getBranch() {
		return BRANCH;
	}

	@Override
	public ISubBranch getSubBranch() {
		return SUBBRANCH;
	}

}
