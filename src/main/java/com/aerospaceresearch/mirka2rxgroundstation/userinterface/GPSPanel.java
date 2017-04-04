/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.userinterface;

import com.aerospaceresearch.mirka2rxgroundstation.sensors.GPS;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class GPSPanel extends JPanel{
    JLabel lastRec, predictedImpact, jlA, jlB, jlPhi;
    
    
    public GPSPanel(){
        
        this.setBorder(BorderFactory.createTitledBorder("GPS"));
        
        GridLayout gl = new GridLayout(5,2);
        gl.setHgap(5);
        gl.setVgap(5);
        
        this.setLayout(gl);
        
        this.add(new JLabel("Last Recoreded Pos: "));
        lastRec = new JLabel();
        this.add(lastRec);
        
        this.add(new JLabel("Predicted Impact: "));
        predictedImpact = new JLabel();
        this.add(predictedImpact);
        
        this.add(new JLabel("A: "));
        jlA = new JLabel();
        this.add(jlA);
        
        this.add(new JLabel("B: "));
        jlB = new JLabel();
        this.add(jlB);
        
        this.add(new JLabel("Phi: "));
        jlPhi = new JLabel();
        this.add(jlPhi);
    }
    
    public void updateGPS(GPS gps){
        //nothing to see here...
    }
}

