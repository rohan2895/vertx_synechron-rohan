package com.synechronkafkavertx;

import java.util.HashMap;
import java.util.Map;

import io.vertx.core.AbstractVerticle;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;

public class KafkaProducer1 extends AbstractVerticle{

	@Override
	public void start() {
		Map<String,String> config=new HashMap<String, String>();
		config.put("bootstrap.servers", "localhost:9092");
		config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		config.put("acks", "1");
		
		KafkaProducer<String,String> producer=KafkaProducer.create(vertx, config);
		
		
		vertx.setPeriodic(2000, id->{
			String key="key - "+id;
			String value="message - "+id;
			
			KafkaProducerRecord<String, String> record= KafkaProducerRecord.create("test-topic1",key,value);
			
			producer.send(record,result->{
				if(result.succeeded()) {
					System.out.println("my message sent "+ record.value());
				}
				else {
					System.out.println("fail due to "+result.cause());
				}
			});
			
		});
				
	}
}
