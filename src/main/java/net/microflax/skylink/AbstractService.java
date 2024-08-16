package net.microflax.skylink;

/**
 * The base class for services.
 */
public abstract class AbstractService<T> {

    /**
     * Persist the entity to the database
     *
     * @param entity the entity
     */
    public abstract void persist(T entity);

}
