package com.synechronvertx.eventbusdistributed;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

public class SenderVerticles extends AbstractVerticle {



	@Override
	public void start() throws Exception {
		vertx.setPeriodic(3000, tid->{
			vertx.eventBus().send("distributed.address", "Hello i am sender and saying you Hi!");
		});
		
		
		
		vertx.fileSystem().readFile("c:/one.txt",f1->{
			if(f1.succeeded()) {
				vertx.fileSystem().readFile("c:/two.txt",f2->{
					if(f2.succeeded()) {
						vertx.fileSystem().readFile("c:/three.txt",f3->{
							if(f3.succeeded()) {
								System.out.println("read completed");
							}
						});
					}
				});
			}
		});

	}

	

}
