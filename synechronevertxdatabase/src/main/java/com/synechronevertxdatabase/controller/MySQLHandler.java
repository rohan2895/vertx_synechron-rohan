package com.synechronevertxdatabase.controller;

import com.synechronevertxdatabase.model.EmployeeRecord;
import com.synechronevertxdatabase.service.MyDatabaseServiceUsingMySQL;

import io.vertx.core.Vertx;
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
		return router;
	}
}
