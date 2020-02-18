package com.aries.dxfConvert.entities;

import java.util.List;

/**
 * 外扩图
 */
public class OutLineMap {

    private String layerName;

    private Point miniPoint;

    private List<Point> points;

    private List<Point> oppositePoints;

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public Point getMiniPoint() {
        return miniPoint;
    }

    public void setMiniPoint(Point miniPoint) {
        this.miniPoint = miniPoint;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public List<Point> getOppositePoints() {
        return oppositePoints;
    }

    public void setOppositePoints(List<Point> oppositePoints) {
        this.oppositePoints = oppositePoints;
    }
}
