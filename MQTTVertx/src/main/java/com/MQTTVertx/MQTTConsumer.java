package com.MQTTVertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.mqtt.MqttClient;

public class MQTTConsumer extends AbstractVerticle{
	@Override
	public void start() {
MqttClient client=MqttClient.create(vertx);
		
		client.connect(1883, "localhost",result->{
			if(result.succeeded()) {
				client.subscribe("syne/topic",1, result1->{
					if(result1.succeeded()) {
						System.out.println("able to subscribe from given topic");
					}
				});
				
				client.publishHandler(message->{
					System.out.println("received message is "+message.payload().toString());
				});
			}
			else {
				System.out.println("fail "+result.cause());
			}
		});
	}
}
