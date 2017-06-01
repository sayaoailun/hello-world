package com.dc.jersey;

import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.dc")
public class Main extends SpringBootServletInitializer {
	
	private static Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {
		log.info("server starting");
		new Main().configure(new SpringApplicationBuilder(Main.class)).run(args);
	}
	
	@Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/ws/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
        registration.setName("jerseyServlet");
        return registration;
    }

}
