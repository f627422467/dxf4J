package com.aries.dxfConvert;

import com.aries.dxfConvert.entities.*;
import com.aries.dxfConvert.sql.Graphic;
import com.aries.dxfConvert.util.Transform;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by Aries
 */
public class GraphicConverter {

    private static final String LLSCALEFORMAT = "%.6f";
    private static final String MAP_KEY_COUNT = "count";
    private static final String MAP_KEY_POINT = "point";
    private static final String MAP_KEY_POINTS = "list";

    // 多段线闭合flag值
    private static final int POLYLINE_FLAG_CLOSED = 1;

    // 圆上点间隔
    private static final Double CIRCLR_ELLIPSE_ARC_PER_POINT = 20d;
    // 凸度 圆弧取点条件 2端点间隔 大于等于此值
    private static final Double POLYLINE_TUDU_DIS = 100d;

    private List<Hatch> hatches;

    public List<Graphic> converter(DXFData data){
        hatches = data.getHatches();
        List<Graphic> list = new ArrayList<>();
        convertText(data.getTexts(), list);
        convertMText(data.getmTexts(), list);
        convertLines(data.getLines(), list);
        convertLwPolyLine(data.getLwPolyLines(), list);
        convertPolyLine(data.getPolyLines(), list);
        convertARC(data.getArcs(), list);
        convertCircle(data.getCircles(), list);
        convertEllipse(data.getEllipses(), list);
        convertSpline(data.getSplines(), list);
        return list;
    }


    /**
     * 样条曲线
     * @param splines
     * @param list
     */
    private void convertSpline(List<Spline> splines, List<Graphic> list){
        Iterator<Spline> iterator = splines.iterator();
        while (iterator.hasNext()){
            Spline spline = iterator.next();
            Graphic graphic = new Graphic();
            graphic.setType(Graphic.GRAPHIC_TYPE_SPLINE);
            graphic.setColor(spline.getColorRGBStr());
            graphic.setFillColor(getFillColor(spline.getHandleId()).getColorRGBStr());
            graphic.setLayerName(spline.getLayerName());
            Map<String,Object> map = parseSpline(spline);

            graphic.setPointCount((BigInteger)map.get(MAP_KEY_COUNT));
            graphic.setPointList((String)map.get(MAP_KEY_POINT));
            if(StringUtils.isNotBlank(graphic.getPointList())){
                list.add(graphic);
            }
        }
    }

    /**
     * 椭圆
     * @param ellipses
     * @param list
     */
    private void convertEllipse(List<Ellipse> ellipses, List<Graphic> list){
        Iterator<Ellipse> iterator = ellipses.iterator();
        while (iterator.hasNext()){
            Ellipse ellipse = iterator.next();
            Graphic graphic = new Graphic();
            graphic.setLayerName(ellipse.getLayerName());
            graphic.setColor(ellipse.getColorRGBStr());
            graphic.setFillColor(getFillColor(ellipse.getHandleId()).getColorRGBStr());
            graphic.setType(Graphic.GRAPHIC_TYPE_ELLIPSE);
            Map<String,Object> map = parseEllipse(ellipse);

            graphic.setPointCount((BigInteger)map.get(MAP_KEY_COUNT));
            graphic.setPointList((String)map.get(MAP_KEY_POINT));
            if(StringUtils.isNotBlank(graphic.getPointList())){
                list.add(graphic);
            }
        }
    }

    /**
     * 圆
     * @param circles
     * @param list
     */
    private void convertCircle(List<Circle> circles, List<Graphic> list){
        Iterator<Circle> iterator = circles.iterator();
        while (iterator.hasNext()){
            Circle circle = iterator.next();
            Graphic graphic = new Graphic();
            graphic.setLayerName(circle.getLayerName());
            graphic.setColor(circle.getColorRGBStr());
            graphic.setFillColor(getFillColor(circle.getHandleId()).getColorRGBStr());
            graphic.setType(Graphic.GRAPHIC_TYPE_CIRCLE);
            Map<String,Object> map = parseCircleOrArc(Graphic.GRAPHIC_TYPE_CIRCLE, circle.getCenterPoint(), circle.getRadius(), 0d, 360d);
            graphic.setPointCount((BigInteger)map.get(MAP_KEY_COUNT));
            graphic.setPointList((String)map.get(MAP_KEY_POINT));
            if(StringUtils.isNotBlank(graphic.getPointList())){
                list.add(graphic);
            }
        }
    }

    /**
     * 转换 圆弧
     * @param arcs
     * @param list
     */
    private void convertARC(List<ARC> arcs, List<Graphic> list){
        Iterator<ARC> iterator = arcs.iterator();
        while(iterator.hasNext()){
            ARC arc = iterator.next();
            Graphic graphic = new Graphic();
            graphic.setLayerName(arc.getLayerName());
            graphic.setColor(arc.getColorRGBStr());
            graphic.setFillColor(getFillColor(arc.getHandleId()).getColorRGBStr());
            graphic.setType(Graphic.GRAPHIC_TYPE_CIRCLE);
            Map<String,Object> map = parseCircleOrArc(Graphic.GRAPHIC_TYPE_ARC, arc.getCenterPoint(), arc.getRadius(), arc.getStartAngle(), arc.getEndAngle());
            graphic.setPointCount((BigInteger)map.get(MAP_KEY_COUNT));
            graphic.setPointList((String)map.get(MAP_KEY_POINT));
            if(StringUtils.isNotBlank(graphic.getPointList())){
                list.add(graphic);
            }
        }
    }

    /**
     * 多行文字
     * @param mTexts
     * @param list
     */
    private void convertMText(List<MText> mTexts, List<Graphic> list){
        Iterator<MText> iterator = mTexts.iterator();
        while(iterator.hasNext()){
            MText mText = iterator.next();
            Graphic graphic = new Graphic();
            graphic.setPointCount(new BigInteger("1"));
            graphic.setColor(mText.getColorRGBStr());
            graphic.setLayerName(mText.getLayerName());
            graphic.setLabel(mText.getLabel());
            graphic.setType(Graphic.GRAPHIC_TYPE_MTEXT);
            List<Point> points = new ArrayList<>(1);
            points.add(mText.getPoint());
            Map<String,Object> map = pointsToStr(points);
            graphic.setPointCount((BigInteger)map.get(MAP_KEY_COUNT));
            graphic.setPointList((String)map.get(MAP_KEY_POINT));
            if(StringUtils.isNotBlank(graphic.getPointList())){
                list.add(graphic);
            }
        }
    }

    /**
     * 转换文字
     * @param texts
     * @param list
     */
    private void convertText(List<Text> texts, List<Graphic> list){
        Iterator<Text> iterator = texts.iterator();
        while (iterator.hasNext()){
            Text text = iterator.next();
            Graphic graphic = new Graphic();
            graphic.setPointCount(new BigInteger("1"));
            graphic.setColor(text.getColorRGBStr());
            graphic.setLayerName(text.getLayerName());
            graphic.setLabel(text.getLabel());
            graphic.setType(Graphic.GRAPHIC_TYPE_TEXT);
            List<Point> points = new ArrayList<>(1);
            points.add(text.getPoint());
            Map<String,Object> map = pointsToStr(points);
            graphic.setPointCount((BigInteger)map.get(MAP_KEY_COUNT));
            graphic.setPointList((String)map.get(MAP_KEY_POINT));
            if(StringUtils.isNotBlank(graphic.getPointList())){
                list.add(graphic);
            }
        }
    }

    /**
     * 转换 二维多段线
     * @param polyLines
     * @param list
     */
    private void convertPolyLine(List<PolyLine> polyLines, List<Graphic> list){
        Iterator<PolyLine> iterator = polyLines.iterator();
        while (iterator.hasNext()){
            PolyLine polyLine = iterator.next();
            Graphic graphic = new Graphic();
            graphic.setType(Graphic.GRAPHIC_TYPE_POLYLINE);
            graphic.setLayerName(polyLine.getLayerName());
            graphic.setColor(polyLine.getColorRGBStr());
            graphic.setFillColor(getFillColor(polyLine.getHandleId()).getColorRGBStr());
            Map<String,Object> map = convertVertex(polyLine.getPolylineFlag(), polyLine.getVertexCoordinates());
            graphic.setPointCount((BigInteger)map.get(MAP_KEY_COUNT));
            graphic.setPointList((String)map.get(MAP_KEY_POINT));
            if(StringUtils.isNotBlank(graphic.getPointList())){
                list.add(graphic);
            }
        }
    }

    /**
     * 转换多段线
     * @param lwPolyLines
     * @param list
     */
    private void convertLwPolyLine(List<LwPolyLine> lwPolyLines, List<Graphic> list){
        Iterator<LwPolyLine> iterator = lwPolyLines.iterator();
        while(iterator.hasNext()){
            LwPolyLine lwPolyLine = iterator.next();
            Graphic graphic = new Graphic();
            graphic.setType(Graphic.GRAPHIC_TYPE_LWPOLYLINE);
            graphic.setLayerName(lwPolyLine.getLayerName());
            graphic.setColor(lwPolyLine.getColorRGBStr());
            graphic.setFillColor(getFillColor(lwPolyLine.getHandleId()).getColorRGBStr());
            Map<String,Object> map = convertVertex(lwPolyLine.getPolylineFlag(), lwPolyLine.getVertexCoordinates());
            graphic.setPointCount((BigInteger)map.get(MAP_KEY_COUNT));
            graphic.setPointList((String) map.get(MAP_KEY_POINT));

            if(StringUtils.isNotBlank(graphic.getPointList())){
                list.add(graphic);
            }
        }
    }

    /**
     * 转换线段的数据
     * @param lines
     * @param list
     */
    private void convertLines(List<Line> lines, List<Graphic> list) {
        Iterator<Line> iterator = lines.iterator();
        while (iterator.hasNext()){
            Line line = iterator.next();
            Graphic graphic = new Graphic();
            graphic.setColor(line.getColorRGBStr());
            graphic.setLayerName(line.getLayerName());
            graphic.setLineWidth(line.getLineWidth());
            graphic.setType(Graphic.GRAPHIC_TYPE_LINE);
            Point pointStart = transformPointGPS(line.getStartPoint());
            Point pointEnd = transformPointGPS(line.getEndPoint());

            StringBuffer gpsListStr = new StringBuffer();
            gpsListStr.append(String.format(LLSCALEFORMAT, pointStart.getX()));
            gpsListStr.append(",");
            gpsListStr.append(String.format(LLSCALEFORMAT, pointStart.getY()));
            gpsListStr.append(";");
            gpsListStr.append(String.format(LLSCALEFORMAT, pointEnd.getX()));
            gpsListStr.append(",");
            gpsListStr.append(String.format(LLSCALEFORMAT, pointEnd.getY()));
            graphic.setPointList(gpsListStr.toString());
            graphic.setPointCount(new BigInteger("2"));

            if(StringUtils.isNotBlank(graphic.getPointList())){
                list.add(graphic);
            }
        }
    }

    /**
     * 多段线二维多段线点转换
     * @param flag 是否闭合
     * @param vertices
     * @return
     */
    private Map<String,Object> convertVertex(Integer flag, List<Vertex> vertices){
        Map<String,Object> map = new HashMap<>();
        Iterator<Vertex> itr = vertices.iterator();
        List<Point> list = new ArrayList<>();
        Vertex preVertex = null;
        while(itr.hasNext()){
            Vertex vertex = itr.next();
            if(preVertex != null && preVertex.getBulge() != null && preVertex.getBulge() != 0){
                List<Point> tudoPoints = getPointsOnTuDoArc(vertex, preVertex);
                list.addAll(tudoPoints);
            }
            Point point = vertex.getLocationPoint();
            list.add(point);
            preVertex = vertex;
        }
        if(flag!=null && flag == POLYLINE_FLAG_CLOSED && list.size()>1){
            list.add(list.get(0));
        }
        map = pointsToStr(list);
        return map;
    }

    /**
     * 转换样条曲线
     * @param spline
     * @return
     */
    private Map<String,Object> parseSpline(Spline spline){
        Map<String,Object> map ;
        List<Point> list = new ArrayList<>();
        // 暂定取控制点-1的10倍个数的点
        int total = (spline.getControlsNum() - 1) * 10;
        for(int i=0;i<total;i++){
            double t_val = i * 1.0 / total;
            Point point = interpolate(t_val, spline.getDegree() + 1, spline.getControls(),
                    spline.getKnotValues(), spline.getWeights());
            if(point != null)
                list.add(point);
        }
        map = pointsToStr(list);
        return map;
    }

    /**
     * 转换椭圆
     * @param ellipse
     * @return
     */
    private Map<String,Object> parseEllipse(Ellipse ellipse){
        Map<String,Object> map ;
        List<Point> list = new ArrayList<>();
        int pointCount;
        Point center = ellipse.getCenterPoint();
        Point endPoint = ellipse.getEndPoint();
        Double[] angle = correctAngle(ellipse.getEndPoint(), ellipse.getStartAngle(), ellipse.getEndAngle());
        Double start = angle[0];
        Double end = angle[1];
        pointCount = (int) (end - start) + 1;
        Double a = Math.sqrt(Math.pow(endPoint.getX(),2) + Math.pow(endPoint.getY(), 2));
        Double b = a * ellipse.getRatio();
        double ang = start;
        for(int i = 0;i< pointCount && ang<end;i++){
            // 一度 1点
            ang += 1;
            Point point = getPointOnEllipse(a, b, ang);
            if(point != null)
                point = rotatePointOnEllipse(point, center, endPoint);
            if(point != null)
                list.add(point);
        }

        map = pointsToStr(list);

        return map;
    }

    /**
     * 获取圆 或 圆弧上的点列表
     * @param type 判断 取点数
     * @param center 中心点
     * @param radius 半径
     * @param start 开始度数
     * @param end 结束
     * @return
     */
    private Map<String,Object> parseCircleOrArc(int type, Point center, Double radius, Double start, Double end){
        Map<String,Object> map;
        try{
            // 圆弧开始结束角度 有可能为start:200,end:20
            if(start > end){
                end = end + 360;
            }

            int pointCount;
            if(type == Graphic.GRAPHIC_TYPE_LWPOLYLINE || type == Graphic.GRAPHIC_TYPE_POLYLINE){
                // 多段线调用此处
                pointCount = (int)((end - start) / 45);
                pointCount = pointCount == 0 ? 1: pointCount + 1;
            }else {
                // 据说dwg中单位是 m 米
                pointCount = (int)(2 * Math.PI * radius / CIRCLR_ELLIPSE_ARC_PER_POINT);
            }
            // 不包括起点、结束点
            List<Point> list = getPointsOnCircleOrArc(pointCount, center, radius, start, end);
            if(type == Graphic.GRAPHIC_TYPE_LWPOLYLINE || type == Graphic.GRAPHIC_TYPE_POLYLINE){
                map = new HashMap<>();
                map.put(MAP_KEY_POINTS, list);
            }else {
                // 添加起点\结束点
                Point startPoint = getPointOnCircle(center, radius, start);
                Point endPoint = getPointOnCircle(center, radius, end);
                list.add(0, startPoint);
                list.add(endPoint);
                map = pointsToStr(list);
            }
        }catch (Exception e){
            e.printStackTrace();
            map = new HashMap<>();
            map.put(MAP_KEY_POINTS, new ArrayList<Point>());
            map.put(MAP_KEY_COUNT, new BigInteger( "0"));
            map.put(MAP_KEY_POINT, "");
        }
        return map;
    }

    /**
     * xy 转换为经纬度
     * @param point
     * @return
     */
    private Point transformPointGPS(Point point){
        Point rst = null;
        if(point != null && point.getX() != null && point.getY() != null){
            double[] ll = Transform.XYToLL(point.getX(), point.getY());
            rst = new Point();
            rst.setX(ll[0]);
            rst.setY(ll[1]);
        }
        return rst;
    }

    /**
     *
     * @param point 传入已参数转换为 gps坐标
     * @return
     */
    private Point transformPointGCJ02(Point point){
        Point rst = null;
        if(point != null && point.getX() != null && point.getY() != null){
            double[] ll = Transform.LLToGCJ02(point.getX(), point.getY());
            rst = new Point();
            rst.setX(ll[0]);
            rst.setY(ll[1]);
        }
        return rst;
    }

    /**
     * 获取凸度圆弧上的点
     * @param current 当前点
     * @param pre  上一结点
     * @return
     */
    private List<Point> getPointsOnTuDoArc(Vertex current, Vertex pre){
        List<Point> list;
        Point currentPoint = current.getLocationPoint();
        Point prePoint = pre.getLocationPoint();
        TuDu tudu = new TuDu(pre.getBulge(), prePoint.getX(), prePoint.getY(),
                currentPoint.getX(), currentPoint.getY());
        ARC arc = tudu.getTuDuArc();
        Map<String,Object> map = parseCircleOrArc(Graphic.GRAPHIC_TYPE_POLYLINE, arc.getCenterPoint(),
                arc.getRadius(), arc.getStartAngle(), arc.getEndAngle());
        list = (List<Point>)map.get(MAP_KEY_POINTS);


        list = sortPointListNearBy(prePoint, currentPoint, list);

        return list;
    }

    /**
     * 求出凸度上的点后与原始点比较距离并排序
     * @param from
     * @param target
     * @param data
     * @return
     */
    private List<Point> sortPointListNearBy(Point from, Point target, List<Point> data){
        List<Point> list ;
        if(data == null || data.size() == 0){
            list = new ArrayList<>();
            return list;
        }
        Point start = data.get(0);
        Double toFrom = Math.sqrt(Math.pow(from.getX() - start.getX(), 2) + Math.pow(from.getY() - start.getY(), 2));
        Double toTarget = Math.sqrt(Math.pow(target.getX() - start.getX(), 2) + Math.pow(target.getY() - start.getY(), 2));
        if(toFrom > toTarget){
            list = new ArrayList<>();
            for(int i=data.size() - 1;i>=0;i--){
                Point point = data.get(i);
                list.add(point);
            }
        }else{
            list = data;
        }
        return list;
    }

    /**
     * 获取圆上点列表
     * @param count
     * @param center
     * @param radius
     * @param start
     * @param end
     * @return
     */
    public List<Point> getPointsOnCircleOrArc(int count, Point center, Double radius, Double start, Double end){
        List<Point> list = new ArrayList<>();
        Double dlt = (end - start) / (count + 1) * 1.0;
        Double angle = start;
        for(int i=0;i<count && angle<end;i++){
            angle = angle + dlt;
            Point point = getPointOnCircle(center, radius, angle);
            list.add(point);
        }
        return list;
    }

    /**
     * 获取标准椭圆上的点
     * @param a
     * @param b
     * @param angle
     * @return
     */
    public Point getPointOnEllipse(Double a, Double b, Double angle){
        Point rst = new Point();
        try{
            double angpi = angle / 360 * 2 * Math.PI;
            double amb = a * b;
            double angTanPow = Math.pow(Math.tan(angpi), 2);
            double aPow = Math.pow(a, 2);
            double bPow = Math.pow(b, 2);
            double sqrt = Math.sqrt(angTanPow * aPow + bPow);

            double x = amb / sqrt;
            double y = Math.tan(angpi) * x;
            int flag = 1; // 点所属象限
            double lsang = angle % 360;
            if(lsang / 90 >= 0){flag = 1; }
            if(lsang / 90 >= 1){flag = 2; }
            if(lsang / 90 >= 2){flag = 3; }
            if(lsang / 90 >= 3){flag = 4; }
            double absx = Math.abs(x);
            double absy = Math.abs(y);
            switch (flag){
                case 1: x = absx; y = absy;
                    break;
                case 2: x = 0 - absx; y = absy;
                    break;
                case 3: x = 0 - absx; y = 0 - absy;
                    break;
                case 4: x = absx; y = 0 - absy;
                    break;
            }
            rst.setX(x);
            rst.setY(y);
        }catch (Exception e){
            rst = null;
            e.printStackTrace();
        }
        return rst;
    }

    /**
     * 标准椭圆方程的点 转为 实际椭圆方程上的点
     * @param point
     * @param center
     * @param end
     * @return
     */
    public Point rotatePointOnEllipse(Point point, Point center, Point end){
        Point rst = new Point();
        try{
            double rangle = Math.atan(end.getY()/end.getX());
            rangle = rangle % (2 * Math.PI);
            if(rangle < 0){
                rangle = rangle + 2 * Math.PI;
            }
            double x = point.getX() * Math.cos(rangle) - point.getY() * Math.sin(rangle) + center.getX();
            double y = point.getX() * Math.sin(rangle) + point.getY() * Math.cos(rangle) + center.getY();
            rst.setX(x);
            rst.setY(y);
        }catch (Exception e){
            rst = null;
            e.printStackTrace();
        }
        return rst;
    }

    /**
     * 获取圆上单点
     * @param center
     * @param radius
     * @param angle
     * @return
     */
    public Point getPointOnCircle(Point center, Double radius, Double angle){
        Point rst = new Point();
        Double x = center.getX() + radius * Math.cos(angle * Math.PI / 180);
        Double y = center.getY() + radius * Math.sin(angle * Math.PI / 180);
        rst.setX(x);
        rst.setY(y);
        return rst;
    }

    /**
     * 椭圆弧度修正
     * @param start
     * @param end
     * @return
     */
    private Double[] correctAngle(Point point, Double start, Double end){
        Double[] rst = new Double[]{0d,0d};
        // 0-2 to 0-360
        start = start / Math.PI * 180;
        end = end / Math.PI * 180;
        if(point.getX() < 0){
            start = start - 180;
            end = end - 180;
        }
        //修正到±2π
        start = start % 360;
        end = end % 360;
        if(start > end ){
            start = start + end;
            end = start - end;
            start = start - end;
        }
        if(start < 0 || end < 0){
            start = start + 360;
            end = end + 360;
        }
        rst[0] = start;
        rst[1] = end;
        return rst;
    }

    /**
     * 获取样条曲线上的点
     * @param t 向量
     * @param order 等级
     * @param controls 控制点
     * @param knotsValue 节点
     * @param weights 权重
     * @return
     */
    private Point interpolate(double t, int order, List<Point> controls, List<Double> knotsValue, List<Double> weights){
        Point point = new Point();
        try{
            int i,j,s,l;
            int d = 2; // 二维
            int n = controls.size();
            if(weights.size() == 0){
                weights = new ArrayList<>(n);
                for( int fi=0;fi<n;fi++){
                    weights.add(1d);
                }
            }
            if(knotsValue.size() == 0){
                knotsValue = new ArrayList<>(n + order);
                for(int fi=0;fi<n + order; fi++){
                    knotsValue.add(new Double(fi));
                }
            }else {
                if(knotsValue.size() != n + order){
                    throw new Exception("节点数组长度不正确");
                }
            }
            int[] domain = new int[]{order - 1, knotsValue.size() - 1 - (order - 1)};
            double low = knotsValue.get(domain[0]);
            double hight = knotsValue.get(domain[1]);
            t = t * (hight - low) + low;
            if(t < low || t > hight){
                throw new Exception("向量超出范围");
            }
            for(s = domain[0]; s<domain[1]; s++){
                double ks = knotsValue.get(s);
                double ks_1 = knotsValue.get(s + 1);
                if(t >= ks && t <= ks_1){
                    break;
                }
            }
            double[][] v = new double[n][];
            for(i=0;i<n;i++){
                double[] tmp = new double[d + 1];
                Point ptmp = controls.get(i);
                double weight = weights.get(i);
                tmp[0] = ptmp.getX() * weight;
                tmp[1] = ptmp.getY() * weight;
                tmp[2] = weight;
                v[i] = tmp;
            }

            double alpha;
            for(l=1;l<=order;l++){
                for(i=s;i>s-order+1;i--){
                    alpha = (t-knotsValue.get(i)) / knotsValue.get(i + order - 1) - knotsValue.get(i);

                    for (j=0;j<d+1;j++){
                        v[i][j] = (1-alpha) * v[i-1][j] + alpha * v[i][j];
                    }
                }
            }

            double[] rst = new double[d];
            for(i=0;i<d;i++){
                rst[i] = v[s][i] / v[s][d];
            }

            point.setX(rst[0]);
            point.setY(rst[1]);

        }catch (Exception e){
            point = null;
            e.printStackTrace();
        }

        return point;
    }

    /**
     * 点列表 转 字符串
     * @param list
     * @return
     */
    private Map<String,Object> pointsToStr(List<Point> list){
        Map<String,Object> map = new HashMap<>();
        try{

            StringBuffer gpsListStr = new StringBuffer();
            Iterator<Point> itr = list.iterator();
            while (itr.hasNext()){
                Point point = itr.next();
                Point gpsPoint = transformPointGPS(point);
                gpsListStr.append(String.format(LLSCALEFORMAT, gpsPoint.getX()));
                gpsListStr.append(",");
                gpsListStr.append(String.format(LLSCALEFORMAT, gpsPoint.getY()));
                if(itr.hasNext()) {
                    gpsListStr.append(";");
                }
            }
            map.put(MAP_KEY_COUNT, new BigInteger(list.size() + ""));
            map.put(MAP_KEY_POINT, gpsListStr.toString());
        }catch (Exception e){
            map.put(MAP_KEY_COUNT, new BigInteger("0"));
            map.put(MAP_KEY_POINT, "");
        }
        return map;
    }

    /**
     * 根据填充图形hatch获取填充颜色
     * @param handlerId
     * @return
     */
    private Color getFillColor(String handlerId){
        Color color = new Color();
        Iterator<Hatch> iterator = hatches.iterator();
        while(iterator.hasNext()){
            Hatch hatch = iterator.next();
            if(StringUtils.isNotBlank(handlerId) &&  handlerId.equalsIgnoreCase(hatch.getHandleId())){
                color = hatch.getColor();
                break;
            }
        }

        return color;
    }
}
