package org.simbiosis.systemui.client;

import org.kembang.module.client.mvp.KembangClientFactory;
import org.simbiosis.systemui.client.branch.IBranch;
import org.simbiosis.systemui.client.role.IRoleList;
import org.simbiosis.systemui.client.subbranch.ISubBranch;
import org.simbiosis.systemui.client.user.IUserList;

public interface AppFactory extends KembangClientFactory {

	IUserList getUserEditor();

	IRoleList getRoleEditor();

	IBranch getBranch();

	ISubBranch getSubBranch();

}
