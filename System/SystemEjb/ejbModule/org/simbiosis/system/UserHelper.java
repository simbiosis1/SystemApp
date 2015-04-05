package org.simbiosis.system;

import javax.persistence.EntityManager;

import org.simbiosis.system.model.Branch;
import org.simbiosis.system.model.Company;
import org.simbiosis.system.model.Role;
import org.simbiosis.system.model.SubBranch;
import org.simbiosis.system.model.User;

public class UserHelper {

	public UserDto createUserToDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setRealName(user.getRealName());
		dto.setEmail(user.getEmail());
		dto.setLevel(user.getLevel());
		dto.setCompany((user.getCompany() != null) ? user.getCompany().getId()
				: 0);
		Branch branch = user.getBranch();
		if (branch != null) {
			dto.setBranch(branch.getId());
			dto.setBranchName(branch.getCode() + " - " + branch.getName());
		} else {
			dto.setBranch(0);
			dto.setBranchName("");
		}
		SubBranch subBranch = user.getSubBranch();
		if (subBranch != null) {
			dto.setSubBranch(subBranch.getId());
			dto.setSubBranchName(subBranch.getCode() + " - "
					+ subBranch.getName());
		} else {
			dto.setSubBranch(0);
			dto.setSubBranchName("");
		}
		dto.setRole(user.getRole().getId());
		dto.setRoleName(user.getRole().getName());
		dto.setActive(user.getActive());
		dto.setPassword(user.getPassword());
		return dto;
	}

	User createUserFromDto(EntityManager em, UserDto dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setRealName(dto.getRealName());
		user.setEmail(dto.getEmail());
		user.setLevel(dto.getLevel());
		Company company = em.find(Company.class, dto.getCompany());
		user.setCompany(company);
		Branch branch = em.find(Branch.class, dto.getBranch());
		user.setBranch(branch);
		SubBranch subBranch = em.find(SubBranch.class, dto.getSubBranch());
		user.setSubBranch(subBranch);
		Role role = em.find(Role.class, dto.getRole());
		user.setRole(role);
		user.setActive(dto.getActive());
		user.setPassword(dto.getPassword());
		return user;
	}

}
