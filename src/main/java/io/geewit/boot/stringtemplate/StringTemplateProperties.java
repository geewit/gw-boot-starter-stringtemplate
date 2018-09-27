package io.geewit.boot.stringtemplate;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * antlr stringtemplate properties
 *
 * @author geewit
 */
@ConfigurationProperties(prefix = "geewit.antlr.stringtemplate")
public class StringTemplateProperties {
    private String rootPath;

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
