package com.aries.dxfConvert.geojson;

import java.util.List;

/**
 * Created by Aries
 */
public class Geometry {

    //"Point", "MultiPoint", "LineString", "MultiLineString",  "Polygon", "MultiPolygon
    public static final String GEOMETRY_TYPE_POINT = "POINT";
    public static final String GEOMETRY_TYPE_MULTIPOINT = "MultiPoint";
    public static final String GEOMETRY_TYPE_LINESTRING = "LineString";
    public static final String GEOMETRY_TYPE_MULTILINESTRING = "MultiLineString";
    public static final String GEOMETRY_TYPE_POLYGON = "Polygon";
    public static final String GEOMETRY_TYPE_MULTIPOLYGON = "MultiPolygon";

    /**
     * 类型
     */
    private String type;

    /**
     * 点集合
     */
    private List<Coordinate> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
