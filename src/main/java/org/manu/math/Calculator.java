package org.manu.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Calculator.class);
	
	public static void main(String[] args) {
		LOGGER.debug("MAIN"+args);
		System.out.println("Logger initialized");

	}

}
