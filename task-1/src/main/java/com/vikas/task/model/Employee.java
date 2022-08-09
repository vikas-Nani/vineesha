package com.vikas.task.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long empid;
	@NonNull
	private String emp_name;
	private String emp_team;
	private String emp_role;
	 
	public Employee() {
		super();
	}
	public Employee(Long empid, String emp_name, String emp_team, String emp_role) {
		super();
		this.empid = empid;
		this.emp_name = emp_name;
		this.emp_team = emp_team;
		this.emp_role = emp_role;
	}
	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_team() {
		return emp_team;
	}
	public void setEmp_team(String emp_team) {
		this.emp_team = emp_team;
	}
	public String getEmp_role() {
		return emp_role;
	}
	public void setEmp_role(String emp_role) {
		this.emp_role = emp_role;
	}
	
	
	

}
