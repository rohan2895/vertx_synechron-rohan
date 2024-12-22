package com.synechronAMQP;

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
        v.deployVerticle(new AMQPProducer());
        v.deployVerticle(new AMQPConsumer());
    }
}
