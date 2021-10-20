package io.fmanaila.propertiesvalidationtesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MyProperties.class)
public class SpringPropertiesValidationTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPropertiesValidationTestingApplication.class, args);
    }

}
