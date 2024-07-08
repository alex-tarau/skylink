package net.microflax.skylink;

import com.github.javafaker.Aviation;
import com.github.javafaker.Country;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;

public abstract class AbstractService {

    private final Faker faker = new Faker();
    private final Aviation aviation = faker.aviation();
    private final Country country = faker.country();
    private final DateAndTime dateAndTime = getFaker().date();
    private static final int NUMBER_OF_ENTITIES_TO_PERSIST = 6;



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

    public DateAndTime getDateAndTime() {
        return dateAndTime;
    }
}
