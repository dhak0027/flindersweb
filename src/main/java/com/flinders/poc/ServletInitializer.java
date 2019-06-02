package com.flinders.poc;

import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Servlet Initializer
 * @author mswahithali
 */

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//setRegisterErrorPageFilter(false);
		return application.sources(Application.class);
	}

}
