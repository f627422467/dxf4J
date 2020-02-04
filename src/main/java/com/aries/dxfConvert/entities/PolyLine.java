package com.aries.dxfConvert.entities;

import java.util.List;

/**
 * Created by Aries
 */
public class PolyLine extends CommonCode{
    /**
     * Obsolete; formerly an “entities follow flag” (optional; ignore if present)
     * group code: 66
     */
    private Integer followFlag;

    /**
     * dummy point -- always 0
     */
    private Point dummyPoint;

    /**
     * polyline flag(bit-coded);
             1 = This is a closed polyline (or a polygon mesh closed in the M direction)
             2 = Curve-fit vertices have been added
             4 = Spline-fit vertices have been added
             8 = This is a 3D polyline
             16 = This is a 3D polygon mesh
             32 = The polygon mesh is closed in the N direction
             64 = The polyline is a polyface mesh
             128 = The linetype pattern is generated continuously around the vertices of this polyline
     * group code: 70
     */
    private Integer polylineFlag;


    /**
     * 默认开始宽度 Starting width (optional; default is 0)
     * group code: 40
     */
    private Double startWidth;

    /**
     * 默认 Ending width (optional; default is 0)
     * group code: 41
     */
    private Double endWidth;
    /**
     * Vertex coordinates (in OCS), multiple entries; one entry for each vertex DXF: X value; APP: 2D point
     * 点列表
     */
    private List<Vertex> vertexCoordinates;

    /**
     * Extrusion direction (optional; default = 0, 0, 1) DXF: X value; APP: 3D vector
     * group code: 210,220,230
     */
    private Double extrusionDirectX;
    private Double extrusionDirectY;
    private Double extrusionDirectZ;

    public List<Vertex> getVertexCoordinates() {
        return vertexCoordinates;
    }

    public void setVertexCoordinates(List<Vertex> vertexCoordinates) {
        this.vertexCoordinates = vertexCoordinates;
    }

    public Integer getFollowFlag() {
        return followFlag;
    }

    public void setFollowFlag(Integer followFlag) {
        this.followFlag = followFlag;
    }

    public Point getDummyPoint() {
        return dummyPoint;
    }

    public void setDummyPoint(Point dummyPoint) {
        this.dummyPoint = dummyPoint;
    }

    public Integer getPolylineFlag() {
        return polylineFlag;
    }

    public void setPolylineFlag(Integer polylineFlag) {
        this.polylineFlag = polylineFlag;
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
