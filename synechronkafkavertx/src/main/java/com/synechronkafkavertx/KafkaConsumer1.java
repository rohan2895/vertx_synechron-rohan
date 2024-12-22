package com.synechronkafkavertx;

import java.util.HashMap;
import java.util.Map;

import io.vertx.core.AbstractVerticle;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import io.vertx.kafka.client.producer.KafkaProducer;

public class KafkaConsumer1 extends AbstractVerticle{
	
	@Override
	public void start() {
		Map<String,String> config=new HashMap<String, String>();
		config.put("bootstrap.servers", "localhost:9092");
		config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		config.put("group.id", "test-group");
		config.put("enable.auto.commit", "true");
		
		KafkaConsumer<String,String> consumer=KafkaConsumer.create(vertx, config);
		
		consumer.subscribe("test-topic1", result->{
			if(result.succeeded()) {
				System.out.println("from test-topic1 subscribed ");
			}
			else {
				System.out.println("fail due to "+result.cause());
			}
		});
		
		
		consumer.handler(record->{
			System.out.println("receiving record key is "+record.key()+"  record value is "+record.value());
		});
		
	}

}










