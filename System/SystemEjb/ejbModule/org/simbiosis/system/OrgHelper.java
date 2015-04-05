package org.simbiosis.system;

import javax.persistence.EntityManager;

import org.simbiosis.system.model.Branch;
import org.simbiosis.system.model.Company;
import org.simbiosis.system.model.SubBranch;

public class OrgHelper {

	public CompanyDto createCompanyToDto(Company company) {
		CompanyDto companyDto = new CompanyDto();
		companyDto.setId(company.getId());
		companyDto.setName(company.getName());
		companyDto.setCode(company.getCode());
		companyDto.setAddress(company.getAddress());
		return companyDto;
	}

	public Company createCompanyFromDto(CompanyDto companyDto) {
		Company company = new Company();
		company.setId(companyDto.getId());
		company.setName(companyDto.getName());
		company.setCode(companyDto.getCode());
		company.setAddress(companyDto.getAddress());
		return company;
	}

	public BranchDto createBranchToDto(Branch branch) {
		BranchDto branchDto = new BranchDto();
		branchDto.setId(branch.getId());
		branchDto.setName(branch.getName());
		branchDto.setCode(branch.getCode());
		branchDto.setAddress(branch.getAddress());
		branchDto.setParam1(branch.getParam1());
		branchDto.setParam2(branch.getParam2());
		branchDto.setParam3(branch.getParam3());
		branchDto.setParam4(branch.getParam4());
		branchDto.setCompany(branch.getCompany().getId());
		return branchDto;
	}

	public Branch createBranchFromDto(EntityManager em, BranchDto dto) {
		Branch branch = new Branch();
		branch.setId(dto.getId());
		branch.setName(dto.getName());
		branch.setCode(dto.getCode());
		branch.setAddress(dto.getAddress());
		branch.setParam1(dto.getParam1());
		branch.setParam2(dto.getParam2());
		branch.setParam3(dto.getParam3());
		branch.setParam4(dto.getParam4());
		Company company = em.find(Company.class, dto.getCompany());
		branch.setCompany(company);
		return branch;
	}

	public SubBranchDto createSubBranchToDto(SubBranch subBranch) {
		SubBranchDto subBranchDto = new SubBranchDto();
		subBranchDto.setId(subBranch.getId());
		subBranchDto.setName(subBranch.getName());
		subBranchDto.setCode(subBranch.getCode());
		subBranchDto.setAddress(subBranch.getAddress());
		Branch branch = subBranch.getBranch();
		if (branch != null) {
			subBranchDto.setBranch(branch.getId());
			subBranchDto.setBranchName(branch.getCode() + " - "
					+ branch.getName());
			subBranchDto.setCompany(branch.getCompany().getId());
		}else{
			subBranchDto.setBranch(0);
			subBranchDto.setBranchName("");
			subBranchDto.setCompany(0);
		}
		return subBranchDto;
	}

	public SubBranch createSubBranchFromDto(EntityManager em,
			SubBranchDto subBranchDto) {
		SubBranch subBranch = new SubBranch();
		subBranch.setId(subBranchDto.getId());
		subBranch.setName(subBranchDto.getName());
		subBranch.setCode(subBranchDto.getCode());
		subBranch.setAddress(subBranchDto.getAddress());
		Branch branch = em.find(Branch.class, subBranchDto.getBranch());
		subBranch.setBranch(branch);
		subBranch.setCompany(branch.getCompany().getId());
		return subBranch;
	}

}
