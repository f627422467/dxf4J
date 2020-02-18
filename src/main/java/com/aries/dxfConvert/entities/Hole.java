package com.aries.dxfConvert.entities;

import java.util.List;

/**
 * 孔
 */
public class Hole {

    private String layerName;

    //原始圆心
    private Point centerPoint;

    //半径
    private Double radius;

    //深度
    private Double deep;

    //相对圆心
    private Point oppositePoint;

    //是否贯通
    private boolean isThrough;


    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
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

    public Point getOppositePoint() {
        return oppositePoint;
    }

    public void setOppositePoint(Point oppositePoint) {
        this.oppositePoint = oppositePoint;
    }

    public Double getDeep() {
        return deep;
    }

    public void setDeep(Double deep) {
        this.deep = deep;
    }

    public boolean getIsThrough() {
        return isThrough;
    }

    public void setIsThrough(boolean isThrough) {
        this.isThrough = isThrough;
    }
}
