package com.synequarkus;

import io.vertx.core.AbstractVerticle;

public class MyVerticles extends AbstractVerticle{
	
	@Override
	public void start() {
		vertx.eventBus().consumer("localhost.address",message->{
			message.reply("if this message comes means quarkus is able to connect with vertx");
		});
	}

}
