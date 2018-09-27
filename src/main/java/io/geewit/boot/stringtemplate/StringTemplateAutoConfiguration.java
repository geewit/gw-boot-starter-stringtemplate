package io.geewit.boot.stringtemplate;

import io.geewit.boot.stringtemplate.service.StringTemplateService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.stringtemplate.v4.ST;

/**
 * antlr string template auto configuration
 *
 * @author geewit
 */
@Configuration
@ConditionalOnClass({ ST.class })
@EnableConfigurationProperties({StringTemplateProperties.class})
public class StringTemplateAutoConfiguration {
    private final ResourceLoader resourceLoader;
    private final StringTemplateProperties stringTemplateProperties;

    public StringTemplateAutoConfiguration(ResourceLoader resourceLoader, StringTemplateProperties stringTemplateProperties) {
        this.resourceLoader = resourceLoader;
        this.stringTemplateProperties = stringTemplateProperties;
    }

    @Bean
    public StringTemplateService stService() {
        return new StringTemplateService(resourceLoader, stringTemplateProperties);
    }
}
