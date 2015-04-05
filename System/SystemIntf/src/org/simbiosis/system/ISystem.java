package org.simbiosis.system;

import java.util.List;

public interface ISystem {
	//
	CompanyDto getCompany(long id);

	List<CompanyDto> findCompanyByCode(String code);

	long saveBranch(BranchDto dto);

	BranchDto getBranch(long id);

	long saveSubBranch(SubBranchDto dto);

	SubBranchDto getSubBranch(long id);

	List<BranchDto> findBranchByCode(long company, String code);

	List<CompanyDto> listCompany();

	List<BranchDto> listBranchByCompany(long company);

	List<SubBranchDto> listSubBranch(long company, long branch);

	//
	long saveUser(UserDto userDto);

	UserDto getUser(long id);

	List<UserDto> listUsers(long company, long branch);

	List<UserDto> listActiveUsers(long company, long branch);

	//
	List<RoleDto> listRoles(long company);

	long saveRole(RoleDto role);

	//
	SessionDto login(String user, String password);

	void logout(String key);

	//
	SessionDto getSession(String key);

	UserDto getUserFromSession(String key);

	//

	long saveMenu(MenuDto menuDto);

	MenuDto getMenu(long id);

	MenuDto getMenuByPlace(String place);

	//

	long saveFastMenu(FastMenuDto fastMenuDto);

	void deleteFastMenu(long id);

	List<FastMenuDto> listUserFastMenu(long userId);

	//

	List<MenuDto> listUserMenuByRole(long roleId);

	//

	long saveCurrency(CurrencyDto currencyDto);

	CurrencyDto getCurrency(long id);

	List<CurrencyDto> listCurrencty();

	//
	void saveConfig(long company, ConfigDto config);

	ConfigDto getConfig(long company, String key);

	String getDataPath(long company);

	//
	List<MenuDto> listAllRoleMenu(long role);

	List<MenuDto> listAllRoleMenuByModule(long role, String module);

	void deleteRoleMenu(long roleMenu);

	long saveRoleMenu(RoleMenuDto roleMenu);

	//

	long saveAuth(AuthDto auth);

	AuthDto getAuth(long id);

	AuthDto getAuthByKey(String key);

	List<AuthDto> listAuth(long company, long branch, int level);

	void disposeAuth(long id);

	void authorize(long id, long authorizer);

}
