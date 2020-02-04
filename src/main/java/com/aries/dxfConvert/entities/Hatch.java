package com.aries.dxfConvert.entities;

import java.util.List;

/**
 * Created by Aries
 */
public class Hatch extends CommonCode{

    /**
     * elevation point
     * group code: {10, 20, 30}
     */
    private Point elevPoint;

    /**
     * 边界 图形数据
     * 这里不太清楚是否有多个 edge 所以这里写多个的情况
     */
    private List<HatchEdge> hatchEdges;

    public Point getElevPoint() {
        return elevPoint;
    }

    public void setElevPoint(Point elevPoint) {
        this.elevPoint = elevPoint;
    }

    public List<HatchEdge> getHatchEdges() {
        return hatchEdges;
    }

    public void setHatchEdges(List<HatchEdge> hatchEdges) {
        this.hatchEdges = hatchEdges;
    }
}
