package com.aries.dxfConvert;

import com.aries.dxfConvert.entities.ARC;
import com.aries.dxfConvert.entities.Point;

/**
 * 凸度对象从c#移过来，暂时没细看怎么算，命名也未修改
 * 算法误差略大，但基本可用，暂时不优化
 * Created by Aries
 */
public class TuDu {

    double dConvexityDegree = 0; //凸度  
    // long nSumLines = dxf_code.size();
    double theta_arc;
    double theta_degree;//角度,包角  
    double dAngle;//起点到终点的弦向量与X正方向之间的倾斜角  
    double dStarX = 0, dStarY = 0;//圆弧起始点  
    double dEndX = 0, dEndY = 0;  //圆弧终止点  
    double dStarC = 0, dEndC = 0; //圆弧起始角度，终止角度  
    double dmiddleX = 0, dmiddleY = 0;//起始点和终止点连接线的中点横纵坐标  
    double dCenterX = 0, dCenterY = 0;//圆心坐标  
    double dCenterX1 = 0, dCenterY1 = 0;//圆心坐标1  
    double dCenterX2 = 0, dCenterY2 = 0;//圆心坐标2  
    double dLength; //弦长  
    double dfR;  //半径  
    double dH;  //圆心到弦的距离  
    //double k; //起始点和终止点连线的中垂线斜率  
    double dAmass; //弦向量与X轴正向单位向量的叉积  
    double dDirectionAngel;//弦中点到圆心的直线向量的方向角（0-2PI之间）  
    double dD; //圆心到弦长的距离  
    double dNslope;////弦的斜率  
    double dK;     //弦中垂线的斜率  
    double dNAngel;//中垂线的倾斜角  
    double dX, dY;       //圆心相对于弦中心点的坐标偏移量  
    double num1, num2;   //x方向矢量和圆心到弧线起点和终点的矢量的叉乘的z   


    double k = 0.0;//弦的斜率  
    double k_verticle = 0.0;//弦的中垂线的斜率  
    double mid_x = 0.0, mid_y = 0.0;//弦的中点坐标  
    double a = 1.0;
    double b = 1.0;
    double c = 1.0;
    double angleChordX = 0;//弦向量X正方向的角度  
    int direction = 0;//判断是G02还是G03  
    boolean isMinorArc = true;//圆弧半径是否为较小的  
    double dStartVale = 0; //起始角的cos（dStarC）值  
    double dEndVale = 0; //终止角的cos（dEndC）值  
    double x1;
    double y1;
    double x2;
    double y2;
    
    public  TuDu(double CD, double p1x, double p1y, double p2x, double p2y)
    {
        dConvexityDegree = CD;
        x1 = p1x;
        y1 = p1y;
        x2 = p2x;
        y2 = p2y;
    }
    public ARC getTuDuArc()
    {
        double cenX=0,cenY=0,StartC=0,EndC=0,dR=0;
        
        // dConvexityDegree = code.r1;
        //当凸度dConvexityDegree不等于0时，表示为圆弧
        if (0 != dConvexityDegree)
        {
            theta_degree = 4 * Math.atan(Math.abs(dConvexityDegree));

            //起始点，终止点
            dStarX = x1;
            dStarY = y1;
            dEndX = x2;
            dEndY = y2;

            //弦长
            dLength = Math.sqrt(Math.pow(dStarX - dEndX, 2) + Math.pow(dStarY - dEndY, 2));
            //圆弧半径
            dfR = Math.abs(0.5 * dLength / Math.sin(0.5 * theta_degree));

            k = (dEndY - dStarY) / (dEndX - dStarX);
            if (k == 0)
            {
                dCenterX1 = (dStarX + dEndX) / 2.0;
                dCenterX2 = (dStarX + dEndX) / 2.0;
                dCenterY1 = dStarY + Math.sqrt(dfR * dfR - (dStarX - dEndX) * (dStarX - dEndX) / 4.0);
                dCenterY2 = dEndY - Math.sqrt(dfR * dfR - (dStarX - dEndX) * (dStarX - dEndX) / 4.0);
            }
            else
            {
                k_verticle = -1.0 / k;
                mid_x = (dStarX + dEndX) / 2.0;
                mid_y = (dStarY + dEndY) / 2.0;
                a = 1.0 + k_verticle * k_verticle;
                b = -2 * mid_x - k_verticle * k_verticle * (dStarX + dEndX);
                c = mid_x * mid_x + k_verticle * k_verticle * (dStarX + dEndX) * (dStarX + dEndX) / 4.0 -
                        (dfR * dfR - ((mid_x - dStarX) * (mid_x - dStarX) + (mid_y - dStarY) * (mid_y - dStarY)));

                dCenterX1 = (-1.0 * b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
                dCenterX2 = (-1.0 * b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
                dCenterY1 = k_verticle * dCenterX1 - k_verticle * mid_x + mid_y;
                dCenterY2 = k_verticle * dCenterX2 - k_verticle * mid_x + mid_y;
            }


            //凸度绝对值小于1表示圆弧包角小于180°，凸度绝对值大于1表示圆弧包角大于180°
            if (Math.abs(dConvexityDegree) <= 1)
                isMinorArc = true;
            else
                isMinorArc = false;


            //确定圆弧的顺逆
            if (0 > dConvexityDegree)
                direction = 2;
            else
                direction = 3;

            //确定圆心
            angleChordX = Math.acos((1 * (dEndX - dStarX) + 0 * (dEndY - dStarY)) / dLength) * 180 / Math.PI;
            if ((dEndY - dStarY) < 0)
            {
                angleChordX *= -1;
            }
            if ((angleChordX > 0 && angleChordX < 180) || angleChordX == 180)
            {
                if (direction == 2)//顺圆
                {
                    //jisy修改
                    //if (isMinorArc)
                    //{
                    //    dCenterX = dCenterX2;
                    //    dCenterY = dCenterY2;
                    //}
                    //else
                    //{
                    //    dCenterX = dCenterX1;
                    //    dCenterY = dCenterY1;
                    //}
                    if (isMinorArc)
                    {
                        dCenterX = dCenterX1;
                        dCenterY = dCenterY1;
                    }
                    else
                    {
                        dCenterX = dCenterX2;
                        dCenterY = dCenterY2;
                    }
                }
                else if (direction == 3)//逆圆
                {

                    if (isMinorArc)
                    {
                        dCenterX = dCenterX2;
                        dCenterY = dCenterY2;
                    }
                    else
                    {
                        dCenterX = dCenterX1;
                        dCenterY = dCenterY1;
                    }
                }
            }

            else
            {
                if (direction == 2)//顺圆
                {
                    //jisy修改
                    //if (isMinorArc)
                    //{
                    //    dCenterX = dCenterX1;
                    //    dCenterY = dCenterY1;
                    //}
                    //else
                    //{
                    //    dCenterX = dCenterX2;
                    //    dCenterY = dCenterY2;
                    //}
                    if (isMinorArc)
                    {
                        dCenterX = dCenterX2;
                        dCenterY = dCenterY2;
                    }
                    else
                    {
                        dCenterX = dCenterX1;
                        dCenterY = dCenterY1;
                    }
                }
                else if (direction == 3)//逆圆
                {

                    if (isMinorArc)
                    {
                        dCenterX = dCenterX1;
                        dCenterY = dCenterY1;
                    }
                    else
                    {
                        dCenterX = dCenterX2;
                        dCenterY = dCenterY2;
                    }
                }
            }


            //起始角度、终止角度
            dStartVale = (dStarX - dCenterX) / dfR;
            //在C++中，浮点型中的结果1可能是1.00000000000000001，避免这种情况出现。
            if (dStartVale > 1)
                dStartVale = 1;
            if (dStartVale < -1)
                dStartVale = -1;
            dStarC = Math.acos(dStartVale);
            //x方向矢量和圆心到弧线起点和终点的矢量的叉乘的z 
            num1 = dStarY - dCenterY;
            if (num1 < 0)
                dStarC = 2 * Math.PI - dStarC;

            //终止角度、终止角度
            dEndVale = (dEndX - dCenterX) / dfR;
            //在C++中，浮点型中的结果1可能是1.00000000000000001，避免这种情况出现。
            if (dEndVale > 1)
                dEndVale = 1;
            if (dEndVale < -1)
                dEndVale = -1;
            dEndC = Math.acos(dEndVale);
            //x方向矢量和圆心到弧线起点和终点的矢量的叉乘的z 
            num2 = dEndY - dCenterY;
            if (num2 < 0)
                dEndC = 2 * Math.PI - dEndC;

            cenX = dCenterX;
            cenY = dCenterY;
            StartC = dStarC / Math.PI * 180;
            EndC = dEndC / Math.PI * 180;
            //jisy修改
            if (dConvexityDegree < 0)
            {
                double M = StartC;
                StartC = EndC;
                EndC = M;
            }
            dR = dfR;
        }

        ARC arc = new ARC();
        Point point = new Point();
        point.setX(cenX);
        point.setY(cenY);
        arc.setCenterPoint(point);
        arc.setStartAngle(StartC);
        arc.setEndAngle(EndC);
        arc.setRadius(dR);
        return arc;
    }
}
