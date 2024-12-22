package com.synechronvertx.eventbusdistributed;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

public class ReceiverVerticles extends AbstractVerticle{

	
	@Override
	public void start() throws Exception {
		vertx.eventBus().consumer("distributed.address",m->{
			System.out.println("Hey Sender i received your greeting "+m.body());
		});

	}

	

}
