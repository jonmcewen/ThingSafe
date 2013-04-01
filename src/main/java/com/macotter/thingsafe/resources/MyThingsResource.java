package com.macotter.thingsafe.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.macotter.thingsafe.auth.User;
import com.macotter.thingstore.entities.Thing;
import com.macotter.thingstore.repositories.ThingRepository;
import com.yammer.dropwizard.auth.Auth;
import com.yammer.metrics.annotation.Timed;

@Path("mythings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MyThingsResource {
	public static final Logger log = LoggerFactory
			.getLogger(MyThingsResource.class);
	private final ThingRepository store;

	public MyThingsResource(ThingRepository store) {
		this.store = store;
	}

	@GET
	@Timed
	public List<Thing> getMyThings(@Auth User user) {
		return this.store.findByThingKeyUser(user.getName());
	}

	@PUT
	@Timed
	public void putThing(Thing thing, @Auth User user) {
		log.info("Putting new Thing: {}", thing);
		thing.setUser(user.getName());
		this.store.save(thing);
	}
}