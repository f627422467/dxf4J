package com.aries.dxfConvert.util;

/**
 * Created by Aries
 */
public class Transform {

    private static final double pi = 3.1415926535897932384626;
    private static final double a = 6378245.0;
    private static final double ee = 0.00669342162296594323;

    public static double[] LLToGCJ02(double lon, double lat){
        double[] rst = new double[2];
        double tlat = transformLat(lon - 105.0, lat - 35.0);
        double tlon = transformLon(lon - 105.0, lat - 35.0);
        double rlat = lat / 180.0 * pi;
        double magic = Math.sin(rlat);
        magic = 1- ee * magic * magic;
        double sqrtM = Math.sqrt(magic);
        tlat = (tlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtM) * pi);
        tlon = (tlon * 180.0) / (a / sqrtM * Math.cos(rlat) * pi);

        rst[0] = lon + tlon;
        rst[1] = lat + tlat;
        return rst;
    }

    /**
     * 某坐标系转换，不公开
     * @param lon
     * @param lat
     * @return
     */
    public static double[] LLToXY(double lon, double lat){
        double[] rst = new double[2];
        rst[0] = lon;
        rst[1] = lat;
        return rst;
    }

    /**
     * 坐标系转换，这暂时不转化
     * @param x
     * @param y
     * @return
     */
    public static double[] XYToLL(double x, double y){
        double[] rst = new double[2];
        rst[0] = x;
        rst[1] = y;
        return rst;
    }

    private static double transformLon(double x, double y){
        double rst = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        rst += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        rst += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        rst += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0/ 3.0;
        return rst;
    }

    private static double transformLat(double x, double y){
        double rst = -100.0 + 2.0 * x + 3.0 *  + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        rst += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        rst += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        rst += (160.0 * Math.sin(y/ 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return rst;
    }
}

















