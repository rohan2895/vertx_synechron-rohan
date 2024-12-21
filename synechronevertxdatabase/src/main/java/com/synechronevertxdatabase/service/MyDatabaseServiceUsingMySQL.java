package com.synechronevertxdatabase.service;

import java.util.ArrayList;
import java.util.List;

import com.synechronevertxdatabase.model.EmployeeRecord;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Tuple;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class MyDatabaseServiceUsingMySQL {
	
	private MySQLPool mysqlclient;
	
	
	public MyDatabaseServiceUsingMySQL(Vertx vertx) {
		MySQLConnectOptions connectionoption=new MySQLConnectOptions().setPort(3306).setHost("localhost").setDatabase("synechronemployeedb")
				.setUser("root").setPassword("rajesh");
		
		PoolOptions poolOption=new PoolOptions().setMaxSize(7);
		
		this.mysqlclient=MySQLPool.pool(vertx,connectionoption,poolOption);
		
	}
	
	
	public Future<EmployeeRecord> createEmployeeRecord(EmployeeRecord emprecord){
		return mysqlclient.preparedQuery("insert into employeerecord values(?,?,?)")
				.execute(Tuple.of(emprecord.getEmpid(),emprecord.getEmpname(),emprecord.getEmpemail())).mapEmpty();
	}
	
	public Future<EmployeeRecord> readEmployee(int empid){
		return mysqlclient.preparedQuery("select * from employeerecord where empid=?").execute(Tuple.of(empid)).map(rows->{
			Row row =rows.iterator().next();
			return new EmployeeRecord(row.getInteger("empid"),row.getString("empname"),row.getString("empemail"));
		});
	}
	
	public Future<List<EmployeeRecord>> findAllEmployee(){
		return mysqlclient.query("select empid,empname,empemail from employeerecord").execute().map(this::mapToEmployee);
	}
	
	public List<EmployeeRecord> mapToEmployee(RowSet<Row> rows){
		List<EmployeeRecord> employees=new ArrayList<EmployeeRecord>();
		
		for(Row row : rows) {
			employees.add(new EmployeeRecord(row.getInteger("empid"),row.getString("empname"),row.getString("empemail")));
		}
				
		return employees;
	}
	
	public Future<Void> updateEmployee(EmployeeRecord empnewrecord){
		return mysqlclient.preparedQuery("update employeerecord set empname=?, empemail=? where empid=?")
				.execute(Tuple.of(empnewrecord.getEmpname(),empnewrecord.getEmpemail(),empnewrecord.getEmpid())).mapEmpty();
	}
	
	public Future<Void> deleteEmployee(int empid){
		return mysqlclient.preparedQuery("delete from  employeerecord where empid=?")
				.execute(Tuple.of(empid)).mapEmpty();
	}
	
}


















