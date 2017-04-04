/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.data;

import java.awt.Color;

/**
 *
 * @author valentinstarlinger, padmal
 */
public enum Mode {
    HIGHALT(1,Color.GREEN),
    MIDALT(2,Color.ORANGE),
    LOWALT(3,Color.RED);

    private int i;
    private Color c;
    
    private Mode(int i, Color c){
        this.i = i;
        this.c = c;
    }
    
    public static Mode getModeFromInt(int i) {
        for (Mode value : Mode.values()) {
            if(i == value.i) return value;
        }
        return HIGHALT;
    }
    
    
}

