package com.aries.dxfConvert.geojson;

import java.util.List;

/**
 * Created by tuzhiming on 2018/7/4.
 */
public class GeoJSON {
    private String type;

    private List<Feature> features;

    /**
     * 可选
     * 坐标参考系统
     */
    private CRS crs;

    /**
     * 可选
     * length 4; double[4]
     */
    private Double[] bbox;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public CRS getCrs() {
        return crs;
    }

    public void setCrs(CRS crs) {
        this.crs = crs;
    }

    public Double[] getBbox() {
        return bbox;
    }

    public void setBbox(Double[] bbox) {
        this.bbox = bbox;
    }
}
