package com.synequarkus;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.Vertx;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;

@Singleton
public class MyVerticleRunner {
	private Vertx vertx;
	public MyVerticleRunner(Vertx vertx) {
		this.vertx=vertx;
	}
	
	public void onStart(@Observes StartupEvent event) {
		vertx.deployVerticle(new MyVerticles());
	}
}
