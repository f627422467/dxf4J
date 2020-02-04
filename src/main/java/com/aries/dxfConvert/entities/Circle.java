package com.aries.dxfConvert.entities;

/**
 * Created by Aries
 */
public class Circle extends CommonCode{

    /**
     * 填充色
     */
    private Color fillColor;

    /**
     * 圆点
     */
    private Point centerPoint;

    /**
     * 半径
     * group code: 40
     */
    private Double radius;

    /**
     * Extrusion direction (optional; default = 0, 0, 1) DXF: X value; APP: 3D vector
     * group code: 210,220,230
     */
    private Double extrusionDirectX;
    private Double extrusionDirectY;
    private Double extrusionDirectZ;

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(Point centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getExtrusionDirectX() {
        return extrusionDirectX;
    }

    public void setExtrusionDirectX(Double extrusionDirectX) {
        this.extrusionDirectX = extrusionDirectX;
    }

    public Double getExtrusionDirectY() {
        return extrusionDirectY;
    }

    public void setExtrusionDirectY(Double extrusionDirectY) {
        this.extrusionDirectY = extrusionDirectY;
    }

    public Double getExtrusionDirectZ() {
        return extrusionDirectZ;
    }

    public void setExtrusionDirectZ(Double extrusionDirectZ) {
        this.extrusionDirectZ = extrusionDirectZ;
    }
}
