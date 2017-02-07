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
public class PS extends ChartSensor{
    
    private State state;
    private int pressure;
    private int tpos, systime;
    private int type;
    private Mode m;
    
    
    public PS(){
        
    }

    public PS(String content, int tpos, int type, Mode m, int systime) {
        this.tpos = tpos;
        this.type = type;
        this.m = m;
        this.systime = systime;
        parseData(content);
    }
    
    @Override
    public Double getTime() {
        return new Double((systime + (tpos - 1)*(12/SensorPos.PS.getFrequency(m))));
    }

    @Override
    public Double getYValue() {
        if(type==3){
            double vout = pressure * 4;
            return (((vout/1024)+0.095)/0.009);
        }else{
            double d = pressure/10;
            return d*0.1;
        }
    }

    @Override
    public State getState() {
        return state;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
    
    public void parseData(String content) {
        
        this.state = State.getState(Integer.parseUnsignedInt(content.substring(SensorPos.PS_HK.getStart(m)+(type-1), SensorPos.PS_HK.getEnd(m)+(type-1)),2));
        //tauschen für spi -> endian
        this.pressure = Integer.parseUnsignedInt(content.substring(SensorPos.PS.getStart(m)+(type-1)*16+(tpos-1)*40
                , SensorPos.PS.getEnd(m)+(type-1)*16+(tpos-1)*40-(type==3?8:0)),2);
        
    }
    
    @Override
    public String toString(){
        return "Pressure Sensor "+type+": "+state+", pressure: "+getYValue()+" at tpos: "+tpos;
    }
    
}
