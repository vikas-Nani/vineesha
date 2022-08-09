package com.vikas.projects.Wicket_application_spring_2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.openjson.JSONArray;
import com.github.openjson.JSONObject;

@Service
public class ServiceClass {

	RestTemplate rt = new RestTemplate();
	HttpClient client = HttpClient.newHttpClient();

	public List<Employee> getemployeebyid(Long id) {
		List<Employee> list2 = new ArrayList<Employee>();
		try {
			ResponseEntity<Employee> re = rt.getForEntity("http://localhost:8081/emp/" + id, Employee.class);
			list2.add(re.getBody());
			return list2;
		} catch (Exception e) {
			System.out.println(e);
			list2.clear();
			return list2;
		}
	}

	public List<Organization> getorgbyid(Long id) {
		List<Organization> list3 = new ArrayList<Organization>();
		ResponseEntity<Organization> re = rt.getForEntity("http://localhost:8081/org/" + id, Organization.class);
		list3.add(re.getBody());
		return list3;

	}

	public List<Employee> getallEmployees() throws IOException, InterruptedException {
		List<Employee> list1 = new ArrayList<Employee>();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8081/employees")).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JSONArray ja = new JSONArray(response.body());
		for (int j = 0; j < ja.length(); j++) {
			JSONObject jp = new JSONObject(ja.get(j));
			list1.add(new Employee(jp.getLong("empid"), jp.getString("emp_name"), jp.getString("emp_team"),
					jp.getString("emp_role")));

		}

		return list1;
	}

	public List<Employee> getallEmployeesoforg(Long id) throws IOException, InterruptedException {
		List<Employee> list4 = new ArrayList<Employee>();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8081/employeesof/" + id))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JSONArray ja = new JSONArray(response.body());
		for (int j = 0; j < ja.length(); j++) {
			JSONObject jp = new JSONObject(ja.get(j));
			list4.add(new Employee(jp.getLong("empid"), jp.getString("emp_name"), jp.getString("emp_team"),
					jp.getString("emp_role")));

		}

		return list4;
	}

	public List<Organization> getallOrganizations() throws IOException, InterruptedException {
		List<Organization> list = new ArrayList<>();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8081/organizations")).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JSONArray ja = new JSONArray(response.body());
		for (int i = 0; i < ja.length(); i++) {
			JSONObject jo = new JSONObject(ja.get(i));

			list.add(new Organization(jo.getLong("oid"), jo.getString("org_name"), jo.getString("org_type"),
					jo.getString("org_location")));
		}
		return list;
	}

	public String addemployee(Long oid, Employee emp) {
		try {
			HttpEntity<Employee> request2 = new HttpEntity<Employee>(emp);
			ResponseEntity<Employee> response2 = rt.exchange("http://localhost:8081/saveemptoorg/" + oid,
					HttpMethod.POST, request2, Employee.class);
			return "success";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String addOrganization(Organization org) {
		try {
			HttpEntity<Organization> request2 = new HttpEntity<Organization>(org);
			ResponseEntity<Organization> response2 = rt.exchange("http://localhost:8081/saveorganization",
					HttpMethod.POST, request2, Organization.class);
			return "success";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String deleteemployee(Long id) {
		try {
			rt.delete("http://localhost:8081/deleteorg/" + id);
			return "success";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public void updateorg(Long id, Long empid) {
		ResponseEntity<Employee> up = rt.getForEntity("http://localhost:8081/emp/" + empid, Employee.class);
		ResponseEntity<Organization> op = rt.getForEntity("http://localhost:8081/org/" + id, Organization.class);

		List<Employee> emp = new ArrayList<Employee>();
		System.out.println(op.getBody().getEmployee());
		for (Employee e : op.getBody().getEmployee()) {
			if (e.getEmpid() != up.getBody().getEmpid()) {
				emp.add(e);
			}
		}
		System.out.println(emp);
		op.getBody().setEmployee(emp);

		HttpEntity<Organization> requestUpdate = new HttpEntity<Organization>(op.getBody());
		rt.exchange("http://localhost:8081/updateOrgby/" + id, HttpMethod.PUT, requestUpdate, Employee.class);

	}

	public void updateorgdetails(Long id, Organization org) {
		HttpEntity<Organization> requestUpdate = new HttpEntity<Organization>(org);
		rt.exchange("http://localhost:8081//updateOrg/" + id, HttpMethod.PUT, requestUpdate, Organization.class);

	}

	public void updateemployee(Long id, Employee emp) {
		ResponseEntity<Employee> up = rt.getForEntity("http://localhost:8081/emp/" + id, Employee.class);
		Employee em = new Employee();
		em.setEmp_name(emp.getEmp_name());
		em.setEmp_role(emp.getEmp_role());
		em.setEmp_team(emp.getEmp_team());
		HttpEntity<Employee> requestUpdate = new HttpEntity<Employee>(em);
		rt.exchange("http://localhost:8081/updateemp/" + id, HttpMethod.PUT, requestUpdate, Employee.class);

	}

	public void updateorga(Long id, Long empid) {

		HttpEntity<Organization> requestUpdate = new HttpEntity<Organization>(new Organization());
		rt.exchange("http://localhost:8081/updateOrgby/" + id, HttpMethod.PUT, requestUpdate, Employee.class);

	}
	public void deleteemployeeorg(Long id, Long empid) {
		HttpEntity<Organization> requestUpdate = new HttpEntity<Organization>(new Organization());
		rt.exchange("http://localhost:8081/deleteemployee/" + empid+"/"+id, HttpMethod.PUT, requestUpdate, Organization.class);

	}

}
