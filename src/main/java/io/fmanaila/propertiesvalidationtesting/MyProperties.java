package io.fmanaila.propertiesvalidationtesting;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties(prefix = "myapp")
public class MyProperties {

    @Email
    @NotBlank
    private String email;

    @URL(protocol = "https")
    @NotBlank(message = "URL must not be blank")
    private String url;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
