package com.mng.spring.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="EmployeeList")
public class EmployeeList {
	private List<Employee> list;  
	  
	 protected EmployeeList() {  
	 }  
	  
	 public EmployeeList(List<Employee> list) {  
	  this.list = list;  
	 }  
	   
	 @XmlElement (name="Employee") 
	 public List<Employee> getEmployeeList() {
	        if (list == null) {
	        	list = new ArrayList<Employee>();
	        }
	        return this.list;
	    }
}
