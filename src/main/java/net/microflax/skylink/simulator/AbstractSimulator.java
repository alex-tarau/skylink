package net.microflax.skylink.simulator;

import net.datafaker.Faker;

/**
 * Base class for all simulators.
 *
 * @param <T> the entity type
 */
public abstract class AbstractSimulator<T> {

    private final Faker faker = new Faker();

    /**
     * Simulate data
     */
    protected abstract T simulate();

    protected abstract void save(T entity);

    public Faker getFaker() {
        return faker;
    }

}
