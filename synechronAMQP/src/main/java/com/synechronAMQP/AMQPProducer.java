package com.synechronAMQP;

import io.vertx.amqp.AmqpClient;
import io.vertx.amqp.AmqpClientOptions;
import io.vertx.amqp.AmqpMessage;
import io.vertx.core.AbstractVerticle;

public class AMQPProducer extends AbstractVerticle{

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
				
				vertx.setPeriodic(2000, id->{
					connection.createSender("my-queue",cc->{
						if(cc.succeeded()) {
							cc.result().send(AmqpMessage.create().withBody("i am MQ PROTOCOL RUNNING UNDER RABBIT").build());
							System.out.println("message sent");
						}
					});
				});
				
			}
			else {
				System.out.println("fail due to "+result.cause());
			}
		});
		
	}
}











