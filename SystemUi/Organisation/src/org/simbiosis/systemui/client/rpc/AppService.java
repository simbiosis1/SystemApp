package org.simbiosis.systemui.client.rpc;

import java.util.List;

import org.kembang.module.shared.UserDv;
import org.simbiosis.systemui.shared.BranchDv;
import org.simbiosis.systemui.shared.CoaDv;
import org.simbiosis.systemui.shared.RoleDv;
import org.simbiosis.systemui.shared.RoleMenuDv;
import org.simbiosis.systemui.shared.SubBranchDv;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface AppService extends RemoteService {

	List<CoaDv> listCoaForTransaction(String key)
			throws IllegalArgumentException;

	List<BranchDv> listBranch(String key) throws IllegalArgumentException;

	List<SubBranchDv> listSubBranch(String key)
			throws IllegalArgumentException;

	List<RoleDv> listRoles(String key) throws IllegalArgumentException;

	List<RoleDv> listCompleteRoles(String key) throws IllegalArgumentException;

	List<UserDv> listUsers(String key) throws IllegalArgumentException;

	List<RoleMenuDv> listRoleMenu(Long id) throws IllegalArgumentException;

	void saveUser(String key, UserDv user) throws IllegalArgumentException;

	void saveRole(String key, RoleDv user) throws IllegalArgumentException;

	void saveBranch(String key, BranchDv dv) throws IllegalArgumentException;

	void saveSubBranch(String key, SubBranchDv dv)
			throws IllegalArgumentException;

}
