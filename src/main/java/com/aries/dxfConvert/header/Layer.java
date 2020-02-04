package com.aries.dxfConvert.header;

import com.aries.dxfConvert.entities.Color;

/**
 * Created by Aries
 */
public class Layer {
    /**
     * layer name
     * group code: 2
     */
    private String name;

    /**
     * standard flags
         *1 = Layer is frozen; otherwise layer is thawed
         2 = Layer is frozen by default in new viewports
         4 = Layer is locked
         16 = If set, table entry is externally dependent on an xref
         32 = If both this bit and bit 16 are set, the externally dependent xref has been successfully resolved
         64 = If set, the table entry was referenced by at least one entity in the drawing the last time the
         drawing was edited. (This flag is for the benefit of AutoCAD commands. It can be ignored by
         most programs that read DXF files and need not be set by programs that write DXF files)
     * group code: 70
     */
    private Integer flag;

    /**
     * color num
     * group code: 62
     */
    private Color color;

    /**
     * linetype name
     * group code: 6
     */
    private String lineTypeName;

    /**
     * plotting flag; if set to 0, do not plot this layer
     * group code: 290
     */
    private Integer plotFlag;

    /**
     * line weight enum value
     * group code : 370
     */
    private Double lineWeight;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getLineTypeName() {
        return lineTypeName;
    }

    public void setLineTypeName(String lineTypeName) {
        this.lineTypeName = lineTypeName;
    }

    public Integer getPlotFlag() {
        return plotFlag;
    }

    public void setPlotFlag(Integer plotFlag) {
        this.plotFlag = plotFlag;
    }

    public Double getLineWeight() {
        return lineWeight;
    }

    public void setLineWeight(Double lineWeight) {
        this.lineWeight = lineWeight;
    }
}
