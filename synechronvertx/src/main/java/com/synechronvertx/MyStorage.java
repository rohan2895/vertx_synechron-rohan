package com.synechronvertx;

import io.vertx.core.Future;
import io.vertx.core.Promise;

public class MyStorage {
	public Future<Void> createRecord(String empname){
		Promise<Void> promise=Promise.promise();
		System.out.println("inserting user "+empname);
		simulateAsyncOperation(()->{
			promise.complete();
		});
		return promise.future();
	}
	
	public Future<String> getRecord(String empname){
		Promise<String> promise=Promise.promise();
		simulateAsyncOperation(()->{
			promise.complete("my employee name is "+empname);
		});
		return promise.future();
	}
	
	private void simulateAsyncOperation(Runnable r) {
		new Thread(()->{
			try {
				Thread.sleep(4000);
				r.run();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
