package com.synequarkus;

import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class MyResource {
	
	@Inject
	Vertx vertx;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<JsonObject> hello() {
        return Uni.createFrom().emitter(emitter->{
        	vertx.eventBus().<String>request("localhost.address", null, reply->{
        		if(reply.succeeded()) {
        			emitter.complete(new JsonObject().put("message", reply.result().body()));
        		}
        		else {
        			emitter.fail(reply.cause());
        		}
        	});
        });
    }
}
