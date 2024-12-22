package com.synechronAMQP;

import io.vertx.amqp.AmqpClient;
import io.vertx.amqp.AmqpClientOptions;
import io.vertx.core.AbstractVerticle;

public class AMQPConsumer extends AbstractVerticle{
	@Override
	public void start() {
		AmqpClientOptions options=new AmqpClientOptions()
				.setHost("localhost")
				.setPort(5672)
				.setUsername("guest")
				.setPassword("guest");
		
		AmqpClient client = AmqpClient.create(vertx, options);
		
		client.connect(result->{
			if(result.succeeded()) {
				System.out.println("connected with Rabbit");
				var connection=result.result();
				
				connection.createReceiver("my-queue",cc->{
					if(cc.succeeded()) {
						cc.result().handler(message->{
							System.out.println("Received message is "+message.bodyAsString());
						});
					}
				});
				
				
			}
			else {
				System.out.println("fail due to "+result.cause());
			}
		});
	}
}
