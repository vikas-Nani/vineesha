package com.vikas.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikas.task.model.Employee;
import com.vikas.task.repository.EmployeeRepository;
@Service

public class EmployeeService implements EmployeeServiceInterface {
	

	@Autowired
	private EmployeeRepository emprepo;
	
	
	@Override
	public List<Employee> getallemp() {
		
		return emprepo.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.emprepo.save(employee);

	}

	@Override
	public void updateEmployee(Long id,Employee employee) {
		Employee emp=emprepo.findById(id).get();
		emp.setEmp_name(employee.getEmp_name());
		emp.setEmp_role(employee.getEmp_role());
		emp.setEmp_team(employee.getEmp_team());
		emprepo.save(emp);

	}

	@Override
	public Employee getbyid(Long id) {
		
		return emprepo.findById(id).get();
	}

	@Override
	public void deleteEmployee(Long id) {
		this.emprepo.deleteById(id);

	}

}
