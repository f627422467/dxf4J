package com.aries.dxfConvert.entities;

/**
 * 通用组码
 * Created by Aries
 */
public class CommonCode {
    /**
     * 62 color
     */
    private Color color;

    /**
     * 5 handle id
      */
    private String handleId;

    /**
     * 8 layer name
     */
    private String layerName;

    public String getColorRGBStr(){
        String rgbStr = "";
        if(color != null ){
            rgbStr = color.getColorRGBStr();
        }
        return rgbStr;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getHandleId() {
        return handleId;
    }

    public void setHandleId(String handleId) {
        this.handleId = handleId;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }
}
