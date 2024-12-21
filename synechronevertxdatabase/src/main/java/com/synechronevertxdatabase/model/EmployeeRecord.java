package com.synechronevertxdatabase.model;

import io.vertx.core.json.JsonObject;

public class EmployeeRecord {
	private int empid;
	private String empname;
	private String empemail;
	
	public EmployeeRecord() {}
	public EmployeeRecord(int empid,String empname,String empemail) {
		this.empid=empid;
		this.empname=empname;
		this.empemail=empemail;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmpemail() {
		return empemail;
	}
	public void setEmpemail(String empemail) {
		this.empemail = empemail;
	}
	
	public JsonObject toJson() {
		return new JsonObject().put("empid", empid).put("empname", empname).put("email",empemail);
	}
	
	public static EmployeeRecord fromJson(JsonObject json) {
		return new EmployeeRecord(json.getInteger("empid"),json.getString("empname"),json.getString("empemail"));
	}
}	























