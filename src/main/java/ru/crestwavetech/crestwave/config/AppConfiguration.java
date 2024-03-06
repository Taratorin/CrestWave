package ru.crestwavetech.crestwave.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.property")
public class AppConfiguration implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Value("${http.port}")
    public String port;

        @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(Integer.parseInt(port));
    }
}