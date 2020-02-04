package com.aries.dxfConvert.entities;

/**
 * Created by Aries
 */
public class Line extends CommonCode{
    /**
     * 开始点
     */
    private Point startPoint;
    /**
     * 结束点
     */
    private Point endPoint;

    /**
     * 线宽度
     * @return
     */
    private Double lineWidth;

    public Double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(Double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
