package com.synechronevertxdatabase.controller;

import com.synechronevertxdatabase.model.EmployeeRecord;
import com.synechronevertxdatabase.service.MyDatabaseServiceUsingMySQL;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class MySQLHandler {

	private MyDatabaseServiceUsingMySQL mysqlservice;
	
	public MySQLHandler(Vertx vertx) {
		this.mysqlservice=new MyDatabaseServiceUsingMySQL(vertx);
	}
	
	
	public Router createRouter() {
		Router router=Router.router(Vertx.vertx());
		router.route().handler(BodyHandler.create());
		
		router.post("/mysql/createEmployee").handler(ctx->{
			EmployeeRecord empRecord=EmployeeRecord.fromJson(ctx.getBodyAsJson());
			
			mysqlservice.createEmployeeRecord(empRecord).onSuccess(v->ctx.response().end("employee record created successfully")).onFailure(ctx::fail);
		});
		
		
		router.get("/mysql/findEmployeebyid/:empid").handler(ctx->{
			
			int empid=Integer.parseInt(ctx.pathParam("empid"));
			
			mysqlservice.readEmployee(empid).onSuccess(v->ctx.response().end(v.toJson().encode())).onFailure(ctx::fail);
		});
		
		router.get("/mysql/findallemployee").handler(ctx->{
			
			mysqlservice.findAllEmployee().onSuccess(employee->	{	
				
				JsonArray jsonArray=new JsonArray();
				employee.forEach(emp -> jsonArray.add(emp.toJson()));
				
				ctx.response()
				.putHeader("content-type","application/json")
				.end(jsonArray.encode());
			}).onFailure(err->{
				ctx.response().putHeader("content-type","application/json")
				.end(new JsonObject().put("error", err.getMessage()).encode());
			});
		});
		
		
		router.put("/mysql/editEmployeebyid/:empid").handler(ctx->{
			int empid=Integer.parseInt(ctx.pathParam("empid"));
			EmployeeRecord empnewRecord=EmployeeRecord.fromJson(ctx.getBodyAsJson());
			empnewRecord.setEmpid(empid);
			
			mysqlservice.updateEmployee(empnewRecord).onSuccess(v->ctx.response().end("employee recordupdated successfully")).onFailure(ctx::fail);
			
		});
		
		router.delete("/mysql/deleteEmployeebyid/:empid").handler(ctx->{
			int empid=Integer.parseInt(ctx.pathParam("empid"));
						
			mysqlservice.deleteEmployee(empid).onSuccess(v->ctx.response().end("employee record deleted successfully")).onFailure(ctx::fail);
			
		});
		
		
		return router;
	}
}





















