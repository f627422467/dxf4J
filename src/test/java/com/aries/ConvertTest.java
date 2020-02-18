package com.aries;

import com.aries.dxfConvert.DXFData;
import com.aries.dxfConvert.GraphicConverter;
import com.aries.dxfConvert.Parser;
import com.aries.dxfConvert.entities.*;
import com.aries.dxfConvert.sql.Graphic;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Aries
 */
public class ConvertTest {
    public static void main(String[] args){

        String file = "C:\\Users\\Aries\\Desktop\\工作\\20200118 打孔对接\\DXF标准示例 （含自定义属性）.dxf";
        Parser parser =new Parser();
        GraphicConverter converter = new GraphicConverter();
        DXFData data = null;
        List<Graphic> list = null;
        try{
            //读取dxf文件，转化成对象
            data = parser.dxfParse(new FileInputStream(file));
            //对基础数据进行二次处理，方便显示
            converter(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(data);
    }



    public static void converter(DXFData data){
        //处理外扩图，只针对图层名中含有 xxx_WaiKuo 的图形
        List<OutLineMap> outLineMaps = convertLwPolyLine(data.getLwPolyLines());
        //按layer聚合
        Map<String,OutLineMap> layerName_OutLineMap = new HashMap<>();
        for(OutLineMap outLineMap : outLineMaps){
            layerName_OutLineMap.put(outLineMap.getLayerName(),outLineMap);
        }
        //处理所有孔
        List<Hole> holes = convertCircle(data.getCircles(),layerName_OutLineMap);
    }


    public static List<OutLineMap> convertLwPolyLine(List<LwPolyLine> lwPolyLines){
        List<OutLineMap> outLineMaps = Lists.newArrayList();
        Iterator<LwPolyLine> iterator = lwPolyLines.iterator();
        while(iterator.hasNext()){
            LwPolyLine lwPolyLine = iterator.next();
            if(!lwPolyLine.getLayerName().toLowerCase().contains("waikuo")){
                continue;
            }
            OutLineMap outLineMap = new OutLineMap();
            outLineMap.setLayerName(lwPolyLine.getLayerName());
            List<Point> points = new ArrayList<>();
            Point minPoint = null;
            for(Vertex vertex : lwPolyLine.getVertexCoordinates()){
                Point tmpPoint = vertex.getLocationPoint();
                points.add(tmpPoint);
                if(minPoint == null ||
                        (isLess(tmpPoint.getX(),minPoint.getX()) ||
                                isLess(tmpPoint.getY(), minPoint.getY()) ||
                                isLess(tmpPoint.getZ(),minPoint.getZ()))){
                    minPoint = tmpPoint;
                }
            }
            outLineMap.setPoints(points);

            List<Point> oppositePoints = new ArrayList<>();
            for(Point point : points){
                Point tmpPoint = new Point();
                tmpPoint.setX(subtraction(point.getX(),minPoint.getX()));
                tmpPoint.setY(subtraction(point.getY(),minPoint.getY()));
                tmpPoint.setZ(subtraction(point.getZ(),minPoint.getZ()));
                oppositePoints.add(tmpPoint);
            }
            outLineMap.setOppositePoints(oppositePoints);
            outLineMaps.add(outLineMap);
        }
        return outLineMaps;
    }

    public static boolean isLess(Double one,Double two){
        if (one==null || two == null)
            return false;
        return one < two;
    }

    public static Double subtraction(Double one,Double two){
        if (one==null || two == null)
            return 0.0;
        return one - two;
    }

    public static List<Hole> convertCircle(List<Circle> circles,Map<String,OutLineMap> layerName_OutLineMap){
        List<Hole> holes = new ArrayList<>();
        Iterator<Circle> iterator = circles.iterator();
        while (iterator.hasNext()){
            Circle circle = iterator.next();
            if(!circle.getLayerName().toLowerCase().contains("kong")){
                continue;
            }
            Hole hole = new Hole();
            hole.setLayerName(circle.getLayerName());
            hole.setCenterPoint(circle.getCenterPoint());
            hole.setRadius(circle.getRadius());
            //判断是否贯通
            String[] names = hole.getLayerName().split("_");
            String deepStr = names[2].replaceAll("D","");
            if(deepStr.contains("T")){
                hole.setIsThrough(true);
            }else{
                Double deep = Double.parseDouble(deepStr);
                hole.setIsThrough(false);
                hole.setDeep(deep);
            }

            String prefix = names[0].trim();
            String outLineMapLayerName = prefix + "_WaiKuo";
            if(layerName_OutLineMap.containsKey(outLineMapLayerName)){
                OutLineMap outLineMap = layerName_OutLineMap.get(outLineMapLayerName);
                Point miniPoint = outLineMap.getMiniPoint();
                Point tmpPoint = new Point();
                tmpPoint.setX(subtraction(hole.getCenterPoint().getX(),miniPoint.getX()));
                tmpPoint.setY(subtraction(hole.getCenterPoint().getY(),miniPoint.getY()));
                tmpPoint.setZ(subtraction(hole.getCenterPoint().getZ(),miniPoint.getZ()));
                hole.setOppositePoint(tmpPoint);
            }
        }
        return holes;
    }

}
