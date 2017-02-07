/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.sensors;

import data.State;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author valentinstarlinger
 */
public abstract class ChartSensor {
    
    public ChartPoint getData(){
        return new ChartPoint(getTime(),getYValue());
    }
    
    public abstract Double getTime();
    public abstract Double getYValue(); 
    public abstract State getState();
    
}
