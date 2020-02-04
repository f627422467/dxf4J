package com.aries.dxfConvert.entities;

import java.util.List;

/**
 * Created by Aries
 */
public class LwPolyLine extends CommonCode{
    /**
     * number of vertices
     * group code: 90
     */
    private Integer verticesNum;

    /**
     * polyline flag(bit-coded); default is 0: 1 = Closed; 128 = Plinegen
     * group code: 70
     */
    private Integer polylineFlag;

    /**
     * Constant width (optional; default = 0). Not used if variable width (codes 40 and/or 41) is set
     * group code: 43
     */
    private Double constantWidth;

    /**
     * Elevation (optional; default = 0)
     * group code: 38
     */
    private Integer elevation;

    /**
     * Vertex coordinates (in OCS), multiple entries; one entry for each vertex DXF: X value; APP: 2D point
     * 点列表
     * group code: {10,20,30}(点),40,41,42都在vertex中
     */
    private List<Vertex> vertexCoordinates;
    /**
     * Extrusion direction (optional; default = 0, 0, 1) DXF: X value; APP: 3D vector
     * group code: 210,220,230
     */
    private Double extrusionDirectX;
    private Double extrusionDirectY;
    private Double extrusionDirectZ;

    public Integer getVerticesNum() {
        return verticesNum;
    }

    public void setVerticesNum(Integer verticesNum) {
        this.verticesNum = verticesNum;
    }

    public Integer getPolylineFlag() {
        return polylineFlag;
    }

    public void setPolylineFlag(Integer polylineFlag) {
        this.polylineFlag = polylineFlag;
    }

    public Double getConstantWidth() {
        return constantWidth;
    }

    public void setConstantWidth(Double constantWidth) {
        this.constantWidth = constantWidth;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public List<Vertex> getVertexCoordinates() {
        return vertexCoordinates;
    }

    public void setVertexCoordinates(List<Vertex> vertexCoordinates) {
        this.vertexCoordinates = vertexCoordinates;
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
