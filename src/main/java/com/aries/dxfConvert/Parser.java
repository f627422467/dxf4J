package com.aries.dxfConvert;

import com.aries.dxfConvert.entities.*;
import com.aries.dxfConvert.header.Layer;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aries
 */
public class Parser {

    public static final String DXF_SECTION_STR = "SECTION";
    public static final String DXF_HEADER_STR = "HEADER";
    public static final String DXF_TABLES_STR = "TABLES";
    public static final String DXF_ENTITIES_STR = "ENTITIES";

    public static final String DXF_EOF = "EOF";
    public static final String DXF_ENDSEC = "ENDSEC";
    public static final String DXF_ENDTAB = "ENDTAB";
    public static final String DXF_CODE102_END = "}";
    public static final String DXF_SEQEND = "SEQEND";
    public static final String DXF_EXTMIN = "$EXTMIN";
    public static final String DXF_EXTMAX = "$EXTMAX";

    // 二维多段线的点
    public static final int DXF_POLYLINE_VERTEX = 1;

    /**
     * 读取DXF文件
     * @param inputStream
     * @return
     */
    public DXFData dxfParse(InputStream inputStream){
        DXFData data = new DXFData();
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String[] code = readGroupCode(bufferedReader);
            while(code[0] != null && code[1] != null){
                if(DXF_SECTION_STR.equalsIgnoreCase(code[1] )){
                    code = readGroupCode(bufferedReader);
                    switch (code[1]){
                        case DXF_HEADER_STR:
                            code = parseHeader(bufferedReader, data);
                            break;
                        case DXF_ENTITIES_STR:
                            code = parseEntities(bufferedReader, data);
                            break;
                        case DXF_TABLES_STR:
                            code = parseTables(bufferedReader, data);
                            break;
                    }
                }else {
                    code = readGroupCode(bufferedReader);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            }catch (IOException e){
                // log.error("也许已经被关闭了")
                e.printStackTrace();
            }
        }

        return data;
    }

    /* 读取entities--start*/

    /**
     * 读取图形数据
     * @param bufferedReader
     * @param data
     */
    private String[] parseEntities(BufferedReader bufferedReader, DXFData data){
        String[] code = readGroupCode(bufferedReader);
        // dxf 以 EOF 结尾
        while(code[1] != null && !DXF_EOF.equalsIgnoreCase(code[1])){
            switch (code[1]){
                case "TEXT":
                    code = parseText(bufferedReader, data.getTexts());
                    break;
                case "MTEXT":
                    code = parseMText(bufferedReader, data.getmTexts());
                    break;
                case "LINE":
                    code = parseLine(bufferedReader, data.getLines());
                    break;
                case "LWPOLYLINE":
                    code = parseLwPolyLine(bufferedReader, data.getLwPolyLines());
                    break;
                case "AcDbPolyline":
                    code = parseAcDbPolyLine(bufferedReader, data.getAcDbPolyLines());
                    break;
                case "POLYLINE":
                    code = parsePolyLine(bufferedReader, data.getPolyLines());
                    break;
                case "ARC":
                    code = parseArc(bufferedReader, data.getArcs());
                    break;
                case "CIRCLE":
                    code = parseCircle(bufferedReader, data.getCircles());
                    break;
                case "ELLIPSE":
                    code = parseEllipse(bufferedReader, data.getEllipses());
                    break;
                case "SPLINE":
                    code = parseSpline(bufferedReader, data.getSplines());
                    break;
                case "HATCH":
                    code = parseHatch(bufferedReader, data.getHatches());
                    break;
                default:
                    code = readGroupCode(bufferedReader);
                    break;
            }
        }
        return code;
    }

    /**
     * 读取文字
     * @param bufferedReader
     * @param list
     */
    private String[] parseText(BufferedReader bufferedReader, List<Text> list){
        String[] code = readGroupCode(bufferedReader);
        Text text = new Text();
        Point point = new Point();
        Color color = new Color();
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])) {
            switch (code[0]) {
                case "8":
                    text.setLayerName(code[1]);
                    break;
                case "10":
                    point.setX(Double.parseDouble(code[1]));
                    break;
                case "20":
                    point.setY(Double.parseDouble(code[1]));
                    break;
                case "40":
                    text.setHeight(Double.parseDouble(code[1]));
                    break;
                case "50":
                    text.setRotation(Double.parseDouble(code[1]));
                case "62":
                    color.setColor(Integer.parseInt(code[1])); 
                    break;
                case "1":
                    text.setLabel(code[1]);
                    break;
                case "0":
                    text.setPoint(point);
                    text.setColor(color);
                    list.add(text);
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        if(DXF_ENDSEC.equalsIgnoreCase(code[1]) && point.getX()!=null){
            text.setPoint(point);
            text.setColor(color);
            list.add(text);
        }
        return code;
    }

    /**
     * 读取多行文字
     * @param bufferedReader
     * @param list
     */
    private String[] parseMText(BufferedReader bufferedReader, List<MText> list){
        String[] code = readGroupCode(bufferedReader);
        MText mText = new MText();
        Point point = new Point();
        Color color = new Color();
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])) {
            switch (code[0]) {
                case "8":
                    mText.setLayerName(code[1]);
                    break;
                case "10":
                    point.setX(Double.parseDouble(code[1]));
                    break;
                case "20":
                    point.setY(Double.parseDouble(code[1]));
                    break;
                case "40":
                    mText.setHeight(Double.parseDouble(code[1]));
                    break;
                case "50":
                    mText.setRotation(Double.parseDouble(code[1]));
                case "62":
                    color.setColor(Integer.parseInt(code[1])); 
                    break;
                case "90":
                    mText.setBgfSetting(Integer.parseInt(code[1]));
                    break;
                case "1":
                    mText.setLabel(code[1]);
                    break;
                case "0":
                    mText.setPoint(point);
                    mText.setColor(color);
                    list.add(mText);
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        if(DXF_ENDSEC.equalsIgnoreCase(code[1]) && point.getX()!=null){
            mText.setPoint(point);
            mText.setColor(color);
            list.add(mText);
        }
        return code;
    }

    /**
     * 读取线段
     * @param bufferedReader
     * @param list
     */
    private String[] parseLine(BufferedReader bufferedReader, List<Line> list){
        String[] code = readGroupCode(bufferedReader);
        Line line = new Line();
        Point startPoint = new Point();
        Point endPoint = new Point();
        Color color = new Color();
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])){
            switch (code[0]){
                case "8":
                    line.setLayerName(code[1]);
                    break;
                case "10":
                    startPoint.setX(Double.parseDouble(code[1]));
                    break;
                case "20":
                    startPoint.setY(Double.parseDouble(code[1]));
                    break;
                case "11":
                    endPoint.setX(Double.parseDouble(code[1]));
                    break;
                case "21":
                    endPoint.setY(Double.parseDouble(code[1]));
                    // 读取到结束端点则返回
                    line.setStartPoint(startPoint);
                    line.setEndPoint(endPoint);
                    list.add(line);
                    return code;
                case "62":
                    color.setColor(Integer.parseInt(code[1])); 
                    line.setColor(color);
                    break;
                case "370":
                    line.setLineWidth(Double.parseDouble(code[1]));
                    break;
                case "0":
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        return code;
    }

    /**
     * 读取圆弧
     * @param bufferedReader
     * @param list
     */
    private String[] parseArc(BufferedReader bufferedReader, List<ARC> list){
        String[] code = readGroupCode(bufferedReader);
        ARC arc = new ARC();
        Point centerPoint = new Point();
        Color color = new Color();
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])) {
            switch (code[0]) {
                case "8":
                    arc.setLayerName(code[1]);
                    break;
                case "10":
                    centerPoint.setX(Double.parseDouble(code[1]));
                    break;
                case "20":
                    centerPoint.setY(Double.parseDouble(code[1]));
                    break;
                case "40":
                    arc.setRadius(Double.parseDouble(code[1]));
                    break;
                case "50":
                    arc.setStartAngle(Double.parseDouble(code[1]));
                    break;
                case "51":
                    arc.setEndAngle(Double.parseDouble(code[1]));
                    break;
                case "62":
                    color.setColor(Integer.parseInt(code[1])); 
                    break;
                case "0":
                    arc.setCenterPoint(centerPoint);
                    arc.setColor(color);
                    list.add(arc);
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        if(DXF_ENDSEC.equalsIgnoreCase(code[1]) && centerPoint.getX()!=null){
            arc.setCenterPoint(centerPoint);
            arc.setColor(color);
            list.add(arc);
        }
        return code;
    }

    /**
     * 读取圆
     * @param bufferedReader
     * @param list
     */
    private String[] parseCircle(BufferedReader bufferedReader, List<Circle> list){
        String[] code = readGroupCode(bufferedReader);
        Circle circle = new Circle();
        Color color = new Color();
        Point centerPoint = new Point();
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])) {
            switch (code[0]) {
                case "8":
                    circle.setLayerName(code[1]);
                    break;
                case "10":
                    centerPoint.setX(Double.parseDouble(code[1]));
                    break;
                case "20":
                    centerPoint.setY(Double.parseDouble(code[1]));
                    break;
                case "40":
                    circle.setRadius(Double.parseDouble(code[1]));
                    break;
                case "62":
                    color.setColor(Integer.parseInt(code[1])); 
                    break;
                case "0":
                    circle.setCenterPoint(centerPoint);
                    circle.setColor(color);
                    list.add(circle);
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        if(DXF_ENDSEC.equalsIgnoreCase(code[1]) && centerPoint.getX()!=null){
            circle.setCenterPoint(centerPoint);
            circle.setColor(color);
            list.add(circle);
        }
        return code;
    }

    /**
     * 矩形
     * @param bufferedReader
     * @param list
     * @return
     */
    private String[] parseAcDbPolyLine(BufferedReader bufferedReader, List<AcDbPolyLine> list){
        String[] code = readGroupCode(bufferedReader);
        AcDbPolyLine acDbPolyLine = new AcDbPolyLine();
        Color color = new Color();
        Vertex vertex = null;
        List<Vertex> vertices = new ArrayList<>();
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])){
            switch (code[0]){
                case "8":
                    acDbPolyLine.setLayerName(code[1]);
                    break;
                case "10":
                    if(vertex != null){
                        vertices.add(vertex);
                    }
                    vertex = new Vertex();
                    vertex.setLocationPoint(new Point(Double.parseDouble(code[1]), 0d));
                    break;
                case "20":
                    vertex.getLocationPoint().setY(Double.parseDouble(code[1]));
                    break;
                case "42":
                    // 凸度
                    vertex.setBulge(Double.parseDouble(code[1]));
                    break;
                case "62":
                    color.setColor(Integer.parseInt(code[1]));
                    acDbPolyLine.setColor(color);
                    break;
                case "70":
                    acDbPolyLine.setPolylineFlag(Integer.parseInt(code[1]));
                    break;
                case "90":
                    acDbPolyLine.setVerticesNum(Integer.parseInt(code[1]));
                    break;
                case "102":
                    // handleId
                    Code102 code102 = parseCode102(bufferedReader, code);
                    acDbPolyLine.setHandleId(code102.getSpID());
                    break;
                case "0":
                    if(vertex != null){
                        vertices.add(vertex);
                    }
                    acDbPolyLine.setVertexCoordinates(vertices);
                    list.add(acDbPolyLine);
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        if(DXF_ENDSEC.equalsIgnoreCase(code[1]) && vertex!=null){
            vertices.add(vertex);
            acDbPolyLine.setVertexCoordinates(vertices);
            list.add(acDbPolyLine);
        }
        return code;
    }

    /**
     * 读取多段线
     * @param bufferedReader
     * @param list
     */
    private String[] parseLwPolyLine(BufferedReader bufferedReader, List<LwPolyLine> list){
        String[] code = readGroupCode(bufferedReader);
        LwPolyLine lwPolyLine = new LwPolyLine();
        Color color = new Color();
        Vertex vertex = null;
        List<Vertex> vertices = new ArrayList<>();
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])){
            switch (code[0]){
                case "8":
                    lwPolyLine.setLayerName(code[1]);
                    break;
                case "10":
                    if(vertex != null){
                        vertices.add(vertex);
                    }
                    vertex = new Vertex();
                    vertex.setLocationPoint(new Point(Double.parseDouble(code[1]), 0d));
                    break;
                case "20":
                    vertex.getLocationPoint().setY(Double.parseDouble(code[1]));
                    break;
                case "42":
                    // 凸度
                    vertex.setBulge(Double.parseDouble(code[1]));
                    break;
                case "62":
                    color.setColor(Integer.parseInt(code[1])); 
                    lwPolyLine.setColor(color);
                    break;
                case "70":
                    lwPolyLine.setPolylineFlag(Integer.parseInt(code[1]));
                    break;
                case "90":
                    lwPolyLine.setVerticesNum(Integer.parseInt(code[1]));
                    break;
                case "102":
                    // handleId
                    Code102 code102 = parseCode102(bufferedReader, code);
                    lwPolyLine.setHandleId(code102.getSpID());
                    break;
                case "0":
                    if(vertex != null){
                        vertices.add(vertex);
                    }
                    lwPolyLine.setVertexCoordinates(vertices);
                    list.add(lwPolyLine);
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        if(DXF_ENDSEC.equalsIgnoreCase(code[1]) && vertex!=null){
            vertices.add(vertex);
            lwPolyLine.setVertexCoordinates(vertices);
            list.add(lwPolyLine);
        }
        return code;
    }

    /**
     * 读取二维多段线
     * @param bufferedReader
     * @param list
     */
    private String[] parsePolyLine(BufferedReader bufferedReader, List<PolyLine> list){
        String[] code = readGroupCode(bufferedReader);
        PolyLine polyLine = new PolyLine();
        List<Vertex> vertexs = new ArrayList<>();
        Color color = new Color();
        boolean codeFlag = false;
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])){
            switch (code[0]){
                case "8":
                    polyLine.setLayerName(code[1]);
                    break;
                case "62":
                    color.setColor(Integer.parseInt(code[1])); 
                    polyLine.setColor(color);
                    break;
                case "70":
                    polyLine.setPolylineFlag(Integer.parseInt(code[1]));
                    break;
                case "0":
                    if(code[1] != null && "VERTEX".equalsIgnoreCase(code[1])){
                        while(code[1] != null && !DXF_SEQEND.equalsIgnoreCase(code[1])){
                            switch (code[1]){
                                case "VERTEX":
                                    code = parseVertex(bufferedReader, vertexs, DXF_POLYLINE_VERTEX);
                                    break;
                                default:
                                    code = readGroupCode(bufferedReader);
                                    break;
                            }
                            // 如 0: ellipse 则跳出while
                            if("0".equalsIgnoreCase(code[0]) && !"VERTEX".equalsIgnoreCase(code[1])){
                                codeFlag = true;
                                break;
                            }
                        }
                    }else {
                        polyLine.setVertexCoordinates(vertexs);
                        list.add(polyLine);
                        return code;
                    }
                    break;
            }
            if(!codeFlag){
                code = readGroupCode(bufferedReader);
            }else {
                codeFlag = false;
            }
        }
        if(DXF_ENDSEC.equalsIgnoreCase(code[1]) && vertexs.size()>0){
            polyLine.setVertexCoordinates(vertexs);
            list.add(polyLine);
        }
        return code;
    }

    /**
     * 读取椭圆
     * @param bufferedReader
     * @param list
     */
    private String[] parseEllipse(BufferedReader bufferedReader, List<Ellipse> list){
        String[] code = readGroupCode(bufferedReader);
        Ellipse ellipse = new Ellipse();
        Point centerPoint = new Point();
        Point endPoint = new Point();
        Color color = new Color();
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])) {
            switch (code[0]) {
                case "8":
                    ellipse.setLayerName(code[1]);
                    break;
                case "10":
                    centerPoint.setX(Double.parseDouble(code[1]));
                    break;
                case "20":
                    centerPoint.setY(Double.parseDouble(code[1]));
                    break;
                case "11":
                    endPoint.setX(Double.parseDouble(code[1]));
                    break;
                case "21":
                    endPoint.setY(Double.parseDouble(code[1]));
                    break;
                case "40":
                    ellipse.setRatio(Double.parseDouble(code[1]));
                    break;
                case "41":
                    ellipse.setStartAngle(Double.parseDouble(code[1]));
                    break;
                case "42":
                    ellipse.setEndAngle(Double.parseDouble(code[1]));
                    break;
                case "62":
                    color.setColor(Integer.parseInt(code[1])); 
                    break;
                case "102":
                    // handleId
                    Code102 code102 = parseCode102(bufferedReader, code);
                    ellipse.setHandleId(code102.getSpID());
                    break;
                case "0":
                    ellipse.setCenterPoint(centerPoint);
                    ellipse.setEndPoint(endPoint);
                    ellipse.setColor(color);
                    list.add(ellipse);
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        if(DXF_ENDSEC.equalsIgnoreCase(code[1]) && centerPoint.getX()!=null){
            ellipse.setCenterPoint(centerPoint);
            ellipse.setEndPoint(centerPoint);
            ellipse.setColor(color);
            list.add(ellipse);
        }
        return code;
    }

    /**
     * 读取样条曲线
     * @param bufferedReader
     * @param list
     */
    private String[] parseSpline(BufferedReader bufferedReader, List<Spline> list){
        String[] code = readGroupCode(bufferedReader);
        Spline spline = new Spline();
        Color color = new Color();
        Point contro = null;
        Point fit = null;
        List<Point> controls = new ArrayList<>();
        List<Point> fits = new ArrayList<>();
        List<Double> knotValues = new ArrayList<>();
        List<Double> weights = new ArrayList<>();
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])) {
            switch (code[0]) {
                case "8":
                    spline.setLayerName(code[1]);
                    break;
                case "10":
                    if(contro != null ){
                        controls.add(contro);
                    }
                    contro = new Point();
                    contro.setX(Double.parseDouble(code[1]));
                    break;
                case "20":
                    contro.setY(Double.parseDouble(code[1]));
                    break;
                case "11":
                    if(fit != null){
                        fits.add(fit);
                    }
                    fit = new Point();
                    fit.setX(Double.parseDouble(code[1]));
                    break;
                case "21":
                    fit.setY(Double.parseDouble(code[1]));
                    break;
                case "40":
                    knotValues.add(Double.parseDouble(code[1]));
                    break;
                case "41":
                    weights.add(Double.parseDouble(code[1]));
                    break;
                case "42":
                    spline.setKnotTolerance(Double.parseDouble(code[1]));
                    break;
                case "43":
                    spline.setControlTolerance(Double.parseDouble(code[1]));
                    break;
                case "44":
                    spline.setFitTolerance(Double.parseDouble(code[1]));
                    break;
                case "62":
                    color.setColor(Integer.parseInt(code[1])); 
                    break;
                case "70":
                    spline.setSplineFlag(Integer.parseInt(code[1]));
                    break;
                case "71":
                    spline.setDegree(Integer.parseInt(code[1]));
                    break;
                case "72":
                    spline.setKnotsNum(Integer.parseInt(code[1]));
                    break;
                case "73":
                    spline.setControlsNum(Integer.parseInt(code[1]));
                    break;
                case "74":
                    spline.setFitsNum(Integer.parseInt(code[1]));
                    break;
                case "0":
                    if(contro != null ){
                        controls.add(contro);
                    }
                    if(fit != null){
                        fits.add(fit);
                    }
                    spline.setControls(controls);
                    spline.setFits(fits);
                    spline.setKnotValues(knotValues);
                    spline.setWeights(weights);
                    spline.setColor(color);
                    list.add(spline);
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        // 向量肯定有值
        if(DXF_ENDSEC.equalsIgnoreCase(code[1]) && knotValues.size()>0){
            spline.setControls(controls);
            spline.setFits(fits);
            spline.setKnotValues(knotValues);
            spline.setWeights(weights);
            spline.setColor(color);
            list.add(spline);
        }
        return code;
    }

    /**
     * 读取图形填充
     * @param bufferedReader
     * @param list
     */
    private String[] parseHatch(BufferedReader bufferedReader, List<Hatch> list){
        String[] code = readGroupCode(bufferedReader);
        boolean codeFlag = false;
        Hatch hatch = new Hatch();
        Color color = new Color();
        Point point = new Point();
        List<HatchEdge> hatchEdges = new ArrayList<>();
        Integer bpTypeFlag = 0; // Boundary path type flag (bit coded): 2- polyline
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])) {
            switch (code[0]) {
                case "8":
                    hatch.setLayerName(code[1]);
                    break;
                case "10":
                    point.setX(Double.parseDouble(code[1]));
                    break;
                case "20":
                    point.setY(Double.parseDouble(code[1]));
                    break;
                case "30":
                    point.setZ(Double.parseDouble(code[1]));
                    break;
                case "62":
                    color.setColor(Integer.parseInt(code[1])); 
                    break;
                case "72":
                    // 边界图形
                    // code = parseHatchEdge(bufferedReader, hatchEdges, code, bpTypeFlag);
                    bpTypeFlag = 0;
                    break;
                case "92":
                    bpTypeFlag = Integer.parseInt(code[1]);
                    break;
                case "5":
                    hatch.setHandleId(code[1]);
                    break;
                case "0":
                    hatch.setHatchEdges(hatchEdges);
                    hatch.setColor(color);
                    list.add(hatch);
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        if(DXF_ENDSEC.equalsIgnoreCase(code[1]) && StringUtils.isNotBlank(hatch.getHandleId())){
            hatch.setHatchEdges(hatchEdges);
            hatch.setColor(color);
            list.add(hatch);
        }
        return code;
    }

    /**
     * 读取填充图形边界信息 --待完善
     * @param bufferedReader
     * @param list
     * @param preCode
     * @param bpTypeFlag
     * @return
     */
    private String[] parseHatchEdge(BufferedReader bufferedReader, List<HatchEdge> list, String[] preCode, Integer bpTypeFlag){
        String[] code = preCode;
        HatchEdge hatchEdge = new HatchEdge();
        List<PolyLine> polyLines = new ArrayList<>();
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])) {
            switch (code[0]) {
                case "72":
                    if(bpTypeFlag != null && bpTypeFlag == 2){
                        parseHatchEdgePolyLine(bufferedReader, polyLines);
                    }else{
                        switch (code[1]){
                            case "1":
                                break;
                            case "2":
                                break;
                            case "3":
                                break;
                            case "4":
                                break;
                        }
                    }
                    break;
                case "92":
                    bpTypeFlag = Integer.parseInt(code[1]);
                    break;
                case "330":
                    hatchEdge.setSoftPointerId(code[1]);
                    break;
                case "0":
                    return code;
            }
            if("330".equalsIgnoreCase(code[0])){
                break;
            }
        }
        return code;
    }

    private String[] parseHatchEdgePolyLine(BufferedReader bufferedReader, List<PolyLine> list){
        String[] code = readGroupCode(bufferedReader);
        PolyLine polyLine = new PolyLine();

        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])) {
            switch (code[0]) {
                case "10":
                    break;
                case "20":
                    break;
                case "30":
                    break;
                case "42":
                    break;
                case "72":
                    break;
                case "73":
                    break;
                case "93":
                    break;
                case "330":

                    break;
            }

        }
        return code;
    }

    /**
     * 读取点数据 vertex
     * @param bufferedReader
     * @param list 保存的vertex列表
     * @param filter 过滤值：
     *               DXF_POLYLINE_VERTEX： 二维多段线过滤
     * @return
     */
    private String[] parseVertex(BufferedReader bufferedReader, List<Vertex> list, int filter){
        Vertex vertex = new Vertex();
        Point point = null;
        String[] code = readGroupCode(bufferedReader);
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])){
            switch (code[0]){
                case "10":
                    point = new Point();
                    point.setX(Double.parseDouble(code[1]));
                    break;
                case "20":
                    point.setY(Double.parseDouble(code[1]));
                    break;
                case "42":
                    vertex.setBulge(Double.parseDouble(code[1]));
                    break;
                case "70":
                    vertex.setVertexFlag(Integer.parseInt(code[1]));
                    break;
                case "50":
                    vertex.setCurveFitDirect(Double.parseDouble(code[1]));
                    break;
                case "0":
                    if(point != null ){
                        vertex.setLocationPoint(point);
                        switch (filter){
                            case DXF_POLYLINE_VERTEX:
                                // null-未声明， 1-拟合添加的额外点， 2-曲线拟合的点， 8-样条拟合的点
                                // 排除： 4-not used， 16-样条控制点， 32/64-3D，  128-面
                                Integer vflag = vertex.getVertexFlag();
                                if(vflag == null || vflag == 1 || vflag == 2 || vflag == 8){
                                    list.add(vertex);
                                }
                                break;
                            default:
                                list.add(vertex);
                                break;
                        }
                    }
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        if(DXF_ENDSEC.equalsIgnoreCase(code[1]) && point != null){
            vertex.setLocationPoint(point);
            if(DXF_POLYLINE_VERTEX == filter){
                Integer vflag = vertex.getVertexFlag();
                if(vflag == null || vflag == 1 || vflag == 2 || vflag == 8){
                    list.add(vertex);
                }
            }else {
                list.add(vertex);
            }
        }
        return code;
    }

    /**
     * 读取软指针
     * @param bufferedReader
     * @param preCode 当前已读到的组码
     * @return
     */
    private Code102 parseCode102(BufferedReader bufferedReader, String[] preCode){
        Code102 adc102 = new Code102();
        adc102.setGroupName(preCode[1]);
        String[] code = readGroupCode(bufferedReader);
        while(code[1] != null && !DXF_CODE102_END.equalsIgnoreCase(code[1])){
            switch (code[0]){
                case "330":
                    adc102.setSpID(code[1]);
                    break;
            }
            code = readGroupCode(bufferedReader);
        }
        return adc102;
    }

    /* 读取entities--end*/


    /* 读取headers --start*/
    private String[] parseHeader(BufferedReader bufferedReader, DXFData data){
        String[] code = readGroupCode(bufferedReader);
        Point extMin;
        Point extMax;
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])){
            switch (code[1]){
                case DXF_EXTMIN:
                    extMin = new Point();
                    code = readGroupCode(bufferedReader);
                    extMin.setX(Double.parseDouble(code[1]));
                    code = readGroupCode(bufferedReader);
                    extMin.setY(Double.parseDouble(code[1]));
                    data.setExtMin(extMin);
                    break;
                case DXF_EXTMAX:
                    extMax = new Point();
                    code = readGroupCode(bufferedReader);
                    extMax.setX(Double.parseDouble(code[1]));
                    code = readGroupCode(bufferedReader);
                    extMax.setY(Double.parseDouble(code[1]));
                    data.setExtMax(extMax);
                    break;
                case "0":
                    return code;
            }
            code = readGroupCode(bufferedReader);
        }
        return code;
    }
    /* 读取headers --end*/


    /* 读取tables --start*/

    /**
     * 读取tables 数据
     * @param bufferedReader
     * @param data
     * @return
     */
    private String[] parseTables(BufferedReader bufferedReader, DXFData data) {
        String[] code = readGroupCode(bufferedReader);
        while(code[1] != null && !DXF_ENDSEC.equalsIgnoreCase(code[1])){
            switch (code[1]){
                case "TABLE":
                    System.out.println();
                    while(code[1] != null && !DXF_ENDTAB.equalsIgnoreCase(code[1])){
                        if("2".equalsIgnoreCase(code[0])){
                            switch (code[1]){
                                case "LAYER":
                                    // parse layer
                                    code = readGroupCode(bufferedReader); // 读取一行；因为下面判断逻辑都是"LAYER"
                                    while(code[1] != null && !DXF_ENDTAB.equalsIgnoreCase(code[1])){
                                        if("LAYER".equalsIgnoreCase(code[1])){
                                                code = parseLayer(bufferedReader, data.getLayers());
                                        }else {
                                            code = readGroupCode(bufferedReader);
                                        }
                                    }
                                    break;
                                case "STYLE":
                                    // parse style
                                    code = readGroupCode(bufferedReader);
                                    break;
                                default:
                                    code = readGroupCode(bufferedReader);
                                    break;
                            }
                        }else {
                            code = readGroupCode(bufferedReader);
                        }
                    }
                    break;
                default:
                    code = readGroupCode(bufferedReader);
                    break;
            }
        }
        return code;
    }

    /**
     * 读取图层
     * @param bufferedReader
     * @param layers
     * tips:  table 下面会包含多个 0-layer
     * @return
     */
    private String[] parseLayer(BufferedReader bufferedReader, List<Layer> layers) {
        String[] code = readGroupCode(bufferedReader);
        Layer layer = null;
        Color color = new Color();
        while(code[1] != null && !DXF_ENDTAB.equalsIgnoreCase(code[1])){
            switch (code[0]){
                case "2":
                    if(layer != null ){
                        layers.add(layer);
                    }
                    layer = new Layer();
                    layer.setName(code[1]);
                    break;
                case "6":
                    layer.setLineTypeName(code[1]);
                    break;
                case "62":
                    color.setColor(Integer.parseInt(code[1])); 
                    break;
                case "70":
                    layer.setFlag(Integer.parseInt(code[1]));
                    break;
                case "370":
                    layer.setLineWeight(Double.parseDouble(code[1]));
                    break;
                case "0":
                    // 退出时 添加图层到 list
                    if(layer != null ){
                        layers.add(layer);
                    }
                    return code;

            }
            code = readGroupCode(bufferedReader);
        }
        // 退出时 添加图层到 list
        if(layer != null ){
            layers.add(layer);
        }
        return code;
    }

    /* 读取tables --end*/
    /**
     * 读取一组组码值
     * @param bufferedReader
     * @return
     */
    private String[] readGroupCode(BufferedReader bufferedReader){
        String[] code = new String[]{null,null};
        try{
            code[0] = bufferedReader.readLine();
            code[1] = bufferedReader.readLine();
            if(code[0] != null ){
                code[0] = code[0].trim();
            }
            if(code[1] != null ){
                code[1] = code[1].trim();
            }
        }catch (Exception e){
            // something must be error
            e.printStackTrace();
        }
        return code;
    }

}
