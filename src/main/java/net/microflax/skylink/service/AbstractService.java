package net.microflax.skylink.service;

import com.github.javafaker.Aviation;
import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import org.slf4j.Logger;

public abstract class AbstractService<E> {

    private final Faker faker = new Faker();
    private final Aviation aviation = faker.aviation();
    private final Country country = faker.country();
    protected static final int NUMBER_OF_ENTITIES_TO_PERSIST = 6;

    /**
     * Persist an entity to the database
     */
    public abstract void persist(E entity);

    /**
     * Generate entities to display in the UI
     */
    public abstract void generate();

    public Faker getFaker() {
        return faker;
    }

    public Country getCountry() {
        return country;
    }

    public Aviation getAviation() {
        return aviation;
    }
}
