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
public enum State {
    OK (Color.green.darker()),
    ERROR(Color.red.darker());
    
    private Color c;
    
    private State (Color c){
        this.c = c;
    }
    
    public Color getColor(){
        return c;
    }
    
    public static State getState(int i){
        switch (i){
            case 1:
                return OK;
            case 0:
            default:
                return ERROR;
        }
    }
}

