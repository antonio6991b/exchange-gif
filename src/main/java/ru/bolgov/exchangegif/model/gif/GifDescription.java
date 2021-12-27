package ru.bolgov.exchangegif.model.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties
public class GifDescription {
    private GifData data;
    private Map<String, String> meta;

    public GifData getData() {
        return data;
    }

    public void setData(GifData data) {
        this.data = data;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }
}
