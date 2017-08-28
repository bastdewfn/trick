package com.pfq.trick.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.util.*;

/**
 * Created by huwei on 2017/8/7.
 */
@Configuration
@ConfigurationProperties()
@PropertySource("classpath:/trick.properties")
public class TrickConfig   {
   private Map<String, TrickProperties> trick;

    public Map<String, TrickProperties> getTrick() {
        return trick;
    }

    public void setTrick(Map<String, TrickProperties> trick) {
        this.trick = trick;
    }


}
