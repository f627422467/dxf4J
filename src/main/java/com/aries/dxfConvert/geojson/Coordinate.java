package com.aries.dxfConvert.geojson;

import com.aries.dxfConvert.entities.Point;

/**
 * Created by tuzhiming on 2018/7/4.
 */
public class Coordinate {

    private Double latitude;

    private Double longitude;

    private Double altitude;


    public void parsePoint(Point point){
        if(point != null){
            longitude = point.getX();
            latitude = point.getY();
            altitude = point.getZ();
        }else {
            longitude = 0d;
            latitude = 0d;
            altitude = 0d;
        }
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }
}
