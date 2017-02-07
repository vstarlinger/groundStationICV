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
public class SD {

    private State state;
    private Mode m;

    public SD(State state) {
        this.state = state;
    }

    public SD(String content, Mode m) {
        this.m = m;
        parseContent(content);
    }

    private void parseContent(String content) {
        this.state = State.getState(Integer.parseUnsignedInt(content.substring(SensorPos.SD.getStart(m), SensorPos.SD.getEnd(m)),2));
    }

    @Override
    public String toString() {
        return "SD card: " + state;
    }

    public State getState() {
        return state;
    }
}
