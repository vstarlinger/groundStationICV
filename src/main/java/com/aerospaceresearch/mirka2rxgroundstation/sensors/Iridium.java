/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.sensors;

import com.aerospaceresearch.mirka2rxgroundstation.data.Mode;
import com.aerospaceresearch.mirka2rxgroundstation.data.SensorPos;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class Iridium {
    
    private int signalstrength;
    private int alltimeHigh;
    private int alltimeLow;
    private int tries;
    private Mode m;

    public Iridium(int signalstrength, int alltimeHigh, int alltimeLow, int tries) {
        this.signalstrength = signalstrength;
        this.alltimeHigh = alltimeHigh;
        this.alltimeLow = alltimeLow;
        this.tries = tries;
    }
    
    public Iridium(String content, Mode m){
        this.m = m;
        parseData(content);
    }

    private void parseData(String content) {
        this.signalstrength = Integer.parseUnsignedInt(content.substring(SensorPos.IRIDIUM_SIGNAL.getStart(m), SensorPos.IRIDIUM_SIGNAL.getEnd(m)),2);
        this.alltimeHigh = Integer.parseUnsignedInt(content.substring(SensorPos.IRIDIUM_HIGH.getStart(m), SensorPos.IRIDIUM_HIGH.getEnd(m)),2);
        this.alltimeLow = Integer.parseUnsignedInt(content.substring(SensorPos.IRIDIUM_LOW.getStart(m), SensorPos.IRIDIUM_LOW.getEnd(m)),2);
        this.tries = Integer.parseUnsignedInt(content.substring(SensorPos.IRIDIUM_TRIES.getStart(m), SensorPos.IRIDIUM_TRIES.getEnd(m)),2);
    }

    @Override
    public String toString() {
        return "Iridium: Signalstrength: " + signalstrength + " High: " + alltimeHigh + " Low: " + alltimeLow + " Tries: " + tries;
    }


    public int getSignalstrength() {
        return signalstrength;
    }

    public void setSignalstrength(int signalstrength) {
        this.signalstrength = signalstrength;
    }

    public int getAlltimeHigh() {
        return alltimeHigh;
    }

    public void setAlltimeHigh(int alltimeHigh) {
        this.alltimeHigh = alltimeHigh;
    }

    public int getAlltimeLow() {
        return alltimeLow;
    }

    public void setAlltimeLow(int alltimeLow) {
        this.alltimeLow = alltimeLow;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }
    
    
}
