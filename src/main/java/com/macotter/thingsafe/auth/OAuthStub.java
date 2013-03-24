package com.macotter.thingsafe.auth;

import com.google.common.base.Optional;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;

public class OAuthStub implements Authenticator<String, User> {

	@Override
	public Optional<User> authenticate(String token)
			throws AuthenticationException {
		// Note: this header would be more complex with a real OAuth request,
		// and the various fields would need parsing
		if ("oauth_token=\"letmein\"".equals(token)) {
			return Optional.of(new User("testuser"));
		}
		return Optional.absent();
	}

}
