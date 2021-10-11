package com.scoreboard.match.config;

import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorMetricsConfig {

    private PrometheusMeterRegistry registry;
    @Bean
    InitializingBean forcePrometheusPostProcessor(BeanPostProcessor meterRegistryPostProcessor) {
        registry= new PrometheusMeterRegistry(new PrometheusConfig() {
            @Override
            public String get(String key) {
                return null;
            }
        });
        return () -> meterRegistryPostProcessor.postProcessAfterInitialization(registry, "");
    }


}

