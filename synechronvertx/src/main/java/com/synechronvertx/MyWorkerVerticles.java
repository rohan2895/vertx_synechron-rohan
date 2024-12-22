package com.synechronvertx;

import io.vertx.core.AbstractVerticle;

public class MyWorkerVerticles extends AbstractVerticle{
	@Override
	public void start() {
		vertx.setPeriodic(2000,request->{
			System.out.println("it is my worker verticles");
		});
	}
}
