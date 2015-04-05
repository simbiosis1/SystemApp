package org.simbiosis.systemui.client.rpc;

import java.util.List;

import org.kembang.module.shared.UserDv;
import org.simbiosis.systemui.shared.BranchDv;
import org.simbiosis.systemui.shared.CoaDv;
import org.simbiosis.systemui.shared.RoleDv;
import org.simbiosis.systemui.shared.RoleMenuDv;
import org.simbiosis.systemui.shared.SubBranchDv;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AppServiceAsync {

	void listRoles(String key, AsyncCallback<List<RoleDv>> callback);

	void listUsers(String key, AsyncCallback<List<UserDv>> callback);

	void saveUser(String key, UserDv user, AsyncCallback<Void> callback);

	void listBranch(String key, AsyncCallback<List<BranchDv>> callback);

	void saveRole(String key, RoleDv user, AsyncCallback<Void> callback);

	void listRoleMenu(Long id, AsyncCallback<List<RoleMenuDv>> callback);

	void listCompleteRoles(String key, AsyncCallback<List<RoleDv>> callback);

	void listSubBranch(String key, AsyncCallback<List<SubBranchDv>> callback);

	void saveBranch(String key, BranchDv dv, AsyncCallback<Void> callback);

	void listCoaForTransaction(String key, AsyncCallback<List<CoaDv>> callback);

	void saveSubBranch(String key, SubBranchDv dv, AsyncCallback<Void> callback);

}
