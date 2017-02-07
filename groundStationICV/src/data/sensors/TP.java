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

/**
 *
 * @author valentinstarlinger
 */
public class TP extends ChartSensor{
    
    public State state;
    
    public int temp;
    private int tpos, systime;
    private Mode m;


    public TP(State state, int temp) {
        this.state = state;
        this.temp = temp;
    }
    
    public TP(String content, int tpos, Mode mode, int systime) {
        this.tpos = tpos;
        this.systime = systime;
        this.m = mode;
        parseData(content);
    }
    
    @Override
    public Double getTime() {
        return new Double((systime + (tpos - 1)*(12/SensorPos.TP.getFrequency(m))));
    }

    @Override
    public Double getYValue() {
        return new Double(temp);
    }
    
    public void parseData(String content) {
        this.state = State.getState(Integer.parseUnsignedInt(content.substring(SensorPos.TP_HK.getStart(m), SensorPos.TP_HK.getEnd(m)),2));
        
        int diff = SensorPos.TP.getDiff(m);
        
        this.temp = Util.binToInt(content.substring(SensorPos.TP.getStart(m)+(tpos-1)*diff
                , SensorPos.TP.getEnd(m)+(tpos-1)*diff));
        
    }
    
    @Override
    public String toString(){
        return "Thermopile: "+state+", temp: "+getYValue()+" at tpos: "+tpos;
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
