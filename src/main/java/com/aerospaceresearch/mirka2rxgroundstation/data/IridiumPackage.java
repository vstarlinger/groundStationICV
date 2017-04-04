/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.data;

import static com.aerospaceresearch.mirka2rxgroundstation.data.SensorPos.CJ;
import static com.aerospaceresearch.mirka2rxgroundstation.data.SensorPos.PS;
import static com.aerospaceresearch.mirka2rxgroundstation.data.SensorPos.TC;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.CJ;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.ChartSensor;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.GPS;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.IMU;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.Iridium;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.MCU;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.PS;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.SD;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.TC;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.TP;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.VB;
import java.util.ArrayList;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class IridiumPackage {

    int posPB = 47;

    String content;

    private ArrayList<MCU> mcus;
    private ChartSensor vb;
    private Iridium iridium;
    private SD sd;
    private ArrayList<ChartSensor> spi1, spi2, ap;
    private ArrayList<IMU> imu;
    private ArrayList<ChartSensor> tc1, tc2, tc3, tc4, tc5, tc6;
    private ArrayList<ChartSensor> cj1, cj2, cj3, cj4, cj5, cj6;
    private ArrayList<ChartSensor> tp;
    private ArrayList<GPS> gps;

    public IridiumPackage(String content) {
        this.content = content;

        parseContent();
    }

    public boolean isOK(){
        return (content.matches("[01]*"));// && checkParity());
    }
    
    public boolean checkParity() {
        int c = 0;
        for (int i = 0; i < content.length(); i++) {
            if(i==posPB)continue;
            if (content.charAt(i) == '1') {
                c++;
            }
        }

        return ((c % 2) == Integer.parseInt(content.charAt(posPB) + ""));

    }

    private void parseContent() {
        //first parse HKdata

        mcus = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            mcus.add(new MCU((i + 1), content));
        }

        Mode m = mcus.get(0).getMode();
        int time = mcus.get(0).getSystemtime();

        vb = new VB(content, m,time);
        iridium = new Iridium(content, m);
        sd = new SD(content, m);

        spi1 = new ArrayList<>();
        spi2 = new ArrayList<>();
        ap = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            spi1.add(new PS(content, i + 1, 1, m,time));
            spi2.add(new PS(content, i + 1, 2, m,time));
            ap.add(new PS(content, i + 1, 3, m,time));
        }

        imu = new ArrayList<>();

        for (int i = 0; i < SensorPos.IMU_C.getFrequency(m); i++) {
            imu.add(new IMU(content, i + 1, m, time));
        }

        tc1 = new ArrayList<>();
        tc2 = new ArrayList<>();
        tc3 = new ArrayList<>();
        tc4 = new ArrayList<>();
        tc5 = new ArrayList<>();
        tc6 = new ArrayList<>();

        for (int i = 0; i < SensorPos.TC.getFrequency(m); i++) {
            tc1.add(new TC(content, i + 1, 1, m,time));
            tc2.add(new TC(content, i + 1, 2, m,time));
            tc3.add(new TC(content, i + 1, 3, m,time));
            tc4.add(new TC(content, i + 1, 4, m,time));
            tc5.add(new TC(content, i + 1, 5, m,time));
            tc6.add(new TC(content, i + 1, 6, m,time));
        }

        cj1 = new ArrayList<>();
        cj2 = new ArrayList<>();
        cj3 = new ArrayList<>();
        cj4 = new ArrayList<>();
        cj5 = new ArrayList<>();
        cj6 = new ArrayList<>();

        for (int i = 0; i < SensorPos.CJ.getFrequency(m); i++) {
            cj1.add(new CJ(content, i + 1, 1, m,time));
            cj2.add(new CJ(content, i + 1, 2, m,time));
            cj3.add(new CJ(content, i + 1, 3, m,time));
            cj4.add(new CJ(content, i + 1, 4, m,time));
            cj5.add(new CJ(content, i + 1, 5, m,time));
            cj6.add(new CJ(content, i + 1, 6, m,time));
        }

        tp = new ArrayList<>();
        for (int i = 0; i < SensorPos.TP.getFrequency(m); i++) {
            tp.add(new TP(content, i + 1, m,time));
        }

        gps = new ArrayList<>();
        for (int i = 0; i < SensorPos.GPS.getFrequency(m); i++) {
            gps.add(new GPS(content, i +1, m, time));
        }
    }

    public String getOriginalContent() {
        return content;
    }

    @Override
    public String toString() {
        if(mcus == null) return "";
        String s = "";

        for (MCU mcu : mcus) {
            s += mcu.toString() + "\n";
        }

        Mode m = mcus.get(0).getMode();

        s += iridium.toString() + "\n";
        s += vb.toString() + "\n";
        s += sd.toString() + "\n";

        for (IMU imu1 : imu) {
            s+= imu1.toString() + "\n";
        }

        for (int i = 0; i < SensorPos.TC.getFrequency(m); i++) {
            s += tc1.get(i).toString() + "\n";
            s += tc2.get(i).toString() + "\n";
            s += tc3.get(i).toString() + "\n";
            s += tc4.get(i).toString() + "\n";
            s += tc5.get(i).toString() + "\n";
            s += tc6.get(i).toString() + "\n";
        }

        for (int i = 0; i < SensorPos.CJ.getFrequency(m); i++) {
            s += cj1.get(i).toString() + "\n";
            s += cj2.get(i).toString() + "\n";
            s += cj3.get(i).toString() + "\n";
            s += cj4.get(i).toString() + "\n";
            s += cj5.get(i).toString() + "\n";
            s += cj6.get(i).toString() + "\n";
        }
        
        for (ChartSensor tp1 : tp) {
            s += tp1.toString()+ "\n";
        }

        for (GPS g : gps){
            s += g.toString()+ "\n";
        }
        
        return s;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<MCU> getMcus() {
        return mcus;
    }

    public ArrayList<ChartSensor> getVb() {
        ArrayList<ChartSensor> a = new ArrayList<>();
        a.add(vb);
        return a;
    }

    public Iridium getIridium() {
        return iridium;
    }

    public SD getSd() {
        return sd;
    }

    public ArrayList<ChartSensor> getSpi1() {
        return spi1;
    }

    public ArrayList<ChartSensor> getSpi2() {
        return spi2;
    }

    public ArrayList<ChartSensor> getAp() {
        return ap;
    }

    public ArrayList<IMU> getImu() {
        return imu;
    }

    public ArrayList<ChartSensor> getTc1() {
        return tc1;
    }

    public ArrayList<ChartSensor> getTc2() {
        return tc2;
    }

    public ArrayList<ChartSensor> getTc3() {
        return tc3;
    }

    public ArrayList<ChartSensor> getTc4() {
        return tc4;
    }

    public ArrayList<ChartSensor> getTc5() {
        return tc5;
    }

    public ArrayList<ChartSensor> getTc6() {
        return tc6;
    }

    public ArrayList<ChartSensor> getCj1() {
        return cj1;
    }

    public ArrayList<ChartSensor> getCj2() {
        return cj2;
    }

    public ArrayList<ChartSensor> getCj3() {
        return cj3;
    }

    public ArrayList<ChartSensor> getCj4() {
        return cj4;
    }

    public ArrayList<ChartSensor> getCj5() {
        return cj5;
    }

    public ArrayList<ChartSensor> getCj6() {
        return cj6;
    }

    public ArrayList<ChartSensor> getTp() {
        return tp;
    }

    public ArrayList<GPS> getGps() {
        return gps;
    }

}

