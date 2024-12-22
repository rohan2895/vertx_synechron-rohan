package com.synechronvertx.eventbuslocal;

import io.vertx.core.Vertx;

public class EventBusLocal{

	public static void main(String[] args) {
		Vertx v=Vertx.vertx();
		v.deployVerticle(new EventBusLocalSender());
		v.deployVerticle(new EventBusLocalReceiver());

	}

}
