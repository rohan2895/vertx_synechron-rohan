package com.synechronvertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyVerticle extends AbstractVerticle{

	@Override
	public void start() {
		MyStorage m=new MyStorage();
		
		Future<Void> myrecord= m.createRecord("Rajesh Upadhyay");
		
		Future<String> myrecord1=myrecord.compose(v->m.getRecord("Rajesh Upadhyay"));
	}
}
