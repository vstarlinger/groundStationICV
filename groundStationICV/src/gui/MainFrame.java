package gui;

import data.IridiumPackage;
import data.MailEvent;
import data.SerialEvent;
import data.Util;
import processing.MailUpdateListener;
import processing.MailReceiver;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import processing.LogSaver;
import processing.SerialComm;
import processing.SerialListener;

/**
 *
 * @author valentinstarlinger
 */
public class MainFrame extends JFrame {

    GridBagConstraints gbc;

    SerialComm sc;
    MailReceiver mr;

    IridiumPanel ip;
    CurrentDataPanel dp;
    SerialPanel sp;

    public MainFrame() {
        
        this.setDefaultCloseOperation(3);

        //MenuBar
        //Panels
        JPanel mainPanel = new JPanel();

        sc = new SerialComm();

        mr = new MailReceiver();

        dp = new CurrentDataPanel();
        dp.setMinimumSize(new java.awt.Dimension(500,dp.getHeight()));
        ip = new IridiumPanel(mr);
        mr.addMailUpdateListener(new MailListener());

        sp = new SerialPanel(sc);

        sp.addActionListener(new SendListener());

        sc.addSerialListener(new RXListener());
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ArrayList<String> ports = new ArrayList<>();
                        for (String s : jssc.SerialPortList.getPortNames()) {
                            ports.add(s);
                        }
                        sp.updatePortList(ports);
                        sleep(500);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }.start();

        JTabbedPane bottomPanel = new JTabbedPane();
        bottomPanel.addTab("Iridium", ip);
        bottomPanel.addTab("Serial", sp);
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(dp, BorderLayout.WEST);
        mainPanel.add(bottomPanel, BorderLayout.CENTER);

        this.getContentPane().add(mainPanel);
        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        // Create a scrollable pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        // Attach main panel to it
        add(scrollPane);
//        IridiumPackage ip = new IridiumPackage("00011011000000101101101010011111111111111111111000000010000000100000001000000001011101000000000101110100000000010111010010101111000110100011000100011010001100011001011000011010001100010001101000110001100101100001101000110001000110100011000110010110000110100011000100011010001100011001011000011010001100010001101000110001100101100001101000110001000110100011000110010110100000000001111000000000000000000000000000000000100000000001111000000000000000000000000000000000100000000001111000000000000000000000000000000000100000000001111000000000000000000000000000000000100000000001111000000000000000000000000000000000100000000001111000000000000000000000000000000000001111111111011000000000110000110000001000010000100000001010000111111111001111111111011000000000110000110000001000010000100000001010000111111111001111111111011000000000110000110000001000010000100000001010000111111111001111111111011000000000110000110000001000010000100000001010000111111111001111111111011000000000110000110000001000010000100000001010000111111111001111111111011000000000110000110000001000010000100000001010000111111111001111111111011000000000110000110000001000010000100000001010000111111111001111111111011000000000110000110000001000010000100000001010000111111111001111111111011000000000110000110000001000010000100000001010000111111111001111111111011000000000110000110000001000010000100000001010000111111111001111111111011000000000110000110000001000010000100000001010000111111111001111111111011000000000110000110000001000010000100000001010000110111100000000010100010100000001010001010000000101000101000000010100010100000001010001010000000101000101000000010100010100000001010001010000000101000101000000010100010100000001010001010000000101000101000000010100010100000001010001010000000101000101000000010100010100000001010001010000000101000101000000010100010100000001010001010000000101000101000000010100010100000001010001010000000101000101000000010100010100000001010001010000000101000101000000010100010100000001010001010000000101000101000000010100010100000001010001010000000101000101000000010100010100000001010001010000000101000101100000000110011010000000011001101000000001100110100000000110011010000000011001101000000001100110100000000110011010000000011001101000000001100110100000000110011010000000011001101000000001100110100000000110011010000000011001101000000001100110100000000110011010000000011001101000000001100110100000000110011010000000011001101000000001100110100000000110011010000000011001101000000001100110100000000110011010000000011001101000000001100110100000000110011010000000011001101000000001100110100000000110011010000000011001101000000001100110100000000110011010000000011001101000000001100110000000001100110000000000110011000000000011001100");
//        dp.updateData(ip);
//        
//        sp.updateData(new SerialEvent(ip.toString(), "d", 2));
    }

    private class SendListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (((JButton) e.getSource()).getText().equals("Send Command")) {
                if (sc.isOpen()) {
                    switch (e.getActionCommand()) {
                        case "LaunchMacro":
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        sc.send("Cameras");
                                        sleep(3*1000);
                                        sc.send("Prelaunch");
                                        sleep(20*1000);
                                        sc.send("Active");
                                        sleep(5*1000);
                                        sc.send("Cameras");
                                        sleep(2*1000);
                                        sc.send("Cameras");
                                        sleep(2*1000);
                                        sc.send("Prelaunch");
                                    } catch (InterruptedException ex) {
                                    }

                                }
                            }.start();
                            break;
                        default:
                            sc.send(e.getActionCommand());
                            break;
                    }
                } else {
                    sc.start(sp.getPort(), sp.getBaudrate());
                }
            } else {
                sc.start(sp.getPort(), sp.getBaudrate());
            }
        }

    }

    private class RXListener implements SerialListener {

        boolean isIridium = false;

        String buffer = "";

        String ISTART = "IRIDIUM";
        String IEND = "XMESSAGE";

        @Override
        public void messageReceived(final SerialEvent e) {
            //startwort - IRIDIUM : endwort -XMESSAGE
            //System.out.println(e.getMsg());
            System.out.println("msg received via serial");
            new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(6000);
                        isIridium = false;
                        if (!buffer.isEmpty()) {
                            LogSaver.saveDownlink("EIMESSAGE" + buffer + "ENDEIMESSAGE", false);
                            buffer = "";
                            sp.updateData(new SerialEvent("Received erroneous Iridium data\n", e.getPort(), e.getTimeStamp()));

                        }
                    } catch (InterruptedException ex) {
                    }
                }
            }.start();

            if (isIridium) {
                String tmp = buffer + e.getMsg();
                updateIridium(tmp, e);
                if (!buffer.isEmpty()) {
                    buffer = tmp;
                }
            } else { //currently not iridium 

                String msg = e.getMsg();
                if (msg.matches("([\\s\\w]*?(I(R(I(D(I(U(M)?)?)?)?)?)?))|([\\s\\w]*?IRIDIUM[\\s\\w]*)")) {
                    isIridium = true;
                    if (msg.contains(ISTART)) {
                        String[] sa = msg.split(ISTART);

                        if (sa.length == 1) {
                            updateIridium(sa[0], e);
                        } else if (sa.length == 2) {
                            sp.updateData(new SerialEvent(sa[0], e.getPort(), e.getTimeStamp()));
                            updateIridium(sa[1], e);
                        }
                    } else {
                        buffer = msg;
                    }
                } else {
                    LogSaver.saveDownlink(e.getMsg(), false);
                    sp.updateData(e);
                }
            }
//
//            if (e.getMsg().contains("IRIDIUM")) {
//                isIridium = true;
//            }
//
//            if (!isIridium) {
//                sp.updateData(e);
//            } else {
//                sp.updateData(new SerialEvent("Iridium Data received\n", e.getPort(), e.getTimeStamp()));
//            }
//            LogSaver.saveDownlink(e.getMsg(), isIridium);
//            if (e.getMsg().contains("XMESSAGE")) {
//                isIridium = false;
//            }
        }

        private void updateIridium(String s, SerialEvent e) {
            if (s.contains(IEND)) {
                String[] sab = s.split(IEND);

                buffer = "";
                isIridium = false;
                
                //System.out.println(sab[0]);
                //System.out.println("Iridiummessagelength: "+sab[0].length());
                String istring = Util.bytesToBinString(sab[0]);
                //System.out.println("Bitlength: "+istring.length());
                //System.out.println(istring);
                
                LogSaver.saveDownlink(istring, true);
                IridiumPackage ip = new IridiumPackage(istring);
                if (ip.isOK()) {
                    sp.updateData(new SerialEvent("Iridium Data received", e.getPort(), e.getTimeStamp()));
                    sp.updateData(new SerialEvent(sab[0], e.getPort(), e.getTimeStamp()));
                    dp.updateData(ip);
                } else {
                    sp.updateData(new SerialEvent("Received erroneous Iridium data", e.getPort(), e.getTimeStamp()));
                    sp.updateData(new SerialEvent(sab[0], e.getPort(), e.getTimeStamp()));
                }

                if (sab.length == 2) {
                    messageReceived(new SerialEvent(sab[1], e.getPort(), e.getTimeStamp()));
                }
            } else {
                buffer = s;
            }
        }

        @Override
        public void error(String msg) {
            sp.updateError(msg);
            LogSaver.saveDownlink(msg, isIridium);
        }

    }

    private class MailListener implements MailUpdateListener {

        @Override
        public void mailUpdated(MailEvent e) {
            ip.updateData(e);
            dp.updateData(e.getIridiumPackage());
            LogSaver.saveIridium(e.toString());
        }

        @Override
        public void error(String msg) {
            ip.updateError(msg);
            LogSaver.saveIridium(msg);
        }

    }
}
