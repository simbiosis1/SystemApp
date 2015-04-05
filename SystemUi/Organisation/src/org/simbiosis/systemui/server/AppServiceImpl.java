package org.simbiosis.systemui.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;

import org.kembang.module.shared.UserDv;
import org.simbiosis.bp.gl.IGlBp;
import org.simbiosis.bp.system.ISystemBp;
import org.simbiosis.gl.model.Coa;
import org.simbiosis.system.BranchDto;
import org.simbiosis.system.MenuDto;
import org.simbiosis.system.RoleDto;
import org.simbiosis.system.RoleMenuDto;
import org.simbiosis.system.SubBranchDto;
import org.simbiosis.system.UserDto;
import org.simbiosis.systemui.client.rpc.AppService;
import org.simbiosis.systemui.shared.BranchDv;
import org.simbiosis.systemui.shared.CoaDv;
import org.simbiosis.systemui.shared.LevelTypeEnum;
import org.simbiosis.systemui.shared.RoleDv;
import org.simbiosis.systemui.shared.RoleMenuDv;
import org.simbiosis.systemui.shared.SubBranchDv;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class AppServiceImpl extends RemoteServiceServlet implements AppService {

	@EJB(lookup = "java:global/SystemBpEar/SystemBpEjb/SystemBp")
	ISystemBp iSystem;
	@EJB(lookup = "java:global/GlBpEar/GlBpEjb/GlBp")
	IGlBp glBp;

	public AppServiceImpl() {
	}

	private CoaDv createCoaDv(Coa coa) {
		CoaDv coaDv = new CoaDv();
		coaDv.setId(coa.getId());
		coaDv.setCode(coa.getCode());
		coaDv.setDescription(coa.getDescription());
		return coaDv;
	}

	@Override
	public List<CoaDv> listCoaForTransaction(String key) {
		List<CoaDv> returnList = new ArrayList<CoaDv>();
		for (Coa coaDto : glBp.listCoaForTransaction(key)) {
			returnList.add(createCoaDv(coaDto));
		}
		return returnList;
	}

	@Override
	public List<UserDv> listUsers(String key) throws IllegalArgumentException {
		List<UserDv> result = new ArrayList<UserDv>();
		List<UserDto> users = iSystem.listUsers(key);
		int nr = 1;
		for (UserDto user : users) {
			UserDv dv = new UserDv();
			dv.setNr(nr++);
			dv.setId(user.getId());
			dv.setBranch(user.getBranch());
			if (dv.getBranch() == 0) {
				dv.setBranchName("SEMUA CABANG");
			} else {
				dv.setBranchName(user.getBranchName());
			}
			dv.setSubBranch(user.getSubBranch());
			if (dv.getSubBranch() == 0) {
				dv.setSubBranchName("SEMUA KANTOR KAS");
			} else {
				dv.setSubBranchName(user.getSubBranchName());
			}
			dv.setName(user.getName());
			dv.setRealName(user.getRealName());
			dv.setEmail(user.getEmail());
			dv.setRole(user.getRole());
			dv.setRoleName(user.getRoleName());
			dv.setLevel(user.getLevel());
			dv.setStrLevel(LevelTypeEnum.valueToString(dv.getLevel()));
			dv.setActive(user.getActive() == 1);
			dv.setStrActive(user.getActive() == 1 ? "AKTIF" : "NON AKTIF");
			dv.setChangePassword(false);
			dv.setPassword("");
			dv.setConfirmPassword("");
			result.add(dv);
		}
		return result;
	}

	private RoleDv createRoleDvFromDto(RoleDto dto, int nr) {
		RoleDv dv = new RoleDv();
		dv.setNr(nr);
		dv.setId(dto.getId());
		dv.setName(dto.getName());
		dv.setDescription(dto.getDescription());
		return dv;
	}

	@Override
	public List<RoleDv> listRoles(String key) throws IllegalArgumentException {
		List<RoleDv> result = new ArrayList<RoleDv>();
		List<RoleDto> roles = iSystem.listRoles(key);
		int nr = 1;
		for (RoleDto role : roles) {
			result.add(createRoleDvFromDto(role, nr++));
		}
		return result;
	}

	@Override
	public List<RoleDv> listCompleteRoles(String key)
			throws IllegalArgumentException {
		List<RoleDv> result = new ArrayList<RoleDv>();
		List<RoleDto> roles = iSystem.listRoles(key);
		int nr = 1;
		for (RoleDto role : roles) {
			RoleDv dv = createRoleDvFromDto(role, nr++);
			dv.getRoleMenus().addAll(listRoleMenu(role.getId()));
			result.add(dv);
		}
		return result;
	}

	@Override
	public void saveUser(String key, UserDv dv) throws IllegalArgumentException {
		UserDto dto = new UserDto();
		dto.setId(dv.getId());
		dto.setBranch(dv.getBranch());
		dto.setSubBranch(dv.getSubBranch());
		dto.setName(dv.getName());
		dto.setRealName(dv.getRealName().toUpperCase());
		dto.setEmail(dv.getEmail() != null ? dv.getEmail().toUpperCase() : "");
		dto.setRole(dv.getRole());
		dto.setLevel(dv.getLevel());
		dto.setActive(dv.isActive() ? 1 : 0);
		dto.setChangePassword(dv.isChangePassword());
		dto.setPassword(dv.getPassword());
		iSystem.saveUser(key, dto);
	}

	@Override
	public List<BranchDv> listBranch(String key)
			throws IllegalArgumentException {
		List<BranchDv> result = new ArrayList<BranchDv>();
		List<BranchDto> branches = iSystem.listBranch(key);
		int nr = 1;
		for (BranchDto branch : branches) {
			BranchDv dv = new BranchDv();
			dv.setNr(nr++);
			dv.setId(branch.getId());
			dv.setName(branch.getName());
			dv.setCode(branch.getCode());
			dv.setAddress(branch.getAddress());
			dv.setCoa1(branch.getParam1());
			Coa coa = glBp.getCoa(branch.getParam1());
			dv.setStrCoa1(coa.getCode() + " - " + coa.getDescription());
			dv.setCoa2(branch.getParam2());
			coa = glBp.getCoa(branch.getParam2());
			dv.setStrCoa2(coa.getCode() + " - " + coa.getDescription());
			result.add(dv);
		}
		return result;
	}

	@Override
	public List<SubBranchDv> listSubBranch(String key)
			throws IllegalArgumentException {
		List<SubBranchDv> result = new ArrayList<SubBranchDv>();
		List<SubBranchDto> subBranches = iSystem.listSubBranch(key);
		int nr = 1;
		for (SubBranchDto subBranch : subBranches) {
			SubBranchDv dv = new SubBranchDv();
			dv.setNr(nr++);
			dv.setId(subBranch.getId());
			dv.setCode(subBranch.getCode());
			dv.setName(subBranch.getName());
			dv.setAddress(subBranch.getAddress());
			dv.setBranch(subBranch.getBranch());
			dv.setStrBranch(subBranch.getBranchName());
			result.add(dv);
		}
		return result;
	}

	@Override
	public void saveRole(String key, RoleDv role)
			throws IllegalArgumentException {
		RoleDto dto = new RoleDto();
		dto.setId(role.getId());
		dto.setName(role.getName().toUpperCase());
		dto.setDescription(role.getDescription() != null ? role
				.getDescription().toUpperCase() : dto.getName());
		long roleId = iSystem.saveRole(key, dto);
		// Save role menu
		for (RoleMenuDv roleMenu : role.getRoleMenus()) {
			// 1. Hapus yang rolemenu status tidak activ dan rolemenu id<>0
			if (!roleMenu.getStatus() && roleMenu.getId() != 0) {
				iSystem.deleteRoleMenu(roleMenu.getId());
			}

			// 2. Simpan yang rolemenu status active dan rolemenu id=0
			if (roleMenu.getStatus() && roleMenu.getId() == 0) {
				RoleMenuDto newRM = new RoleMenuDto();
				newRM.setMenuId(roleMenu.getMenu());
				newRM.setRoleId(roleId);
				iSystem.saveRoleMenu(newRM);
			}
		}
	}

	@Override
	public List<RoleMenuDv> listRoleMenu(Long id)
			throws IllegalArgumentException {
		List<RoleMenuDv> result = new ArrayList<RoleMenuDv>();
		List<MenuDto> allRoleMenu = iSystem.listAllRoleMenu(id);
		for (MenuDto availMenu : allRoleMenu) {
			RoleMenuDv roleMenu = new RoleMenuDv();
			roleMenu.setMenu(availMenu.getId());
			roleMenu.setId(availMenu.getOtherId());
			roleMenu.setName(availMenu.getGrandParentTitle() + " - "
					+ availMenu.getParentTitle() + " - " + availMenu.getTitle());
			roleMenu.setStatus(availMenu.getActive() == 1);
			result.add(roleMenu);
		}
		// Sortir
		Collections.sort(result, new Comparator<RoleMenuDv>() {

			@Override
			public int compare(RoleMenuDv arg0, RoleMenuDv arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
		//
		int i = 1;
		for (RoleMenuDv roleMenu : result) {
			roleMenu.setNr(i++);
		}
		return result;
	}

	@Override
	public void saveBranch(String key, BranchDv dv)
			throws IllegalArgumentException {
		BranchDto dto = new BranchDto();
		dto.setId(dv.getId());
		dto.setName(dv.getName() != null ? dv.getName().toUpperCase() : "");
		dto.setCode(dv.getCode() != null ? dv.getCode().toUpperCase() : "");
		dto.setAddress(dv.getAddress() != null ? dv.getAddress().toUpperCase()
				: "");
		dto.setParam1(dv.getCoa1());
		dto.setParam2(dv.getCoa2());
		iSystem.saveBranch(key, dto);
	}

	@Override
	public void saveSubBranch(String key, SubBranchDv dv)
			throws IllegalArgumentException {
		SubBranchDto dto = new SubBranchDto();
		dto.setId(dv.getId());
		dto.setCode(dv.getCode() != null ? dv.getCode().toUpperCase() : "");
		dto.setName(dv.getName() != null ? dv.getName().toUpperCase() : "");
		dto.setAddress(dv.getAddress() != null ? dv.getAddress().toUpperCase()
				: "");
		dto.setBranch(dv.getBranch());
		iSystem.saveSubBranch(key, dto);
	}

}
