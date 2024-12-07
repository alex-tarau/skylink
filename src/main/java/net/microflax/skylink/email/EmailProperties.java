package net.microflax.skylink.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@Getter
@Setter
public class EmailProperties {

    private String host = "smtp.gmail.com";
    private int port = 587;
    private String userName = "skylink";
    private String password = "skylink123";
    private boolean auth = true;
    private boolean tls = true;
}
