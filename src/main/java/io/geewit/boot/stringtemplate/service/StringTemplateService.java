package io.geewit.boot.stringtemplate.service;

import io.geewit.boot.stringtemplate.StringTemplateProperties;
import io.geewit.boot.stringtemplate.utils.StringTemplateUtils;
import io.geewit.core.utils.lang.reflection.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author geewit
 */
public class StringTemplateService {
    private final static Logger logger = LoggerFactory.getLogger(StringTemplateService.class);

    private final ResourceLoader resourceLoader;
    private final StringTemplateProperties stringTemplateProperties;

    public StringTemplateService(ResourceLoader resourceLoader, StringTemplateProperties stringTemplateProperties) {
        this.resourceLoader = resourceLoader;
        this.stringTemplateProperties = stringTemplateProperties;
    }

    @SuppressWarnings({"unused"})
    public String render(String templateName, Map<String, Object> params) {
        String template = getTemplate(templateName);
        if(template == null) {
            return null;
        }
        String rendered = StringTemplateUtils.render(template, params);
        logger.debug("rendered : " + rendered);
        return rendered;
    }

    @SuppressWarnings({"unused"})
    public String render(String templateName, Object paramObject) {
        String template = getTemplate(templateName);
        if(template == null) {
            return null;
        }
        Map<String, Object> params = new HashMap<>();
        BeanUtils.copyProperties(paramObject, params);
        String rendered = StringTemplateUtils.render(template, params);
        logger.debug("rendered : " + rendered);
        return rendered;
    }

    private String getTemplate(String templateName) {
        logger.debug("templateName : " + templateName);
        String templatePath = stringTemplateProperties.getRootPath() + templateName + ".tpl";
        Resource resource = resourceLoader.getResource("classpath:" + templatePath);
        try {
            String template = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
            logger.debug("template : " + template);
            return template;
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
            return null;
        }
    }
}
