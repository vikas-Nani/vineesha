package com.vikas.task.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vikas.task.model.Employee;
import com.vikas.task.model.Organization;
import com.vikas.task.service.EmployeeService;
import com.vikas.task.service.OrganizationService;

@RestController
@Controller
public class MainController {
	@Autowired
	private OrganizationService orgservice;
	
	
	
	@Autowired
	private EmployeeService empservice;
	// displaying all organizations

	@GetMapping("/organizations")
	public List<Organization> list() {
		return orgservice.getallorg();
	}
	
	@GetMapping("/employees")
	public List<Employee> emplist(){
		return empservice.getallemp();
	}
	@GetMapping("/org/{id}")
	public Organization org(@PathVariable Long id){
		return orgservice.getbyid(id);
	}
	@GetMapping("/employeesof/{id}")
	public List<Employee> getempbycid(@PathVariable Long id){
		Organization o = orgservice.getbyid(id);
		List<Employee> emp = o.getEmployee();
		return emp;
	}
	// creating a organisation

	@PostMapping("/saveorganization")
	public void saveemp(@RequestBody Organization org) {
		orgservice.saveOrg(org);

	}
	
	@PostMapping("/saveemployee/{id1}/{id2}")
	public void saveempw(@PathVariable Long id1,@PathVariable Long id2,@RequestBody Employee emp) {

		Organization o = orgservice.getbyid(id1);
		
		Organization o1 = orgservice.getbyid(id2);
	    List<Employee> employee=o.getEmployee();
	    List<Employee> employee1=o1.getEmployee();
	    employee.add(emp);
	    employee1.add(emp);
		orgservice.saveOrg(o);
		orgservice.saveOrg(o1);
		
		

	}
	//linking employees to multiple organizations
	
	@PutMapping("/newemp/{oid}/{empid}")
	public void employeeorg(@PathVariable Long oid,@PathVariable Long empid) {
		
				Organization o = orgservice.getbyid(oid);
				
				Employee e=empservice.getbyid(empid);
					List<Employee> emp = o.getEmployee();
					emp.add(e);
					orgservice.saveOrg(o);
				
		
		
	}
	
	

	// add employee to particular organisation

	@PostMapping("/saveemptoorg/{id}")
	public void saveempintoorg(@PathVariable Long id, @RequestBody Employee employee) throws Emptyvalueexception {
		
		
			Organization o = orgservice.getbyid(id);
			
			  if(employee.getEmp_name().isEmpty()||employee.getEmp_name().length()==0||
			  employee.getEmp_name()==null) { throw new
			  Emptyvalueexception(1,"please enter a valid name"); }
			 
		List<Employee> emp = o.getEmployee();
		emp.add(employee);
		o.setEmployee(emp);
		orgservice.saveOrg(o);
		
	}
	// delete organization

	@DeleteMapping("/deleteorg/{id}")
	public void deleteorg(@PathVariable Long id) {
		
		orgservice.deleteOrganization(id);}
		
	

	// delete employee

	@DeleteMapping("/deleteemp/{id}")
	public void delete(@PathVariable Long id) {
		
		empservice.deleteEmployee(id);
		
	}

	// update organization

	@PutMapping("/updateOrg/{id}")
	public void updateorg(@PathVariable Long id, @RequestBody Organization org) {
		
		Organization o = orgservice.getbyid(id);
		o.setOrg_location(org.getOrg_location());
		o.setOrg_name(org.getOrg_name());
		o.setOrg_type(org.getOrg_type());
		//o.setEmployee(org.getEmployee());
		orgservice.saveOrg(o);}
	@PutMapping("/updateOrgby/{id}")
	public void updateorgby(@PathVariable Long id, @RequestBody Organization org) {
		
		Organization o = orgservice.getbyid(id);
		o.setOrg_location(org.getOrg_location());
		o.setOrg_name(org.getOrg_name());
		o.setOrg_type(org.getOrg_type());
		o.setEmployee(org.getEmployee());
		orgservice.saveOrg(o);}
		
	// update employee

//	@PutMapping("/updateemp/{id}")
	@RequestMapping(value="/updateemp/{id}",method=RequestMethod.PUT)
	public void updateemployee(@PathVariable Long id, @RequestBody Employee employee) {
		System.out.println(employee.getEmp_name()+" "+employee.getEmp_role());
		Employee emp = empservice.getbyid(id);
		emp.setEmp_name(employee.getEmp_name());
		emp.setEmp_role(employee.getEmp_role());
		emp.setEmp_team(employee.getEmp_team());
		empservice.saveEmployee(emp);}
		
	@PutMapping("/deleteempinorg/{oid}/{empid}")
	public void deleteempfromorg(@PathVariable Long oid,@PathVariable Long empid) throws NoValuePresent {
		
		Organization o=orgservice.getbyid(oid);
		try {
		Employee e=empservice.getbyid(empid);
		o.getEmployee().remove(e);
		orgservice.saveOrg(o);
		}
		catch(Exception e) 
		{
			throw new NoValuePresent(600,"no employee with given id");
		}
		}
		
		/*
		 * for(Employee x:o.getEmployee()) { if(x.getEmpid()==empid) {
		 * o.getEmployee().remove(x); break; }
		 * 
		 * }
		 */
	@GetMapping("/emp/{id}")
	public Employee get(@PathVariable Long id) {
		return empservice.getbyid(id);
	}
	@PutMapping("/deleteemployee/{id}/{oid}")
	public void deleteemployeeby(@PathVariable Long id, @PathVariable Long oid) {
		
		Organization o = orgservice.getbyid(oid);
		o.getEmployee().remove(empservice.getbyid(id));
		orgservice.saveOrg(o);
		empservice.deleteEmployee(id);
		}
		
		
	}

