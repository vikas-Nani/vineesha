package com.vikas.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikas.task.model.Organization;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
