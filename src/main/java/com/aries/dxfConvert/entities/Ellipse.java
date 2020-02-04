package com.aries.dxfConvert.entities;

/**
 * Created by Aries
 */
public class Ellipse extends CommonCode {
    /**
     * 填充色
     */
    private Color fillColor;

    /**
     * 短轴长
     */
    private Double lenghtMinor;

    /**
     * 中心点
     */
    private Point centerPoint;

    /**
     * 长轴端点
     */
    private Point endPoint;

    /**
     * 轴长比 Ratio of minor axis to major axis
     * group code: 40
     */
    private Double ratio;

    /**
     * Extrusion direction (optional; default = 0, 0, 1) DXF: X value; APP: 3D vector
     * group code: 210,220,230
     */
    private Double extrusionDirectX;
    private Double extrusionDirectY;
    private Double extrusionDirectZ;

    /**
     * 开始角度
     */
    private Double startAngle;

    /**
     * 结束角度
     */
    private Double endAngle;

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Double getLenghtMinor() {
        return lenghtMinor;
    }

    public void setLenghtMinor(Double lenghtMinor) {
        this.lenghtMinor = lenghtMinor;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(Point centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
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

    public Double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(Double startAngle) {
        this.startAngle = startAngle;
    }

    public Double getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(Double endAngle) {
        this.endAngle = endAngle;
    }
}
