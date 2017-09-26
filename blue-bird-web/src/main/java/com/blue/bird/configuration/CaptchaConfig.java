package com.blue.bird.configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

import static org.apache.commons.codec.CharEncoding.UTF_8;

/**
 * Created by jim on 2017/9/27.
 */
@Component
public class CaptchaConfig {

    @Value("${kaptcha.config.path}")
    private Resource resource;

    @Bean
    public DefaultKaptcha getKaptchaBean() throws IOException {

        Properties properties = PropertiesLoaderUtils.loadProperties(new EncodedResource(resource, UTF_8));
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
