package com.synechronvertx.eventbusdistributed;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class EventBusDistributedMain {

	public static void main(String[] args) {
		HazelcastClusterManager hm=new HazelcastClusterManager();
		VertxOptions oo=new VertxOptions().setClusterManager(hm);
		
		Vertx.clusteredVertx(oo,res->{
			
			if(res.succeeded()) {
				Vertx v=res.result();
				v.deployVerticle(new SenderVerticles());
				v.deployVerticle(new ReceiverVerticles());
			}
		});

	}

}
