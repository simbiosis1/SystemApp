package org.simbiosis.bp.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.simbiosis.system.AuthDto;
import org.simbiosis.system.BranchDto;
import org.simbiosis.system.CompanyDto;
import org.simbiosis.system.ConfigDto;
import org.simbiosis.system.FastMenuDto;
import org.simbiosis.system.ISystem;
import org.simbiosis.system.MenuDto;
import org.simbiosis.system.RoleDto;
import org.simbiosis.system.RoleMenuDto;
import org.simbiosis.system.SessionDto;
import org.simbiosis.system.SubBranchDto;
import org.simbiosis.system.UserDto;

@Stateless
@Remote(ISystemBp.class)
public class SystemBp implements ISystemBp {

	private int LEVEL_SEE_ALL = 4;

	@EJB(lookup = "java:global/SystemEar/SystemEjb/SystemImpl")
	ISystem iSystem;

	@Override
	public CompanyDto getCompany(long id) {
		return iSystem.getCompany(id);
	}

	@Override
	public BranchDto getBranch(long id) {
		return iSystem.getBranch(id);
	}

	@Override
	public List<BranchDto> listBranch(String key) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			List<BranchDto> result = iSystem.listBranchByCompany(user
					.getCompany());
			return result;
		}
		return new ArrayList<BranchDto>();
	}

	@Override
	public List<BranchDto> listBranchByLevel(String key) {
		List<BranchDto> branchList = new ArrayList<BranchDto>();
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			if (user.getLevel() <= LEVEL_SEE_ALL) {
				BranchDto all = new BranchDto();
				all.setId(0);
				all.setName("SEMUA CABANG / KONSOLIDASI");
				all.setCode("");
				branchList.add(all);
				//
				branchList
						.addAll(iSystem.listBranchByCompany(user.getCompany()));
			} else {
				for (BranchDto dto : iSystem.listBranchByCompany(user
						.getCompany())) {
					if (dto.getId() == user.getBranch()) {
						branchList.add(dto);
					}
				}
			}
		}
		return branchList;
	}

	@Override
	public long saveUser(String key, UserDto dto) {
		UserDto user = iSystem.getUserFromSession(key);
		dto.setCompany(user.getCompany());
		if (dto.getId() != 0 && !dto.isChangePassword()) {
			UserDto oldDto = iSystem.getUser(dto.getId());
			dto.setPassword(oldDto.getPassword());
		}
		return iSystem.saveUser(dto);
	}

	@Override
	public UserDto getUser(long id) {
		return iSystem.getUser(id);
	}

	@Override
	public List<UserDto> listUsers(String key) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			if (user.getLevel() <= LEVEL_SEE_ALL) {
				return iSystem.listUsers(user.getCompany(), 0);
			}
			return iSystem.listUsers(user.getCompany(), user.getBranch());
		}
		return new ArrayList<UserDto>();
	}

	@Override
	public List<UserDto> listActiveUsers(String key) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			if (user.getLevel() <= LEVEL_SEE_ALL) {
				return iSystem.listActiveUsers(user.getCompany(), 0);
			}
			return iSystem.listActiveUsers(user.getCompany(), user.getBranch());
		}
		return new ArrayList<UserDto>();
	}

	@Override
	public long saveRole(String key, RoleDto role) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			role.setCompany(user.getCompany());
			return iSystem.saveRole(role);
		}
		return 0;
	}

	@Override
	public List<RoleDto> listRoles(String key) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			return iSystem.listRoles(user.getCompany());
		}
		return new ArrayList<RoleDto>();
	}

	@Override
	public UserDto getUserFromSession(String key) {
		return iSystem.getUserFromSession(key);
	}

	@Override
	public SessionDto getSession(String key) {
		return iSystem.getSession(key);
	}

	@Override
	public List<MenuDto> listUserMenu(String key) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			return iSystem.listUserMenuByRole(user.getRole());
		}
		return new ArrayList<MenuDto>();
	}

	@Override
	public List<MenuDto> listAllRoleMenu(long role) {
		return iSystem.listAllRoleMenu(role);
	}

	@Override
	public List<FastMenuDto> listUserFastMenu(String key) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			return iSystem.listUserFastMenu(user.getId());
		}
		return new ArrayList<FastMenuDto>();
	}

	@Override
	public String getStaticServerPath() {
		return new String("/home/simbiosis");
	}

	@Override
	public SessionDto login(String user, String password) {
		return iSystem.login(user, password);
	}

	@Override
	public void logout(String key) {
		iSystem.logout(key);
	}

	@Override
	public String getReportServerPath(String key) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			return getStaticServerPath() + "/"
					+ iSystem.getDataPath(user.getCompany());
		}
		return "";
	}

	@Override
	public long saveRoleMenu(RoleMenuDto roleMenu) {
		return iSystem.saveRoleMenu(roleMenu);
	}

	@Override
	public void deleteRoleMenu(long roleMenu) {
		iSystem.deleteRoleMenu(roleMenu);
	}

	@Override
	public List<CompanyDto> findCompanyByCode(String code) {
		return iSystem.findCompanyByCode(code);
	}

	@Override
	public List<BranchDto> findBranchByCode(long company, String code) {
		return iSystem.findBranchByCode(company, code);
	}

	@Override
	public List<FastMenuDto> listAllFastMenu(String key) {
		List<FastMenuDto> result = new ArrayList<FastMenuDto>();
		UserDto user = iSystem.getUserFromSession(key);
		Map<Long, FastMenuDto> fastMenuMap = new HashMap<Long, FastMenuDto>();
		if (user != null) {
			//
			List<FastMenuDto> fastMenus = iSystem
					.listUserFastMenu(user.getId());
			for (FastMenuDto fastMenu : fastMenus) {
				fastMenuMap.put(fastMenu.getMenuId(), fastMenu);
			}
			//
			List<MenuDto> menus = iSystem.listAllRoleMenu(user.getRole());
			for (MenuDto menu : menus) {
				if (menu.getActive() == 1) {
					FastMenuDto fmResult = new FastMenuDto();
					fmResult.setMenuId(menu.getId());
					fmResult.setUserId(user.getId());
					fmResult.setTitle(menu.getTitle());
					fmResult.setLongTitle(menu.getGrandParentTitle() + " - "
							+ menu.getParentTitle() + " - " + menu.getTitle());
					fmResult.setLink(menu.getLink());
					FastMenuDto map = fastMenuMap.get(menu.getId());
					if (map == null) {
						fmResult.setActive(0);
						fmResult.setId(0);
					} else {
						fmResult.setActive(1);
						fmResult.setId(map.getId());
					}
					result.add(fmResult);
				}
			}
		}
		return result;
	}

	@Override
	public long saveFastMenu(String key, FastMenuDto fastMenu) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			fastMenu.setUserId(user.getId());
			return iSystem.saveFastMenu(fastMenu);
		}
		return 0;
	}

	@Override
	public void deleteFastMenu(long id) {
		iSystem.deleteFastMenu(id);
	}

	@Override
	public long saveAuth(String key, AuthDto dto) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			if (dto.getId() == 0) {
				dto.setUser(user.getId());
				dto.setCompany(user.getCompany());
				dto.setBranch(user.getBranch());
			}
			return iSystem.saveAuth(dto);
		}
		return 0;
	}

	@Override
	public AuthDto getAuth(long id) {
		return iSystem.getAuth(id);
	}

	@Override
	public List<AuthDto> listAuth(String key) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			long company = user.getCompany();
			long branch = user.getBranch();
			if (user.getLevel() <= LEVEL_SEE_ALL) {
				branch = 0;
			}
			return iSystem.listAuth(company, branch, user.getLevel());
		}
		return new ArrayList<AuthDto>();
	}

	@Override
	public AuthDto getAuthByKey(String key) {
		return iSystem.getAuthByKey(key);
	}

	@Override
	public void disposeAuth(long id) {
		iSystem.disposeAuth(id);
	}

	@Override
	public void authorize(String key, AuthDto dto) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			iSystem.authorize(dto.getId(), user.getId());
		}
	}

	@Override
	public ConfigDto getConfig(String key, String config) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			return iSystem.getConfig(user.getCompany(), config);
		}
		return null;
	}

	@Override
	public void saveConfig(String key, ConfigDto config) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			iSystem.saveConfig(user.getCompany(), config);
		}
	}

	@Override
	public List<MenuDto> listUserMenuByModule(String key, String module) {
		String[] names = module.split("\\.");
		String myModule = "";
		for (int i = 0; i < names.length - 1; i++) {
			myModule += names[i] + ".";
		}
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			return iSystem.listAllRoleMenuByModule(user.getRole(), myModule);
		}
		return new ArrayList<MenuDto>();
	}

	@Override
	public long saveBranch(String key, BranchDto dto) {
		long id = 0;
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			if (dto.getId() == 0) {
				dto.setCompany(user.getCompany());
				id = iSystem.saveBranch(dto);
				// =====================================
				SubBranchDto sbDto = new SubBranchDto();
				sbDto.setId(0);
				sbDto.setCode("0");
				sbDto.setName(dto.getName());
				sbDto.setAddress(dto.getAddress());
				sbDto.setBranch(id);
				iSystem.saveSubBranch(sbDto);
			} else {
				BranchDto editBranch = iSystem.getBranch(dto.getId());
				editBranch.setCode(dto.getCode());
				editBranch.setAddress(dto.getAddress());
				editBranch.setName(dto.getName());
				editBranch.setParam1(dto.getParam1());
				editBranch.setParam2(dto.getParam2());
				editBranch.setParam3(dto.getParam3());
				id = iSystem.saveBranch(editBranch);
			}
		}
		return id;
	}

	@Override
	public List<SubBranchDto> listSubBranch(String key) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			List<SubBranchDto> result = iSystem.listSubBranch(
					user.getCompany(), user.getBranch());
			return result;
		}
		return new ArrayList<SubBranchDto>();
	}

	@Override
	public List<SubBranchDto> listSubBranchByLevel(String key) {
		return null;
	}

	@Override
	public long saveSubBranch(String key, SubBranchDto dto) {
		UserDto user = iSystem.getUserFromSession(key);
		if (user != null) {
			dto.setCompany(user.getCompany());
			return iSystem.saveSubBranch(dto);
		}
		return 0;
	}

	@Override
	public SubBranchDto getSubBranch(long id) {
		return iSystem.getSubBranch(id);
	}

}
