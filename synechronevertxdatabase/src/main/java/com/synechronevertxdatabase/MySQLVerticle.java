package com.synechronevertxdatabase;

import com.synechronevertxdatabase.controller.MySQLHandler;

import io.vertx.core.AbstractVerticle;

public class MySQLVerticle extends AbstractVerticle{

	@Override
	public void start() {
		MySQLHandler myHandler=new MySQLHandler(vertx);
		
		vertx.createHttpServer().requestHandler(myHandler.createRouter())
		.listen(10000).onSuccess(res->System.out.println("server started at 10000"));
	}
}
