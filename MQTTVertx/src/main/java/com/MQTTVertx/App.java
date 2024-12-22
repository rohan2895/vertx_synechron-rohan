package com.MQTTVertx;

import io.vertx.core.Vertx;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Vertx v=Vertx.vertx();
        v.deployVerticle(new MQTTProducer());
        v.deployVerticle(new MQTTConsumer());
    }
}
