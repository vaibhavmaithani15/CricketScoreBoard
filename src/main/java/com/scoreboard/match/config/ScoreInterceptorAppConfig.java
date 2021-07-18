package com.scoreboard.match.config;

import com.scoreboard.match.controller.interceptor.ScoreRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class ScoreInterceptorAppConfig extends WebMvcConfigurerAdapter {

    private ScoreRequestInterceptor scoreRequestInterceptor;

    public ScoreInterceptorAppConfig(ScoreRequestInterceptor scoreRequestInterceptor) {
        this.scoreRequestInterceptor = scoreRequestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(scoreRequestInterceptor).addPathPatterns("/score/add");
    }
}
