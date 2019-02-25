package com.mng.spring.service;

import java.util.List;

import com.mng.spring.pojo.Employee;

public interface EmployeeService {

	public Employee getEmpById(String id);

	public List<Employee> getAllEmployee();

	public void deleteEmpById(String id);

	public Employee addEmployee(Employee employee);

}
