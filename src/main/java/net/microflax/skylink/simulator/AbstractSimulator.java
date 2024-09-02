package net.microflax.skylink.simulator;

import net.datafaker.Faker;
import net.microfalx.lang.ClassUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Base class for all simulators.
 *
 * @param <T>  the entity type
 * @param <ID> the identifier type
 */
public abstract class AbstractSimulator<T, ID> {

    protected final Faker faker = new Faker();
    protected final Random random = ThreadLocalRandom.current();

    /**
     * Returns the average number of elements to generate at each iteration.
     *
     * @return a positive integer
     */
    protected abstract int getSimulationCountPerIteration();

    /**
     * Returns the repository which gives access to the persisted elements.
     *
     * @return a non-null instance
     */
    protected abstract JpaRepository<T, ID> getRepository();

    /**
     * Returns whether the simulator should create new elements.
     *
     * @return {@code true} to generate more,  {@code false} otherwise
     */
    protected boolean shouldCreate() {
        return true;
    }

    /**
     * Returns the next random element from the list of element.
     * <p>
     * The method will simulate a new element if the maximum number of elements (based on the configuration)
     * was not reached, otherwise it will randomly return an existing element.
     *
     * @return the created element
     */
    protected final T next() {
        T simulated;
        if (shouldCreate()) {
            simulated = simulate();
            if (simulated != null) save(simulated);
        } else {
            simulated = retriveElement();
        }
        if (simulated == null) {
            throw new SimulationException("NULL element from " + ClassUtils.getName(this));
        }
        return simulated;
    }

    /**
     * Return a random element from the list of elements.
     *
     * @return a non-null element
     */
    protected abstract T retriveElement();

    /**
     * Creates a new element.
     */
    protected abstract T simulate();

    /**
     * Persists the simulated element into the database.
     *
     * @param element the element
     */
    protected final void save(T element) {
        getRepository().save(element);
        postSave(element);
    }

    /**
     * Subclasses can perform additional tasks related to an element.
     *
     * @param element the element
     */
    protected void postSave(T element) {
        // empty by design
    }

}
