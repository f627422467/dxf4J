package com.aries.dxfConvert.entities;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Aries
 */
public class Text extends CommonCode {
    /**
     * 文字 label Default value (the string itself)
     * group code: 1
     */
    private String label;

    /**
     * 字体
     * 本机没有对应字体则：
     * label 中可能带字体信息，提取出来放这里
     */
    private Font font;

    /**
     * 位置
     */
    private Point point;

    /**
     * 高度
     * group code: 40
     */
    private Double height;

    /**
     * 旋转度
     * group code: 50
     */
    private Double rotation;

    /**
     * Relative X scale factor—width (optional; default = 1)
     This value is also adjusted when fit-type text is used
     * group code: 41
     */
    private Double relativeX;

    /**
     * Oblique angle (optional; default = 0)
     * group code: 51
     */
    private Double obliqueAngle;

    /**
     * text style name;
     * group code 7
     */
    private String textStyle;

    /**
     * Text generation flags (optional, default = 0):
         2 = Text is backward (mirrored in X)
         4 = Text is upside down (mirrored in Y)
     * group code: 71
     */
    private Integer generationFlag;

    /**
     * Horizontal text justification type (optional, default = 0) integer codes (not bit-coded)
         0 = Left; 1= Center; 2 = Right
         3 = Aligned (if vertical alignment = 0)
         4 = Middle (if vertical alignment = 0)
     * group code: 72
     */
    private Integer justificationType;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        String font=null, text = label;
        if(StringUtils.isNotBlank(label)){
            if(label.startsWith("{") && label.endsWith("}")){
                label = label.substring(1, label.lastIndexOf("}"));
                String[] labels = label.split(";");
                if(labels.length>0){
                    font = labels[0];
                    text = labels[1];
                }
            }

        }
        if(font != null){
            String[] properties = font.split("\\|");
            if(properties.length>0){
                Font font1 = new Font();
                font1.setFamily(properties[0]);
                font1.setProperties(properties);
                this.setFont(font1);
            }
        }

        this.label = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getRotation() {
        return rotation;
    }

    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }

    public Double getRelativeX() {
        return relativeX;
    }

    public void setRelativeX(Double relativeX) {
        this.relativeX = relativeX;
    }

    public Double getObliqueAngle() {
        return obliqueAngle;
    }

    public void setObliqueAngle(Double obliqueAngle) {
        this.obliqueAngle = obliqueAngle;
    }

    public String getTextStyle() {
        return textStyle;
    }

    public void setTextStyle(String textStyle) {
        this.textStyle = textStyle;
    }

    public Integer getGenerationFlag() {
        return generationFlag;
    }

    public void setGenerationFlag(Integer generationFlag) {
        this.generationFlag = generationFlag;
    }

    public Integer getJustificationType() {
        return justificationType;
    }

    public void setJustificationType(Integer justificationType) {
        this.justificationType = justificationType;
    }
}
