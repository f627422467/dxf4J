package com.aries;

import com.aries.dxfConvert.DXFData;
import com.aries.dxfConvert.GraphicConverter;
import com.aries.dxfConvert.Parser;
import com.aries.dxfConvert.sql.Graphic;

import java.io.FileInputStream;
import java.util.List;

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
            data = parser.dxfParse(new FileInputStream(file));
            list = converter.converter(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(data);
        System.out.println("list:" + list.size());
    }
}
