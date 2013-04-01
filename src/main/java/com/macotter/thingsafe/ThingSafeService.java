package com.macotter.thingsafe;

import com.macotter.thingsafe.auth.OAuthProvider;
import com.macotter.thingsafe.auth.OAuthStub;
import com.macotter.thingsafe.auth.User;
import com.macotter.thingsafe.core.RepositoryHealthCheck;
import com.macotter.thingsafe.resources.MyThingsResource;
import com.macotter.thingstore.StoreFactory;
import com.macotter.thingstore.repositories.ThingRepository;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class ThingSafeService extends Service<ThingSafeConfiguration> {
	public static void main(String[] args) throws Exception {
		new ThingSafeService().run(args);
	}

	@Override
	public void initialize(Bootstrap<ThingSafeConfiguration> bootstrap) {
		bootstrap.setName("thing-safe");
	}

	@Override
	public void run(ThingSafeConfiguration configuration,
			Environment environment) {
		final StoreFactory factory = new StoreFactory();
		final ThingRepository repository = factory.getRepository();
		environment.addResource(new MyThingsResource(repository));
		environment.addHealthCheck(new RepositoryHealthCheck(repository));

		// auth
		Authenticator<String, User> auth = new OAuthStub();
		environment.addProvider(new OAuthProvider<User>(auth));
	}
}