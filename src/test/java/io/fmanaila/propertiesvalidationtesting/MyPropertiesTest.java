package io.fmanaila.propertiesvalidationtesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class MyPropertiesTest {

    @EnableConfigurationProperties(MyProperties.class)
    static class TestConfiguration {
    }

    private ApplicationContextRunner contextRunner() {
        return new ApplicationContextRunner()
                .withUserConfiguration(TestConfiguration.class)
                .withPropertyValues(
                        "myapp.email=test@example.com",
                        "myapp.url=https://example.com"
                );
    }

    @Test
    void givenValidConfigurationProperties_thenContextStarts() {
        contextRunner().run((context) -> assertThat(context).hasNotFailed());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "e", "e.e", "example!", "@", "t@-ee"})
    void givenInvalidEmail_thenContextFails(String email) {
        contextRunner()
            .withPropertyValues("myapp.email=" + email)
            .run((context) -> assertThat(context).hasFailed());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@example.com", "test@example", "test@local-host"})
    void givenValidEmail_thenContextStarts(String email) {
        contextRunner()
                .withPropertyValues("myapp.email=" + email)
                .run((context) -> assertThat(context).hasNotFailed());
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://example.com", "https://one"})
    void givenValidUrl_thenContextStarts(String url) {
        contextRunner()
                .withPropertyValues("myapp.url=" + url)
                .run((context) -> assertThat(context).hasNotFailed());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "e", "e.e", "example!", "±", "http://example.com"})
    void givenInvalidUrl_thenContextFails(String url) {
        contextRunner()
                .withPropertyValues("myapp.url=" + url)
                .run((context) -> assertThat(context).hasFailed());
    }

}