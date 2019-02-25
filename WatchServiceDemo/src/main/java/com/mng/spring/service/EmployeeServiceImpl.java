package com.mng.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mng.spring.pojo.ClientInfo;
import com.mng.spring.pojo.Employee;



@Component
public class EmployeeServiceImpl implements EmployeeService {

	Map<String, Employee> map = null;
	List<Employee> employeeList = null;

	public EmployeeServiceImpl() {
		map = new HashMap<String, Employee>();
		employeeList = new ArrayList<Employee>();

		Employee e1 = new Employee();
		e1.setId("2330287");
		e1.setName("Nagendra");
		e1.setPhoneNo("8951571404");
		e1.setEmailId("nagendra@mphasis.com");

		ArrayList<ClientInfo> list1 = new ArrayList<ClientInfo>();

		ClientInfo clientInfo1 = new ClientInfo();
		clientInfo1.setId("5027363");
		clientInfo1.setName("Nagendra");
		clientInfo1.setEmailId("nagendra@fedex.com");
		list1.add(clientInfo1);

		e1.setClientInfo(list1);

		map.put("2330287", e1);
		employeeList.add(e1);

		Employee e2 = new Employee();
		e2.setId("2330286");
		e2.setName("Murali");
		e2.setPhoneNo("8951571404");
		e2.setEmailId("murali@mphasis.com");

		ArrayList<ClientInfo> list2 = new ArrayList<ClientInfo>();

		ClientInfo clientInfo2 = new ClientInfo();
		clientInfo2.setId("5027363");
		clientInfo2.setName("Murali");
		clientInfo2.setEmailId("murlai@fedex.com");
		list2.add(clientInfo2);

		e2.setClientInfo(list2);
		map.put("2330286", e2);
		employeeList.add(e2);

		Employee e3 = new Employee();
		e3.setId("2315174");
		e3.setName("Ganesh");
		e3.setPhoneNo("8030908256");
		e3.setEmailId("Ganesh@mphasis.com");

		ArrayList<ClientInfo> list3 = new ArrayList<ClientInfo>();

		ClientInfo clientInfo3 = new ClientInfo();
		clientInfo3.setId("5018159");
		clientInfo3.setName("Ganesh");
		clientInfo3.setEmailId("Ganesh@fedex.com");
		list3.add(clientInfo3);

		e3.setClientInfo(list3);

		map.put("2315174", e3);
		employeeList.add(e3);

	}

	@Override
	public Employee getEmpById(String id) {
		return map.get(id);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeList;

	}

	@Override
	public void deleteEmpById(String id) {
		System.out.println("Employee:" + id + " with deleted");
		map.remove(id);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		employeeList.add(employee);
		return employeeList.get(employeeList.size() - 1);
	}

}
