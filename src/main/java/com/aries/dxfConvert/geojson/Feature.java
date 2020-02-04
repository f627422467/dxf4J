package com.aries.dxfConvert.geojson;

import java.util.Map;

/**
 * Created by tuzhiming on 2018/7/4.
 */
public class Feature {

    public static final String FEATURE_TYPE_FEATURE = "Feature";

    private String type;

    private Geometry geometry;

    private Map<String,Object> properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
