package com.macotter.thingsafe.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.macotter.thingsafe.auth.User;
import com.macotter.thingsafe.core.Saying;
import com.yammer.dropwizard.auth.Auth;
import com.yammer.metrics.annotation.Timed;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
	private final String template;
	private final String defaultName;
	private final AtomicLong counter;

	public HelloWorldResource(String template, String defaultName) {
		this.template = template;
		this.defaultName = defaultName;
		this.counter = new AtomicLong();
	}

	@GET
	@Timed
	public Saying sayHello(@Auth User user) {
		return new Saying(counter.incrementAndGet(), String.format(template,
				user.getName()));
	}
}