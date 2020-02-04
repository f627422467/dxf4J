package com.aries.dxfConvert.entities;

/**
 * Created by Aries
 */
public class Font {

    /**
     * 字体类别 ：
     *      \fSimSun
     */
    private String family;

    /**
     * 大小偏差等，没太懂顺序，所以先写在一起
     */
    private String[] properties;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }
}
