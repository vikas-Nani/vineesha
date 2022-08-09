package com.vikas.task.service;

import java.util.List;

import com.vikas.task.model.Employee;

public interface EmployeeServiceInterface {
	List<Employee> getallemp();
	void saveEmployee(Employee employee);
	void updateEmployee(Long id,Employee employee);
	Employee getbyid(Long id);
	void deleteEmployee(Long id);

}
