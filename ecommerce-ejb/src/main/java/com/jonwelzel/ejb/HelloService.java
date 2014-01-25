package com.jonwelzel.ejb;
import javax.ejb.Local;

/**
 * Hello Service interface.
 */
@Local
public interface HelloService {

	/**
	 * Return a greeting
	 * @param name
	 * @return a famous greeting
	 */
	String greet(String name);

}
