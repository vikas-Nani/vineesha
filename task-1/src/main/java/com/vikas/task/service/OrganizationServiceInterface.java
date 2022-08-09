package com.vikas.task.service;

import java.util.List;


import com.vikas.task.model.Organization;

public interface OrganizationServiceInterface {
	List<Organization> getallorg();
	void saveOrg(Organization org);
	void updateOrganization(Long id,Organization org);
	Organization getbyid(Long id);
	void deleteOrganization(Long id);


}
