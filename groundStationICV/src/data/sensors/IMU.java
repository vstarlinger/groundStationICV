/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.sensors;

import data.Mode;
import data.SensorPos;
import data.State;
import data.Util;
import java.awt.Point;
import java.util.Arrays;

/**
 *
 * @author valentinstarlinger
 */
public class IMU {

    private State state;
    private int rx, ry, rz;
    private int qw, qx, qy, qz;
    private int calib;
    private int tpos, systime;
    private Mode m;

    public IMU(String content, int tpos, Mode m, int systime) {
        this.m = m;
        this.tpos = tpos;
        this.systime = systime;
        parseData(content);
    }
    
        
    public Double getTime() {
        return new Double(systime + (tpos - 1) * (12/SensorPos.IMU_Qw.getFrequency(m)));
    }

    public State getState() {
        return state;
    }

    public ChartSensor getRx() {
        return new IMUData(getTime(), new Double(rx),state);
    }

    public ChartSensor getRy() {
        return new IMUData(getTime(), new Double(ry),state);
    }

    public ChartSensor getRz() {
        return new IMUData(getTime(), new Double(rz),state);
    }

    public ChartSensor getQw() {
        return new IMUData(getTime(), new Double(qw),state);
    }

    public ChartSensor getQx() {
        return new IMUData(getTime(), new Double(qx),state);
    }

    public ChartSensor getQy() {
        return new IMUData(getTime(), new Double(qy),state);
    }

    public ChartSensor getQz() {
        return new IMUData(getTime(), new Double(qz),state);
    }

    public int getCalib() {
        return calib;
    }

    public void parseData(String content) {

        this.state = State.getState(Integer.parseUnsignedInt(content.substring(SensorPos.IMU_HK.getStart(m), SensorPos.IMU_HK.getEnd(m)),2));

        if (m == Mode.LOWALT || tpos % 2 != 0) {

            int o = m == Mode.LOWALT ? (tpos - 1) : tpos / 2;

            this.rx = Util.binToInt(content.substring(SensorPos.IMU_Rx.getStart(m) + o * SensorPos.IMU_Rx.getDiff(m)*3
                    , SensorPos.IMU_Rx.getEnd(m) + o * SensorPos.IMU_Rx.getDiff(m)*3));
//            System.out.println(content.substring(SensorPos.IMU_Rx.getStart(m) + o * SensorPos.IMU_Rx.getDiff(m)*3
//                    , SensorPos.IMU_Rx.getEnd(m) + o * SensorPos.IMU_Rx.getDiff(m)*3));
            this.ry = Util.binToInt(content.substring(SensorPos.IMU_Ry.getStart(m) + o * SensorPos.IMU_Ry.getDiff(m)*3
                    , SensorPos.IMU_Ry.getEnd(m) + o * SensorPos.IMU_Ry.getDiff(m)*3));
            this.rz = Util.binToInt(content.substring(SensorPos.IMU_Rz.getStart(m) + o * SensorPos.IMU_Rz.getDiff(m)*3
                    , SensorPos.IMU_Rz.getEnd(m) + o * SensorPos.IMU_Rz.getDiff(m)*3));
        }

//        System.out.println("pos: "+(posQw.x + (tpos - 1) * 72)+" - "+(posQw.y + (tpos - 1) * 72)+" - "+tpos);
//        System.out.println(Arrays.toString(Util.getContentFromPos(posQw.x + (tpos - 1) * 72, posQw.y + (tpos - 1) * 72, content)));

        int diff = (SensorPos.IMU_Qw.getDiff(m))*4 + (SensorPos.IMU_C.getDiff(m));

        this.qw = Util.binToInt(content.substring(SensorPos.IMU_Qw.getStart(m) + (tpos - 1) * diff
                , SensorPos.IMU_Qw.getEnd(m) + (tpos - 1) * diff));
        this.qx = Util.binToInt(content.substring(SensorPos.IMU_Qx.getStart(m) + (tpos - 1) * diff
                , SensorPos.IMU_Qx.getEnd(m) + (tpos - 1) * diff));
        this.qy = Util.binToInt(content.substring(SensorPos.IMU_Qy.getStart(m) + (tpos - 1) * diff
                , SensorPos.IMU_Qy.getEnd(m) + (tpos - 1) * diff));
        this.qz = Util.binToInt(content.substring(SensorPos.IMU_Qz.getStart(m) + (tpos - 1) * diff
                , SensorPos.IMU_Qz.getEnd(m) + (tpos - 1) * diff));
        
        this.calib = Integer.parseUnsignedInt(content.substring(SensorPos.IMU_C.getStart(m) + (tpos - 1) * diff, SensorPos.IMU_C.getEnd(m) + (tpos - 1) * diff),2);

    }
    
    @Override
    public String toString() {
        String s = "IMU: " + state;
        if (m == Mode.LOWALT || tpos % 2 != 0) {
            s += ", rx: " + rx + ", ry: " + ry + ", rz: " + rz;
        }
        s += ", qw: " + qw + ", qx: " + qx + ", qy: " + qy + ", qz: " + qz + ", Calib: " + calib;
        return s;
    }

    public boolean hasRs() {
        return (m == Mode.LOWALT || tpos % 2 != 0);
    }

}
