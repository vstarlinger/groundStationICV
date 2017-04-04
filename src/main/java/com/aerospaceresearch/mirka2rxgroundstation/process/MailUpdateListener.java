/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.process;

import com.aerospaceresearch.mirka2rxgroundstation.data.MailEvent;

/**
 *
 * @author valentinstarlinger, padmal
 */
public interface MailUpdateListener {
    public abstract void mailUpdated(MailEvent e);
    public abstract void error(String msg);
}
