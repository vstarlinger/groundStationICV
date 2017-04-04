/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.sensors;

import com.aerospaceresearch.mirka2rxgroundstation.data.Mode;
import com.aerospaceresearch.mirka2rxgroundstation.data.SensorPos;
import com.aerospaceresearch.mirka2rxgroundstation.data.State;
import com.aerospaceresearch.mirka2rxgroundstation.data.Util;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class TC extends ChartSensor{
    
    private State state;
    
    private int temp;
    private int num,tpos, systime;
    private Mode m;

    public TC(int tpos, int temp, Mode m, int systime) {
        this.tpos = tpos;
        this.m = m;
        this.systime = systime;
        this.temp = temp;
    }
    
    public TC(){
        
    }

    public TC(String content, int tpos, int num, Mode mode, int systime) {
        this.tpos = tpos;
        this.num = num;
        this.systime = systime;
        this.m = mode;
        parseData(content);
    }

    @Override
    public Double getTime() {
        return new Double((systime + (tpos - 1)*(12/SensorPos.TC.getFrequency(m))));
    }

    @Override
    public Double getYValue() {
        double b = temp;
        return b/10;
    }
    
    public void parseData(String content) {
        this.state = State.getState(Integer.parseUnsignedInt(content.substring(SensorPos.TC_HK.getStart(m)+(num-1), SensorPos.TC_HK.getEnd(m)+(num-1)),2));
        
        int diff = SensorPos.TC.getDiff(m);
        int timeDiff = diff * 6;
        
//        System.out.println("!!!!Thermocouplewert bin: "+content.substring(SensorPos.TC.getStart(m)+(num-1)*diff+(tpos-1)*timeDiff
//                , SensorPos.TC.getEnd(m)+(num-1)*diff+(tpos-1)*timeDiff) + ", start: "+(SensorPos.TC.getStart(m)+(num-1)*diff+(tpos-1)*timeDiff)+" - end: "+(SensorPos.TC.getEnd(m)+(num-1)*diff+(tpos-1)*timeDiff));
        
        
        this.temp = Util.binToInt(content.substring(SensorPos.TC.getStart(m)+(num-1)*diff+(tpos-1)*timeDiff
                , SensorPos.TC.getEnd(m)+(num-1)*diff+(tpos-1)*timeDiff));
        
    }
    
    @Override
    public String toString(){
        return "Thermocouple "+num+": "+state+", temp: "+getYValue()+" at tpos: "+tpos;
    }
    
    
    public State getState() {
        return state;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
    
    
}
