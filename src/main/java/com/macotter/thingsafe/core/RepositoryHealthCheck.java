package com.macotter.thingsafe.core;

import com.macotter.thingstore.entities.ThingKey;
import com.macotter.thingstore.repositories.ThingRepository;
import com.yammer.metrics.core.HealthCheck;

public class RepositoryHealthCheck extends HealthCheck {
	private final ThingRepository repo;

	public RepositoryHealthCheck(ThingRepository repo) {
		super("repo");
		this.repo = repo;
	}

	@Override
	protected Result check() throws Exception {
		try {
			this.repo.exists(new ThingKey("health", "check"));
			return Result.healthy();
		} catch (Exception e) {
			return Result.unhealthy("repo doesn't include a name");
		}
	}
}