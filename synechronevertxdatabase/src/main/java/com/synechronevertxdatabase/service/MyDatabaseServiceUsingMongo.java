package com.synechronevertxdatabase.service;

import com.synechronevertxdatabase.model.EmployeeRecord;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.Tuple;

public class MyDatabaseServiceUsingMongo {
	private MongoClient mongoclient;
	
	public MyDatabaseServiceUsingMongo(Vertx vertx) {
		
		JsonObject config=new JsonObject().put("connection_string", "mongodb://localhost:27017").put("db_name", "employeemongodb");
		
		this.mongoclient=MongoClient.create(vertx,config);
		
	}
	
	public Future<Void> createEmployeeRecord(EmployeeRecord emprecord){
		return mongoclient.save("employees",emprecord.toJson()).mapEmpty();
	}
	
	public Future<EmployeeRecord> readEmployee(String empid){
		return mongoclient.findOne("employees", new JsonObject().put("_id", empid),null).map(json->EmployeeRecord.fromJson(json));
	}
	
	public Future<Void> updateEmployee(String id,EmployeeRecord empnewrecord){
		return mongoclient.replaceDocuments("employees", new JsonObject().put("_id", id),empnewrecord.toJson()).mapEmpty();
	}
	
	public Future<Void> deleteEmployee(String empid){
		return mongoclient.removeDocument("employees",new JsonObject().put("_id", empid)).mapEmpty();
	}
	
	
}
