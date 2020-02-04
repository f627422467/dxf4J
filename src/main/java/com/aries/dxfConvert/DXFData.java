package com.aries.dxfConvert;

import com.aries.dxfConvert.entities.*;
import com.aries.dxfConvert.header.Layer;

import java.util.ArrayList;
import java.util.List;

/**
 * dxf 数据结构
 * Created by Aries
 */
public class DXFData {

    private Point extMin;
    private Point extMax;

    private List<Line> lines;
    private List<LwPolyLine> lwPolyLines;
    private List<PolyLine> polyLines;
    private List<Text> texts;
    private List<MText> mTexts;
    private List<ARC> arcs;
    private List<Circle> circles;
    private List<Ellipse> ellipses;
    private List<Spline> splines;
    private List<Hatch> hatches;
    private List<Layer> layers;

    public DXFData() {
        lines = new ArrayList<>();
        lwPolyLines = new ArrayList<>();
        polyLines = new ArrayList<>();
        texts = new ArrayList<>();
        mTexts = new ArrayList<>();
        arcs = new ArrayList<>();
        circles = new ArrayList<>();
        ellipses = new ArrayList<>();
        splines = new ArrayList<>();
        hatches = new ArrayList<>();
        layers = new ArrayList<>();
    }

    public Point getExtMin() {
        return extMin;
    }

    public void setExtMin(Point extMin) {
        this.extMin = extMin;
    }

    public Point getExtMax() {
        return extMax;
    }

    public void setExtMax(Point extMax) {
        this.extMax = extMax;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<LwPolyLine> getLwPolyLines() {
        return lwPolyLines;
    }

    public void setLwPolyLines(List<LwPolyLine> lwPolyLines) {
        this.lwPolyLines = lwPolyLines;
    }

    public List<PolyLine> getPolyLines() {
        return polyLines;
    }

    public void setPolyLines(List<PolyLine> polyLines) {
        this.polyLines = polyLines;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public void setTexts(List<Text> texts) {
        this.texts = texts;
    }

    public List<MText> getmTexts() {
        return mTexts;
    }

    public void setmTexts(List<MText> mTexts) {
        this.mTexts = mTexts;
    }

    public List<ARC> getArcs() {
        return arcs;
    }

    public void setArcs(List<ARC> arcs) {
        this.arcs = arcs;
    }

    public List<Circle> getCircles() {
        return circles;
    }

    public void setCircles(List<Circle> circles) {
        this.circles = circles;
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

    public List<Hatch> getHatches() {
        return hatches;
    }

    public void setHatches(List<Hatch> hatches) {
        this.hatches = hatches;
    }

    @Override
    public String toString() {
        return "{" +
                "lines:" + lines.size() +
                ", lwPolyLines:" + lwPolyLines.size() +
                ", polyLines:" + polyLines.size() +
                ", tests:" + texts.size() +
                ", arcs:" + arcs.size() +
                ", circles:" + circles.size() +
                ", ellipses:" + ellipses.size() +
                ", splines:" + splines.size() +
                ", hatches:" + hatches.size() +
                ", layers:" + layers.size() +
                '}';
    }
}
