/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.userinterface;

import com.aerospaceresearch.mirka2rxgroundstation.data.Mode;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class MCUPanel extends JPanel{
    
    private JLabel mode, time, rc, msgnum;
    
    public MCUPanel(int num){
        
        this.setBorder(BorderFactory.createTitledBorder("MCU"+num));
        
        GridLayout gl = new GridLayout(4,2);
        gl.setHgap(5);
        gl.setVgap(5);
        this.setLayout(gl);
        
        this.add(new JLabel("Mode:",SwingConstants.LEFT));
        mode = new JLabel("",SwingConstants.LEFT);
        this.add(mode);
        this.add(new JLabel("Time:",SwingConstants.LEFT));
        time = new JLabel("",SwingConstants.LEFT);
        this.add(time);
        this.add(new JLabel("RC:",SwingConstants.LEFT));
        rc = new JLabel("",SwingConstants.LEFT);
        this.add(rc);
        this.add(new JLabel("Msg#:",SwingConstants.LEFT));
        msgnum = new JLabel("",SwingConstants.LEFT);
        this.add(msgnum);
        
        
    }

    public void setMode(Mode m) {
        this.mode.setText(m.name());
    }

    public void setTime(double time) {
        this.time.setText(time+"");
    }

    public void setRc(int rc) {
        this.rc.setText(rc+"");
    }

    public void setMsgnum(int msgnum) {
        this.msgnum.setText(msgnum+"");
    }
    
    
}

