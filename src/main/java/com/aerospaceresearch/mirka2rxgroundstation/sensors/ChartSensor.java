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
public abstract class ChartSensor {
    
    public ChartPoint getData(){
        return new ChartPoint(getTime(),getYValue());
    }
    
    public abstract Double getTime();
    public abstract Double getYValue(); 
    public abstract State getState();
    
}

