package com.synechronmicronaut.controller;

import java.util.List;

import com.synechronmicronaut.model.MyEmployee;
import com.synechronmicronaut.service.MyService;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/employee")
public class MyController {

	@Inject
	private MyService mService;
	
	@Get("empall")
	public List<MyEmployee> allUser(){
		return mService.getAllEmployee();
	}
}
