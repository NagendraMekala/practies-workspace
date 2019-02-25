package com.mng.spring.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mng.spring.pojo.Employee;
import com.mng.spring.pojo.EmployeeList;
import com.mng.spring.service.EmployeeService;
import com.mng.spring.watcher.api.FileWatcher;
import com.mng.spring.watcher.api.ProcessData;


@RestController
public class WatchServiceController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private FileWatcher fileMonitor;
	
	@Autowired
	private ProcessData processData;
	
	@RequestMapping("/hello")
	public String hello() {
		System.out.println("##############################TEMP RECORDS #########################"+processData.getFilterDataTemps());
		return "Hello wel come to watch service Api";
	}
	
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id) {
		Employee employee = employeeService.getEmpById(id);
		System.out.println("**************getEmployeeById()*******************");
		
		System.out.println("##############################TEMP RECORDS #########################"+processData.getFilterDataTemps());
		if (employee != null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public @ResponseBody EmployeeList getAllEmployee() {
		List<Employee> employeeList = employeeService.getAllEmployee();
		System.out.println("**************getAllEmployee()*******************");
		System.out.println("##############################TEMP RECORDS #########################"+processData.getFilterDataTemps());
		
		return new EmployeeList(employeeList);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteByEmployee(@PathVariable("id") String id) {
		employeeService.deleteEmpById(id);
		System.out.println("**************DeleteByEmployee()*******************");
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@RequestMapping(value = "/addemp", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Employee>  adddEmployee(@RequestBody Employee employee) {
		Employee newEmp = employeeService.addEmployee(employee);
		System.out.println("**************adddEmployee()*******************");
		
		if (newEmp != null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		}
		return new ResponseEntity<Employee>(HttpStatus.ACCEPTED);
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostConstruct
	public void init(){
		System.out.println("*************init method is executed****************");
		fileMonitor.startMonitoring();
		
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("*************destrory method is executed****************");
		//fileMonitor.stopMonitoring();
		//fileMonitor.stopThreads();
	}
	
	public ProcessData getPostalCutoffProcessData() {
		return processData;
	}

	public void setPostalCutoffProcessData(ProcessData filterDataTemps) {
		this.processData = filterDataTemps;
	}

	public FileWatcher getFileMonitor() {
		return fileMonitor;
	}

	public void setFileMonitor(FileWatcher fileMonitor) {
		this.fileMonitor = fileMonitor;
	}
}
