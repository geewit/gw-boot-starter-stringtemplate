package io.geewit.boot.stringtemplate.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stringtemplate.v4.ST;

import java.util.Map;


/**
 * @author geewit
 */
public class StringTemplateUtils {
    private final static Logger logger = LoggerFactory.getLogger(StringTemplateUtils.class);


    public static String render(String template, char delimiterToken, Map<String, Object> params) {
        logger.debug("template : " + template);
        ST st = new ST(template, delimiterToken, delimiterToken);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            st.add(entry.getKey(), entry.getValue());
        }
        String render = st.render();
        logger.debug("render : " + render);
        return render;
    }

	/**
     * render from Map<String, Object> to String
     * @param template 模板string
     * @param params
     * @return
     */
    public static String render(String template, Map<String, Object> params) {
        return render(template, '$', params);
    }
}
