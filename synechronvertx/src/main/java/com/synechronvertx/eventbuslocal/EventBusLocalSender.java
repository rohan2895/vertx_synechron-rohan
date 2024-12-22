package com.synechronvertx.eventbuslocal;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

public class EventBusLocalSender extends AbstractVerticle {

	
	@Override
	public void start() throws Exception {
		//point to point
		/*vertx.setPeriodic(3000, tid->{
			vertx.eventBus().send("localhost.address", "Hello i am sender and saying you Hi!");
		});*/
		
		//reply request pattern
		/*vertx.setPeriodic(3000, tid->{
			vertx.eventBus().send("localhost.address", "Hello i am sender and saying you Hi!");
		});*/
		
		/*vertx.eventBus().request("localhost.address","my request message", replymessage->{
			if(replymessage.succeeded()) {
				System.out.println("message received from receiver "+replymessage.result().body());
			}
		});*/
		//publisher suscriber pattern
		vertx.setPeriodic(3000, tid->{
			vertx.eventBus().publish("localhost.address", "Hello i am sender and saying you Hi!");
		});

	}

	

}
