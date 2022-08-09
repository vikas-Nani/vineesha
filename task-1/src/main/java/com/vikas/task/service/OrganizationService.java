package com.vikas.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikas.task.model.Organization;
import com.vikas.task.repository.OrganizationRepository;

@Service
public class OrganizationService implements OrganizationServiceInterface {
	@Autowired
	private OrganizationRepository orgrepo;
	
	@Override
	public List<Organization> getallorg() {
		
		return orgrepo.findAll();
	}

	@Override
	public void saveOrg(Organization org) {
		this.orgrepo.save(org);

	}

	@Override
	public void updateOrganization(Long id, Organization org) {
		Organization orgupdate=orgrepo.findById(id).get();
		orgupdate.setOrg_name(org.getOrg_name());
		orgupdate.setOrg_type(org.getOrg_type());
		orgupdate.setOrg_location(org.getOrg_location());
		orgupdate.setEmployee(org.getEmployee());
		orgrepo.save(orgupdate);

	}

	@Override
	public Organization getbyid(Long id) {
		
		return orgrepo.findById(id).get();
	}

	@Override
	public void deleteOrganization(Long id) {
		this.orgrepo.deleteById(id);

	}

}
