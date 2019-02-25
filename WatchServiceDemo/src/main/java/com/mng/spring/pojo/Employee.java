package com.mng.spring.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "id", "name", "phoneNo", "emailId", "clientInfo" })
public class Employee {

	private String id;
	private String name;
	private String phoneNo;
	private String emailId;
	private List<ClientInfo> clientInfo;

	@XmlElement
	public List<ClientInfo> getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(List<ClientInfo> clientInfo) {
		this.clientInfo = clientInfo;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@XmlElement
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
