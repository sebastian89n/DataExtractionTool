package com.bastex.dataextractiontool.configuration;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;

@Configuration
@ComponentScan("com.bastex.dataextractiontool")
public class SpringMainConfiguration {
    @Bean
    public List<JacksonJaxbJsonProvider> jacksonJaxbJsonProviders() {
        return Lists.newArrayList(new JacksonJaxbJsonProvider());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
