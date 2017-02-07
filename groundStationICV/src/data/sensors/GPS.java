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
public class GPS {
    
    private State state;
    private float lon,lat,speed,heading;
    
    private Mode m;
    private int tpos, systime;

    public GPS(String content, int tpos, Mode m, int systime){
        this.tpos = tpos;
        this.m = m;
        this.systime = systime;
        parseData(content);
    }
    
    public Double getTime() {
        return new Double(systime + (tpos - 1) * (12/SensorPos.GPS.getFrequency(m)));
    }

    public void parseData(String content){
        this.state = State.getState(Integer.parseUnsignedInt(content.substring(SensorPos.GPS_HK.getStart(m), SensorPos.GPS_HK.getEnd(m)),2));
        
        int diff = SensorPos.GPS.getDiff(m) * 4;
        
        this.lat = Util.stringToFloat(content.substring(SensorPos.GPS.getStart(m)+ (tpos-1)*diff, SensorPos.GPS.getEnd(m) + (tpos-1)*diff));
        this.lon = Util.stringToFloat(content.substring(SensorPos.GPS.getStart(m)+ (tpos-1)*diff + 32 * 1, SensorPos.GPS.getEnd(m) + (tpos-1)*diff + 32 * 1));
        
        //!!!nicht nach protokol wurde evt. bei OBC vertauscht
        this.heading = Util.stringToFloat(content.substring(SensorPos.GPS.getStart(m)+ (tpos-1)*diff + 32 * 2, SensorPos.GPS.getEnd(m) + (tpos-1)*diff + 32 * 2));
        this.speed = Util.stringToFloat(content.substring(SensorPos.GPS.getStart(m)+ (tpos-1)*diff + 32 * 3, SensorPos.GPS.getEnd(m) + (tpos-1)*diff + 32 * 3));
        
    }
    
    @Override
    public String toString(){
        return "GPS: "+state+" lat: "+lat+", lon: "+lon+", speed: "+speed+", heading: "+heading;
    }

    public State getState() {
        return state;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDirection() {
        return heading;
    }

    public void setDirection(float direction) {
        this.heading = direction;
    }
    
}
