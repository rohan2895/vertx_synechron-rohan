package com.synechronkafkavertx;

import io.vertx.core.Vertx;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       Vertx v1=Vertx.vertx();
       v1.deployVerticle(new KafkaProducer1());
       v1.deployVerticle(new KafkaConsumer1());
    }
}
