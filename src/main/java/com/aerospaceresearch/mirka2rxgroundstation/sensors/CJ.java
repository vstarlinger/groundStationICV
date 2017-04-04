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
public class CJ extends ChartSensor{
    
    private State state;
    
    private int temp;
    private int num,tpos, systime;
    private Mode m;

    public CJ(State state, int temp) {
        this.state = state;
        this.temp = temp;
    }
    
    public CJ(String content, int tpos, int num, Mode mode, int systime) {
        this.tpos = tpos;
        this.num = num;
        this.m = mode;
        this.systime = systime;
        parseData(content);
    }
    
    @Override
    public Double getTime() {
        return new Double((systime + (tpos - 1)*(12/SensorPos.CJ.getFrequency(m))));
    }

    @Override
    public Double getYValue() {
        return ((double)temp)/10;
    }
        
    public void parseData(String content) {
        this.state = State.getState(Integer.parseUnsignedInt(content.substring(SensorPos.CJ_HK.getStart(m)+(num-1), SensorPos.CJ_HK.getEnd(m)+(num-1)),2));
        
        int diff = SensorPos.CJ.getDiff(m);
        int timeDiff = diff * 6;
        
        this.temp = Util.binToInt(content.substring(SensorPos.CJ.getStart(m)+(num-1)*diff+(tpos-1)*timeDiff
                , SensorPos.CJ.getEnd(m)+(num-1)*diff+(tpos-1)*timeDiff));
        
    }
    
    @Override
    public String toString(){
        return "Coldjunction "+num+": "+state+", temp: "+getYValue()+" at tpos: "+tpos;
    }

    public State getState() {
        return state;
    }

    public int getTemp() {
        return temp;
    }
}

