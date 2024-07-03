package net.microflax.skylink.service;

import com.github.javafaker.Aviation;
import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import org.slf4j.Logger;

public abstract class AbstractService {

    protected final Faker faker = new Faker();
    protected final Aviation aviation = faker.aviation();
    protected final Country country = faker.country();
    protected static final int NUMBER_OF_ENTITIES_TO_PERSIST = 6;

    public abstract void persist();
}
