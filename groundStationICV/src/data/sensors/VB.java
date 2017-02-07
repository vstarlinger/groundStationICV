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

/**
 *
 * @author valentinstarlinger
 */
public class VB extends ChartSensor {

    private State state;
    private int systime;
    private int voltage;
    private Mode m;

    public VB(State state, int voltage, Mode m) {
        this.state = state;
        this.voltage = voltage;
        this.m = m;
    }

    public VB(String content, Mode m, int systime) {
        this.m = m;
        this.systime = systime;
        parseData(content);
    }

    @Override
    public Double getTime() {
        return new Double(systime);
    }

    @Override
    public Double getYValue() {
        return getVoltage();
    }

    public State getState() {
        return state;
    }

    public double getVoltage() {
        double i = voltage * 4;
        
        return (i/1024)*9.9;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public void parseData(String content) {

        this.state = State.getState(Integer.parseUnsignedInt(content.substring(SensorPos.VB_HK.getStart(m), SensorPos.VB_HK.getEnd(m)), 2));
        this.voltage = Integer.parseUnsignedInt(content.substring(SensorPos.VB.getStart(m), SensorPos.VB.getEnd(m)), 2);

    }

    @Override
    public String toString() {
        return "Battery state: " + state + ", Voltagelevel: " + getVoltage();
    }

}
