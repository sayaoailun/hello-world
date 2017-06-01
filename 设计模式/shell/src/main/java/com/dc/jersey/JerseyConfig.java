package com.dc.jersey;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		register(RestDemo.class);
		register(MultiPartFeature.class);
//		register(MultiPartResource.class);
	}
	
}
