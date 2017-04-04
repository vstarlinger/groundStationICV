/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.process;

import com.aerospaceresearch.mirka2rxgroundstation.data.SerialEvent;

/**
 *
 * @author valentinstarlinger, padmal
 */
public interface SerialListener {
    public abstract void messageReceived(SerialEvent e);
    public abstract void error(String msg);
}
