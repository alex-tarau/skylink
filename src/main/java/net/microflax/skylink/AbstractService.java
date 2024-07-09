package net.microflax.skylink;

import com.github.javafaker.Aviation;
import com.github.javafaker.Country;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;

/**
 * The base class for services
 */
public abstract class AbstractService {


    /**
     * Generate entities to display in the UI
     */
    public abstract void generate();


}
