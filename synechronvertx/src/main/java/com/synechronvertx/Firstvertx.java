package com.synechronvertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class Firstvertx {

	public static void main(String[] args) {
		Vertx v=Vertx.vertx();
		
		
		DeploymentOptions multipleInstances=new DeploymentOptions().setInstances(5);
		
		JsonObject myconfiguration=new JsonObject().put("port", 10001).put("message", "this is comming from configuration");
		DeploymentOptions myconf=new DeploymentOptions().setConfig(myconfiguration);
		
		
		
		v.deployVerticle(new MyStandardVericles(),res->{
			if(res.succeeded()) {
				System.out.println(res.result());
				
				
				v.setTimer(10000, rr->{
					v.undeploy(res.result(),rr1->{
						if(rr1.succeeded()) {
							System.out.println("undeployment successful");
						}
						else {
							System.out.println("unsuccessful "+rr1.cause());
						}
					});
				});
			}
			else {
				System.out.println("fail "+res.cause());
			}
		});
		//v.deployVerticle(MyStandardVericles.class.getName(),multipleInstances);
		//v.deployVerticle(new MyStandardVericles(),myconf);
		//v.deployVerticle(new MyWorkerVerticles());

	}

}
