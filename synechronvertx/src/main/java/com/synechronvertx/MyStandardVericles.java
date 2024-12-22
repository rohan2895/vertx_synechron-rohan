package com.synechronvertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class MyStandardVericles extends AbstractVerticle{
	
	@Override
	public void start() {
		
		//JsonObject cc=config();
		//int portno=cc.getInteger("port");
		//String mm=cc.getString("message");
		
		vertx.createHttpServer().requestHandler(request->{
			request.response().putHeader("content-type", "text/plain")
			.end("hello");
		}).listen(10001,http->{
			if(http.succeeded()) {
				System.out.println("server started successfully at 10001");
			}
			else {
				System.out.println("could not start because of "+http.cause());
			}
		});
	}

}
