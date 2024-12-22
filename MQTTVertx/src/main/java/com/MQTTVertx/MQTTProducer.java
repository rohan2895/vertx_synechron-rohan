package com.MQTTVertx;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttClient;

public class MQTTProducer extends AbstractVerticle{
	@Override
	public void start() {
		MqttClient client=MqttClient.create(vertx);
		
		client.connect(1883, "localhost",result->{
			if(result.succeeded()) {
				System.out.println("connected with mosquito");
				
				vertx.setPeriodic(2000, id->{
					client.publish("syne/topic", Buffer.buffer("i am MQTT message via vertx"), MqttQoS.AT_LEAST_ONCE, false, false);
					System.out.println("message published successfully");
				});
			}
			else {
				System.out.println("fail "+result.cause());
			}
		});
	}
}
