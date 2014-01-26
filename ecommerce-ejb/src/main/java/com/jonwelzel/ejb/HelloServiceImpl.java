package com.jonwelzel.ejb;

import javax.ejb.Stateless;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Session Bean implementation class HelloServiceImpl
 */
@Stateless(mappedName = "helloService")
public class HelloServiceImpl implements HelloService {

	Logger log = LoggerFactory.getLogger(getClass());

	private static String DEFAULT_NAME = "World";

	@Override
	public String greet(String name) {
		String finalName = StringUtils.defaultIfEmpty(name, DEFAULT_NAME);
		String greeting = "Hello " + finalName;
		log.debug(greeting);
		return greeting;
	}
}
