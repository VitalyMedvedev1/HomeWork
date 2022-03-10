package ru.liga.medvedev.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.liga.medvedev")
@PropertySource("classpath:resource.properties")
public class SpringConfiguration {
}


