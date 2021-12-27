package ru.bolgov.exchangegif.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "program-settings")
@PropertySource("classpath:program-settings.properties")
public class ProgramSettings {

    @Value("${openexchangeKey}")
    private String openexchangeKey;

    @Value("${openexchangeUrl}")
    private String openexchangeUrl;

    @Value("${giphyKey}")
    private String giphyKey;

    @Value("${giphyUrl}")
    private String giphyUrl;

    @Value("${richTag}")
    private String richTag;

    @Value("${brokeTag}")
    private String brokeTag;

    @Value("${base}")
    private String base;

    public String getOpenexchangeKey() {
        return openexchangeKey;
    }

    public void setOpenexchangeKey(String openexchangeKey) {
        this.openexchangeKey = openexchangeKey;
    }

    public String getGiphyKey() {
        return giphyKey;
    }

    public void setGiphyKey(String giphyKey) {
        this.giphyKey = giphyKey;
    }

    public String getRichTag() {
        return richTag;
    }

    public void setRichTag(String richTag) {
        this.richTag = richTag;
    }

    public String getBrokeTag() {
        return brokeTag;
    }

    public void setBrokeTag(String brokeTag) {
        this.brokeTag = brokeTag;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getOpenexchangeUrl() {
        return openexchangeUrl;
    }

    public void setOpenexchangeUrl(String openexchangeUrl) {
        this.openexchangeUrl = openexchangeUrl;
    }

    public String getGiphyUrl() {
        return giphyUrl;
    }

    public void setGiphyUrl(String giphyUrl) {
        this.giphyUrl = giphyUrl;
    }
}
