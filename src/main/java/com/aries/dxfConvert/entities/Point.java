package com.aries.dxfConvert.entities;

/**
 * Created by Aries
 */
public class Point {
    /**
     * Point location
     * group code: 10,20,30
     */
    private Double x;
    private Double y;
    private Double z;
    /**
     * thickness
     * group code: 39
     */
    private Integer thickness;
    /**
     * Extrusion direction (optional; default = 0, 0, 1)
     * group code: 220,220,230
     */
    private Double extrusiongDirectX;
    private Double extrusiongDirectY;
    private Double extrusiongDirectZ;

    public Point() {
    }

    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    public Double getExtrusiongDirectX() {
        return extrusiongDirectX;
    }

    public void setExtrusiongDirectX(Double extrusiongDirectX) {
        this.extrusiongDirectX = extrusiongDirectX;
    }

    public Double getExtrusiongDirectY() {
        return extrusiongDirectY;
    }

    public void setExtrusiongDirectY(Double extrusiongDirectY) {
        this.extrusiongDirectY = extrusiongDirectY;
    }

    public Double getExtrusiongDirectZ() {
        return extrusiongDirectZ;
    }

    public void setExtrusiongDirectZ(Double extrusiongDirectZ) {
        this.extrusiongDirectZ = extrusiongDirectZ;
    }
}
