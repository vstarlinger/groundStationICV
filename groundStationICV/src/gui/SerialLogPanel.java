/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import processing.SerialComm;

/**
 *
 * @author valentinstarlinger
 */
public class SerialLogPanel extends LogPanel{

    private SerialComm sc;
    
    private boolean run = true;
    
    public SerialLogPanel(SerialComm sc){
        this.sc = sc;
        updateStatus();
    }
    
    @Override
    public void updateStatus() {
        new Thread(){
            
            private boolean lastStat = false;
            
            @Override
            public void run(){
            
                while (run){
                    try {
                        if(sc.isOpen()!=lastStat){
                            lastStat = sc.isOpen();
                            SerialLogPanel.this.updateStatusPanel(sc.isOpen());
                        }
                        sleep(500);
                    } catch (InterruptedException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
                
            }
            
        }.start();
    }
    
}
