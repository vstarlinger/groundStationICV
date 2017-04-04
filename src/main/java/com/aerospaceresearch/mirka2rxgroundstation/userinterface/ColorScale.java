/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.userinterface;

import java.awt.Color;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class ColorScale {
    
    private static final Color OK = new Color(2, 100, 64);
    private static final Color HIGH = new Color(224, 5, 32);
    private static final Color LOW = new Color(6, 36, 249);
    
    private static final int HORIZONTAL = 1;
    private static final int VERTICAL = 2;
    
    private int height = 100;
    private int numScales = 5;
    
    private int max,min,current;

    public ColorScale(int alignment, int max, int min, int current) {
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNumScales() {
        return numScales;
    }

    public void setNumScales(int numScales) {
        this.numScales = numScales;
    }
}
