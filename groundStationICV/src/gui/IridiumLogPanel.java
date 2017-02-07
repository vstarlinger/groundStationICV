/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static java.lang.Thread.sleep;
import processing.MailReceiver;
import processing.SerialComm;

/**
 *
 * @author valentinstarlinger
 */
public class IridiumLogPanel extends LogPanel {

    private boolean lastStat = false;

    private boolean run = true;

    MailReceiver mr;

    public IridiumLogPanel(MailReceiver mr) {
        this.mr = mr;
        updateStatus();
    }

    @Override
    public void updateStatus() {
        new Thread() {

            @Override
            public void run() {

                while (run) {
                    try {
                        boolean tmp = mr.isConnected();
                        if (tmp != lastStat) {
                            lastStat = tmp;
                            IridiumLogPanel.this.updateStatusPanel(lastStat);
                        }
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        System.err.println(ex.getMessage());
                    }
                }

            }

        }.start();
    }

}
