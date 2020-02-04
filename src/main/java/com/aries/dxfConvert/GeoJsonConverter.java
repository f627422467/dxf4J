package com.aries.dxfConvert;

import com.aries.dxfConvert.entities.Line;
import com.aries.dxfConvert.geojson.Coordinate;
import com.aries.dxfConvert.geojson.Feature;
import com.aries.dxfConvert.geojson.GeoJSON;
import com.aries.dxfConvert.geojson.Geometry;

import java.util.*;

/**
 * Created by Aries
 */
public class GeoJsonConverter {

    public GeoJSON geojsonConverter(DXFData data){
        GeoJSON geoJSON = new GeoJSON();
        List<Feature> lines = convertLines(data.getLines());

        return geoJSON;
    }

    private List<Feature> convertLines(List<Line> lines) {
        List<Feature> features = new ArrayList<>();
        Iterator<Line> iterator = lines.iterator();
        while (iterator.hasNext()){
            Line line = iterator.next();
            Feature feature = new Feature();
            Geometry geometry = new Geometry();
            Coordinate start=new Coordinate(), end = new Coordinate();
            List<Coordinate> coordinates = new ArrayList<>();
            Map<String,Object> properties = new HashMap<String,Object>();
            feature.setType(Feature.FEATURE_TYPE_FEATURE);
            start.parsePoint(line.getStartPoint());
            end.parsePoint(line.getEndPoint());
            geometry.setType(Geometry.GEOMETRY_TYPE_LINESTRING);
            coordinates.add(start);
            coordinates.add(end);
            geometry.setCoordinates(coordinates);
            properties.put("", line.getHandleId());
            properties.put("", line.getLayerName());
            properties.put("", line.getLineWidth());
            properties.put("", line.getColorRGBStr());
            feature.setGeometry(geometry);
        }

        return features;
    }
}
