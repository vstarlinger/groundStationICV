/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.sensors;

import com.aerospaceresearch.mirka2rxgroundstation.data.State;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class IMUData extends ChartSensor{

    private Double time, y;
    private State s;

    public IMUData(Double time, Double y, State s) {
        this.time = time;
        this.y = y;
        this.s = s;
    }
    
    @Override
    public Double getTime() {
        return time;
    }

    @Override
    public Double getYValue() {
        return y;
    }
    
    @Override
    public State getState(){
        return s;
    }
}

