package com.synechronvertx.eventbuslocal;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

public class EventBusLocalReceiver extends AbstractVerticle {

	

	@Override
	public void start() throws Exception {
		//point to point
		/*vertx.eventBus().consumer("localhost.address",m->{
			System.out.println("Hey Sender i received your greeting "+m.body());
		});*/
		//reply request model
		/*vertx.eventBus().consumer("localhost.address",m->{
			System.out.println("Hey Sender i received your greeting "+m.body());
			m.reply("receiver is also saying hello to sender");
		});*/
		
		//publisher subscriber
		
		vertx.eventBus().consumer("localhost.address",m->{
			System.out.println("consumer1 says Hey Sender i received your greeting "+m.body());
		});
		
		vertx.eventBus().consumer("localhost.address",m->{
			System.out.println("consumer2 says Hey Sender i received your greeting "+m.body());
		});
		
		vertx.eventBus().consumer("localhost.address",m->{
			System.out.println("consumer3 says Hey Sender i received your greeting "+m.body());
		});

	}

	
}
