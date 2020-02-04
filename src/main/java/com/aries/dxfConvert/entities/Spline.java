package com.aries.dxfConvert.entities;

import java.util.List;

/**
 * Created by Aries
 */
public class Spline extends CommonCode{

    /**
     * Spline flag (bit coded):
             1 = Closed spline
             2 = Periodic spline
             4 = Rational spline
             8 = Planar
             16 = Linear (planar bit is also set)
     * group code: 70
     */
    private Integer splineFlag;

    /**
     * 拟合等级
     * Degree of the spline curve
     * group code: 71
     */
    private Integer degree;

    /**
     * 节点数量
     * group code: 72
     */
    private Integer knotsNum;

    /**
     * 控制点数量
     * group code: 73
     */
    private Integer controlsNum;

    /**
     * 拟合点数量
     * group code: 74
     */
    private Integer fitsNum;

    /**
     * 节点偏差 knot tolerance default = 0.0000001
     * group code: 42
     */
    private Double knotTolerance;

    /**
     * 控制点偏差 control point tolerance 0.0000001
     * group code: 43
     */
    private Double controlTolerance;

    /**
     * 拟合点偏差 fit tolerance 0.0000000001
     * group code: 44
     */
    private Double fitTolerance;

    /**
     * 控制点列表
     */
    private List<Point> controls;
    /**
     * 拟合点列表
     */
    private List<Point> fits;

    /**
     * 节点值列表
     */
    private List<Double> knotValues;

    /**
     * 权重列表
     */
    private List<Double> weights;

    /**
     * Normal vector (omitted if the spline is nonplanar)
     * group code: 210,220,230
     */
    private Double normalVectorX;
    private Double normalVectorY;
    private Double normalVectorZ;

    public Integer getSplineFlag() {
        return splineFlag;
    }

    public void setSplineFlag(Integer splineFlag) {
        this.splineFlag = splineFlag;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getKnotsNum() {
        return knotsNum;
    }

    public void setKnotsNum(Integer knotsNum) {
        this.knotsNum = knotsNum;
    }

    public Integer getControlsNum() {
        return controlsNum;
    }

    public void setControlsNum(Integer controlsNum) {
        this.controlsNum = controlsNum;
    }

    public Integer getFitsNum() {
        return fitsNum;
    }

    public void setFitsNum(Integer fitsNum) {
        this.fitsNum = fitsNum;
    }

    public Double getKnotTolerance() {
        return knotTolerance;
    }

    public void setKnotTolerance(Double knotTolerance) {
        this.knotTolerance = knotTolerance;
    }

    public Double getControlTolerance() {
        return controlTolerance;
    }

    public void setControlTolerance(Double controlTolerance) {
        this.controlTolerance = controlTolerance;
    }

    public Double getFitTolerance() {
        return fitTolerance;
    }

    public void setFitTolerance(Double fitTolerance) {
        this.fitTolerance = fitTolerance;
    }

    public List<Point> getControls() {
        return controls;
    }

    public void setControls(List<Point> controls) {
        this.controls = controls;
    }

    public List<Point> getFits() {
        return fits;
    }

    public void setFits(List<Point> fits) {
        this.fits = fits;
    }

    public List<Double> getKnotValues() {
        return knotValues;
    }

    public void setKnotValues(List<Double> knotValues) {
        this.knotValues = knotValues;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    public Double getNormalVectorX() {
        return normalVectorX;
    }

    public void setNormalVectorX(Double normalVectorX) {
        this.normalVectorX = normalVectorX;
    }

    public Double getNormalVectorY() {
        return normalVectorY;
    }

    public void setNormalVectorY(Double normalVectorY) {
        this.normalVectorY = normalVectorY;
    }

    public Double getNormalVectorZ() {
        return normalVectorZ;
    }

    public void setNormalVectorZ(Double normalVectorZ) {
        this.normalVectorZ = normalVectorZ;
    }
}
