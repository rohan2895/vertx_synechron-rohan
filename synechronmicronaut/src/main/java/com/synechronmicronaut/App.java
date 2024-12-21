package com.synechronmicronaut;

import java.util.HashMap;
import java.util.Map;

import io.micronaut.runtime.Micronaut;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Map<String, Object> hh=new HashMap<String, Object>();
    	hh.put("micronaut.server.port", 8082);
    	
        Micronaut.build(args).properties(hh).start();
    }
}
