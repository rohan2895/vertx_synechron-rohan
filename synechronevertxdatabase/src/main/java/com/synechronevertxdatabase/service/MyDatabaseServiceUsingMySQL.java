package com.synechronevertxdatabase.service;

import com.synechronevertxdatabase.model.EmployeeRecord;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Tuple;

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
	
	
}


















