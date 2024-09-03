package net.microflax.skylink;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The base class for services.
 */
public abstract class AbstractService<T, ID> {

    /**
     * Persist the entity to the database
     *
     * @param entity the entity
     */
    public final void persist(T entity) {
        T preSaveEntity = preSave(entity);
        if (preSaveEntity == null) getRepository().save(entity);
        getRepository().save(preSaveEntity);
    }

    /**
     * Returns the repository which gives access to the persisted elements.
     *
     * @return a non-null instance
     */
    protected abstract JpaRepository<T, ID> getRepository();

    /**
     * Return the entity that encapsulated require data before persisting in the database
     *
     * @param entity the entity
     * @return the entity that encapsulated require data before persisting in the database
     */
    protected abstract T preSave(T entity);

}
