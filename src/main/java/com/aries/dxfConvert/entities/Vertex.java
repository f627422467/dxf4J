package com.aries.dxfConvert.entities;

/**
 * Created by Aries
 */
public class Vertex{
    /**
     * 当前点
     */
    private Point locationPoint;

    /**
     * 开始宽度 Starting width (optional; default is 0)
     * group code: 40
     */
    private Double startWidth;

    /**
     * Ending width (optional; default is 0)
     * group code: 41
     */
    private Double endWidth;

    /**
     * 凸度
     * Bulge (optional; default is 0). The bulge is the tangent of one fourth the included angle for an
     arc segment, made negative if the arc goes clockwise from the start point to the endpoint. A
     bulge of 0 indicates a straight segment, and a bulge of 1 is a semicircle
     * group code: 42
     */
    private Double bulge;

    /**
     * Vertex flags:
         1 = Extra vertex created by curve-fitting
         2 = Curve-fit tangent defined for this vertex. A curve-fit tangent direction of 0 may be omitted
         from DXF output but is significant if this bit is set
         4 = Not used
         8 = Spline vertex created by spline-fitting
         16 = Spline frame control point
         32 = 3D polyline vertex
         64 = 3D polygon mesh
         128 = Polyface mesh vertex
     * group code: 70
     */
    private Integer vertexFlag;

    /**
     * Curve fit tangent direction
     * group code: 50
     */
    private Double curveFitDirect;

    public Point getLocationPoint() {
        return locationPoint;
    }

    public void setLocationPoint(Point locationPoint) {
        this.locationPoint = locationPoint;
    }

    public Double getStartWidth() {
        return startWidth;
    }

    public void setStartWidth(Double startWidth) {
        this.startWidth = startWidth;
    }

    public Double getEndWidth() {
        return endWidth;
    }

    public void setEndWidth(Double endWidth) {
        this.endWidth = endWidth;
    }

    public Double getBulge() {
        return bulge;
    }

    public void setBulge(Double bulge) {
        this.bulge = bulge;
    }

    public Integer getVertexFlag() {
        return vertexFlag;
    }

    public void setVertexFlag(Integer vertexFlag) {
        this.vertexFlag = vertexFlag;
    }

    public Double getCurveFitDirect() {
        return curveFitDirect;
    }

    public void setCurveFitDirect(Double curveFitDirect) {
        this.curveFitDirect = curveFitDirect;
    }
}
