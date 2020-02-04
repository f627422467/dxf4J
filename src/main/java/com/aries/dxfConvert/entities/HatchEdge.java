package com.aries.dxfConvert.entities;

import java.util.List;

/**
 * Created by Aries
 */
public class HatchEdge {

    /**
     * 对应 图形的 handleId
     */
    private String softPointerId;

    /**
     * 线段
     */
    private List<Line> lines;

    /**
     * 多段线
     */
    private List<PolyLine> polyLines;

    /**
     * 圆弧
     */
    private List<ARC> arcs;

    /**
     * 椭圆
     */
    private List<Ellipse> ellipses;

    /**
     * 样条曲线
     */
    private List<Spline> splines;

    public String getSoftPointerId() {
        return softPointerId;
    }

    public void setSoftPointerId(String softPointerId) {
        this.softPointerId = softPointerId;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<PolyLine> getPolyLines() {
        return polyLines;
    }

    public void setPolyLines(List<PolyLine> polyLines) {
        this.polyLines = polyLines;
    }

    public List<ARC> getArcs() {
        return arcs;
    }

    public void setArcs(List<ARC> arcs) {
        this.arcs = arcs;
    }

    public List<Ellipse> getEllipses() {
        return ellipses;
    }

    public void setEllipses(List<Ellipse> ellipses) {
        this.ellipses = ellipses;
    }

    public List<Spline> getSplines() {
        return splines;
    }

    public void setSplines(List<Spline> splines) {
        this.splines = splines;
    }
}
