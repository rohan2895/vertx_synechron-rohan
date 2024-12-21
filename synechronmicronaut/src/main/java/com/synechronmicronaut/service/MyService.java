package com.synechronmicronaut.service;

import java.util.Arrays;
import java.util.List;

import com.synechronmicronaut.model.MyEmployee;

import jakarta.inject.Singleton;


@Singleton
public class MyService {

	public List<MyEmployee> getAllEmployee() {
		
		return Arrays.asList(
				new MyEmployee("Rajesh", "abc@gmail.com"),
				new MyEmployee("Rahul","rahul@gmail.com")
				);
	}

}
