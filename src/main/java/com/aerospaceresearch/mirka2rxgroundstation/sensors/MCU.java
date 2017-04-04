/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.sensors;

import com.aerospaceresearch.mirka2rxgroundstation.data.Mode;
import com.aerospaceresearch.mirka2rxgroundstation.data.SensorPos;
import com.aerospaceresearch.mirka2rxgroundstation.data.Util;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class MCU {
    
    private int resetcounter;
    private Mode mode;    
    private int systemtime;
    private int msgnumber;
    private int num;
    
    public MCU(int num, int resetcounter, Mode state, int systemtime, int msgnumber) {
        this.num = num;
        this.resetcounter = resetcounter;
        this.mode = state;
        this.msgnumber = msgnumber;
        this.systemtime = systemtime;
    }

    public MCU(int num, String content) {
        this.num = num;
        parseData(content);
    }

    public int getMsgnumber() {
        return msgnumber;
    }

    public void setMsgnumber(int msgnumber) {
        this.msgnumber = msgnumber;
    }

    public int getResetcounter() {
        return resetcounter;
    }

    public void setResetcounter(int resetcounter) {
        this.resetcounter = resetcounter;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public int getSystemtime() {
        return systemtime;
    }

    public void setSystemtime(int systemtime) {
        this.systemtime = systemtime;
    }

    public void parseData(String content) {
        
        this.msgnumber = Integer.parseUnsignedInt(content.substring(SensorPos.MCU_MSG.getStart(), SensorPos.MCU_MSG.getEnd()),2);
        this.mode = (Mode.getModeFromInt(Integer.parseUnsignedInt(content.substring(SensorPos.MCU.getStart()+(num-1)*2, SensorPos.MCU.getEnd()+(num-1)*2),2)));
        this.resetcounter = Integer.parseUnsignedInt(content.substring(SensorPos.MCU_RC.getStart()+(num-1)*8, SensorPos.MCU_RC.getEnd()+(num-1)*8),2);
        this.systemtime = Util.binToUInt(content.substring(SensorPos.MCU_TIME.getStart()+(num-1)*16, SensorPos.MCU_TIME.getEnd()+(num-1)*16));
        
    }
    
    @Override
    public String toString(){
        return "MCU"+num+": "+"state: "+mode+", Message number: "+msgnumber+", Systemtime: "+systemtime+", Resetcounter: "+resetcounter;
    }
    
}
