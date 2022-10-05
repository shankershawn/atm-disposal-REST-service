/**
 * 
 */
package com.shankarsan.atm.disposal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author SHANKARSAN
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableAspectJAutoProxy
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
