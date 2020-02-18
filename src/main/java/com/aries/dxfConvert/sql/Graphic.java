package com.aries.dxfConvert.sql;

import java.math.BigInteger;

/**
 * 先做个简单的保存数据库
 * Created by Aries
 */
public class Graphic {
    /**
     * cad 图形类型
     */
    public static final int GRAPHIC_TYPE__POINT = 1;
    public static final int GRAPHIC_TYPE_LINE = 2;
    public static final int GRAPHIC_TYPE_LWPOLYLINE = 3;
    public static final int GRAPHIC_TYPE_POLYLINE = 4;
    public static final int GRAPHIC_TYPE_SPLINE = 5;
    public static final int GRAPHIC_TYPE_ARC = 6;
    public static final int GRAPHIC_TYPE_CIRCLE = 7;
    public static final int GRAPHIC_TYPE_ELLIPSE = 8;
    public static final int GRAPHIC_TYPE_TEXT = 9;
    public static final int GRAPHIC_TYPE_MTEXT = 10;
    public static final int GRAPHIC_TYPE_AcDbPOLYLINE = 11;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 颜色
     */
    private String color;

    /**
     * 填充颜色
     */
    private String fillColor;

    /**
     * 1- 实线 2-虚线
     */
    private Integer lineType;

    /**
     * 线宽
     */
    private Double lineWidth;

    /**
     * 图层顺序
     */
    private Integer layerIndex;

    /**
     * 图层名称
     */
    private String layerName;

    /**
     * 图形名称
     */
    private String name;

    /**
     * 图形为Text时，内容
     */
    private String label;

    /**
     * 点个数
     */
    private BigInteger pointCount;

    /**
     * 转换成点列表字符串
     */
    private String pointList;

    /**
     * 转换成gcj02点列表字符串
     */
//    private String gcj02List;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public Integer getLineType() {
        return lineType;
    }

    public void setLineType(Integer lineType) {
        this.lineType = lineType;
    }

    public Double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(Double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public Integer getLayerIndex() {
        return layerIndex;
    }

    public void setLayerIndex(Integer layerIndex) {
        this.layerIndex = layerIndex;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigInteger getPointCount() {
        return pointCount;
    }

    public void setPointCount(BigInteger pointCount) {
        this.pointCount = pointCount;
    }

    public String getPointList() {
        return pointList;
    }

    public void setPointList(String pointList) {
        this.pointList = pointList;
    }
}
