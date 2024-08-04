package net.microflax.skylink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({"net.microfalx.bootstrap", "net.microflax.skylink"})
@EnableJpaRepositories({"net.microfalx.bootstrap", "net.microflax.skylink"})
@EntityScan({"net.microfalx.bootstrap", "net.microflax.skylink"})
@EnableTransactionManagement
public class SkylinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkylinkApplication.class, args);
    }

}
