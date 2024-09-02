package net.microflax.skylink.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for all simulators with a cache.
 *
 * @param <T>  the entity type
 * @param <ID> the identifier type
 */
public abstract class AbstractCacheSimulator<T, ID> extends AbstractSimulator<T, ID> {

    private volatile List<T> cache = Collections.emptyList();

    /**
     * Returns the number of elements which exists in the database.
     *
     * @return a positive integer
     */
    protected final int getElementCount() {
        if (cache.isEmpty()) cache = new ArrayList<>(getRepository().findAll());
        return cache.size();
    }

    @Override
    protected final T retriveElement() {
        return cache.get(random.nextInt(cache.size()));
    }

    @Override
    protected void postSave(T element) {
        super.postSave(element);
        cache.add(element);
    }
}
