package org.simbiosis.bp.system;

import java.util.List;

import org.simbiosis.system.AuthDto;
import org.simbiosis.system.BranchDto;
import org.simbiosis.system.CompanyDto;
import org.simbiosis.system.ConfigDto;
import org.simbiosis.system.FastMenuDto;
import org.simbiosis.system.MenuDto;
import org.simbiosis.system.RoleDto;
import org.simbiosis.system.RoleMenuDto;
import org.simbiosis.system.SessionDto;
import org.simbiosis.system.SubBranchDto;
import org.simbiosis.system.UserDto;

public interface ISystemBp {
	//
	CompanyDto getCompany(long id);

	List<CompanyDto> findCompanyByCode(String code);

	BranchDto getBranch(long id);

	List<BranchDto> findBranchByCode(long company, String code);

	//
	List<BranchDto> listBranch(String key);

	List<BranchDto> listBranchByLevel(String key);

	List<SubBranchDto> listSubBranch(String key);

	List<SubBranchDto> listSubBranchByLevel(String key);

	SubBranchDto getSubBranch(long id);

	//
	SessionDto getSession(String key);

	//
	long saveUser(String key, UserDto user);

	UserDto getUser(long id);

	List<UserDto> listUsers(String key);

	List<UserDto> listActiveUsers(String key);

	UserDto getUserFromSession(String key);

	//
	SessionDto login(String user, String password);

	void logout(String key);

	//

	ConfigDto getConfig(String key, String config);

	void saveConfig(String key, ConfigDto config);

	//
	List<MenuDto> listUserMenu(String key);

	List<MenuDto> listUserMenuByModule(String key, String module);

	List<FastMenuDto> listUserFastMenu(String key);

	List<FastMenuDto> listAllFastMenu(String key);

	long saveFastMenu(String key, FastMenuDto fastMenu);

	void deleteFastMenu(long id);

	//
	long saveRole(String key, RoleDto role);

	List<RoleDto> listRoles(String key);

	//
	String getStaticServerPath();

	String getReportServerPath(String key);

	//

	long saveBranch(String key, BranchDto dto);

	long saveSubBranch(String key, SubBranchDto dto);

	//

	long saveRoleMenu(RoleMenuDto roleMenu);

	void deleteRoleMenu(long roleMenu);

	List<MenuDto> listAllRoleMenu(long role);

	//

	long saveAuth(String key, AuthDto dto);

	AuthDto getAuth(long id);

	AuthDto getAuthByKey(String key);

	List<AuthDto> listAuth(String key);

	void disposeAuth(long id);

	void authorize(String key, AuthDto dto);

}
