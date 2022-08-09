package com.vikas.task.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long oid;
	private String org_name;
	private String org_type;
	private String org_location;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn
	List<Employee> employee;
	
	public Organization() {
		super();
	}
	public Organization(Long oid, String org_name, String org_type, String org_location, List<Employee> employee) {
		super();
		this.oid = oid;
		this.org_name = org_name;
		this.org_type = org_type;
		this.org_location = org_location;
		this.employee = employee;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getOrg_type() {
		return org_type;
	}
	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}
	public String getOrg_location() {
		return org_location;
	}
	public void setOrg_location(String org_location) {
		this.org_location = org_location;
	}
	
	public List<Employee> getEmployee() {
		return employee;
	}
	
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
	
}
