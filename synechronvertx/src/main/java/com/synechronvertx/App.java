package com.synechronvertx;

import io.vertx.core.Vertx;

public class App 
{
    public static void main( String[] args )
    {
        Vertx v=Vertx.vertx();
        
        v.deployVerticle(new MyVerticle());
    }
}
