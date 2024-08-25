package net.microflax.skylink.simulator;

import net.datafaker.Faker;
import net.microfalx.lang.ClassUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Base class for all simulators.
 *
 * @param <T> the entity type
 */
public abstract class AbstractSimulator<T> {

    protected final Faker faker = new Faker();
    protected final ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * Returns the number of elements which exists in the database.
     *
     * @return a positive integer
     */
    protected abstract int getElementCount();

    /**
     * Returns the average number of elements to generate at each iteration.
     * <p>
     * The range is about 30% of the average.
     *
     * @return a positive integer
     */
    protected abstract int getSimulationCountPerIteration();

    /**
     * Returns whether the simulator should generate more elements.
     *
     * @return {@code true} to generate more,  {@code false} otherwise
     */
    protected boolean shouldSimulate() {
        return true;
    }

    /**
     * Generates and persists the next element of the simulation.
     *
     * @return the created element
     */
    protected T next() {
        T simulated;
        if (shouldSimulate()) {
            simulated = simulate();
            if (simulated != null) save(simulated);
        } else {
            simulated = getNextCached();
        }
        if (simulated == null) {
            throw new SimulationException("NULL element from " + ClassUtils.getName(this));
        }
        return simulated;
    }

    /**
     * Return the next element from the cache.
     *
     * @return a non-null element
     */
    protected abstract T getNextCached();

    /**
     * Simulate one element.
     */
    protected abstract T simulate();

    /**
     * Persists the simulated element in the database.
     *
     * @param element the element
     */
    protected abstract void save(T element);

}
