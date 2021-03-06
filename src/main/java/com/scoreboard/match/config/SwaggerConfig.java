package com.scoreboard.match.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.scoreboard.match.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("Swagger")
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Score Board",
                "Live cricket score board",
                "1.0.0-SNAPSHOT",
                "Terms of Service",
                new Contact("Rahul Maithani", "", "vaibhavmaithani15@gmail.com"),
                "",
                "",
                Collections.emptyList());
    }

}
