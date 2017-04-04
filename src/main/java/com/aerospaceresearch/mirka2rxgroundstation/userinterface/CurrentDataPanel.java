/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.userinterface;

import com.aerospaceresearch.mirka2rxgroundstation.data.IridiumPackage;
import com.aerospaceresearch.mirka2rxgroundstation.data.Mode;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.ChartSensor;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.GPS;
import com.aerospaceresearch.mirka2rxgroundstation.sensors.IMU;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class CurrentDataPanel extends JPanel {

    ArrayList<TempPanel> thermocouples;
    TempPanel tempSensor;
    
    GridBagConstraints gbc;
    
    JTextArea jta;
    
    LineChart batchart, tcchart, cjchart, tpchart, imuqchart, imurchart, pschart;

    ArrayList<JLabel> jl_tcs, jl_cjs, jl_pss;
    
    JLabel jl_vbs, jl_sds, jl_imus, jl_tps, jl_cps, jl_gps;
    
    private JLabel jl_iss, jl_ihigh, jl_ilow, jl_tries;
    
    MCUPanel mcup1, mcup2, mcup3;
    
    GPSPanel gpsp;
    
    private static final Font BIGFONT = new Font("Helvetica,Arial", Font.BOLD, 14);
    
    public CurrentDataPanel(boolean b){
        super();
        
        JScrollPane jsp = new JScrollPane();
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jta = new JTextArea();
        jsp.setViewportView(jta);
        this.setLayout(new BorderLayout(5,5));
        this.add(jsp,BorderLayout.CENTER);
    }

    public CurrentDataPanel() {
        super();
        /**
         * 
         */
        JPanel statePanel = new JPanel();
        
        statePanel.setBorder(BorderFactory.createTitledBorder("States"));
        
        gbc = new GridBagConstraints();
        statePanel.setLayout(new GridBagLayout());
        
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.weighty = 1;
        gbc.weightx = 1;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        jl_tcs = new ArrayList<>();
        jl_cjs = new ArrayList<>();
        
        for (int i = 1; i < 5; i++) {
            gbc.insets = new Insets(10,5,5,5);
            jl_tcs.add(new JLabel("TC"+i,SwingConstants.CENTER));
            jl_tcs.get(i-1).setOpaque(true);
            jl_cjs.add(new JLabel("CJ"+i,SwingConstants.CENTER));
            jl_cjs.get(i-1).setOpaque(true);
            gbc.gridy = 0;
            statePanel.add(jl_tcs.get(i-1),gbc);
            
            gbc.gridy = 2;
            statePanel.add(jl_cjs.get(i-1),gbc);
            gbc.gridx++;
            
        }
        
        gbc.gridx = 0;
            
        for (int i = 5; i < 7; i++) {
            jl_tcs.add(new JLabel("TC"+i,SwingConstants.CENTER));
            jl_tcs.get(i-1).setOpaque(true);
            jl_cjs.add(new JLabel("CJ"+i,SwingConstants.CENTER));
            jl_cjs.get(i-1).setOpaque(true);
            
            gbc.gridy = 1;
            statePanel.add(jl_tcs.get(i-1),gbc);
            gbc.gridy = 3;
            statePanel.add(jl_cjs.get(i-1),gbc);
            gbc.gridx++;
            
        }
        
        gbc.gridy++;
        
        gbc.gridx = 0;
        
        gbc.insets = new Insets(10,5,10,5);
        jl_vbs = new JLabel("VB",SwingConstants.CENTER);
        jl_vbs.setOpaque(true);
        statePanel.add(jl_vbs, gbc);
        
        gbc.gridx++;
        jl_sds = new JLabel("SD",SwingConstants.CENTER);
        jl_sds.setOpaque(true);
        statePanel.add(jl_sds, gbc);
        
        gbc.gridx++;
        jl_imus = new JLabel("IMU",SwingConstants.CENTER);
        jl_imus.setOpaque(true);
        statePanel.add(jl_imus, gbc);
        gbc.gridx++;
        
        jl_tps = new JLabel("TP",SwingConstants.CENTER);
        jl_tps.setOpaque(true);
        statePanel.add(jl_tps, gbc);
        
        gbc.gridy++;
        
        gbc.gridx = 0;
        jl_pss = new ArrayList<>();
        jl_pss.add(new JLabel("SPI1",SwingConstants.CENTER));
        jl_pss.get(0).setOpaque(true);
        statePanel.add(jl_pss.get(0),gbc);
        gbc.gridx++;
        jl_pss.add(new JLabel("SPI2",SwingConstants.CENTER));
        jl_pss.get(1).setOpaque(true);
        statePanel.add(jl_pss.get(1),gbc);
        gbc.gridx++;
        jl_pss.add(new JLabel("APS",SwingConstants.CENTER));
        jl_pss.get(2).setOpaque(true);
        statePanel.add(jl_pss.get(2),gbc);
        
        gbc.gridy++;
        gbc.gridx = 0;
        jl_gps = new JLabel("GPS", SwingConstants.CENTER);
        jl_gps.setOpaque(true);
        statePanel.add(jl_gps,gbc);
        
        /*
        *
        */
        
        mcup1 = new MCUPanel(1);
        mcup2 = new MCUPanel(2);
        mcup3 = new MCUPanel(3);
        
        JPanel ip = new JPanel();
        ip.setBorder(BorderFactory.createTitledBorder("Iridium"));
        
        GridLayout gl_i = new GridLayout(4,2);
        gl_i.setHgap(5);
        gl_i.setVgap(5);
        ip.setLayout(gl_i);
        
        ip.add(new JLabel("Signal strength:",SwingConstants.LEFT));
        jl_iss = new JLabel("",SwingConstants.LEFT);
        ip.add(jl_iss);
        ip.add(new JLabel("Tries:",SwingConstants.LEFT));
        jl_tries = new JLabel("",SwingConstants.LEFT);
        ip.add(jl_tries);
        ip.add(new JLabel("High:",SwingConstants.LEFT));
        jl_ihigh = new JLabel("",SwingConstants.LEFT);
        ip.add(jl_ihigh);
        ip.add(new JLabel("Low:",SwingConstants.LEFT));
        jl_ilow = new JLabel("",SwingConstants.LEFT);
        ip.add(jl_ilow);
        
        gpsp = new GPSPanel();
        
        /**
         * 
         */
        JLabel jl_temp = new JLabel("Temperature:");
        jl_temp.setFont(BIGFONT);
        
        tcchart = new LineChart("Thermocouples","Time [s]","Temperature [°C]");
        cjchart = new LineChart("Cold Junctions","Time [s]","Temperature [°C]");
        
        JLabel jl_therm = new JLabel("Thermopile:");
        jl_therm.setFont(BIGFONT);
        JLabel jl_p = new JLabel("Pressure:");
        jl_p.setFont(BIGFONT);
        
        tpchart = new LineChart("Thermopile","Time [s]","Temperature [°C]");
        pschart = new LineChart("Pressure Sensors","Time [s]","Pressure [mbar]");
        
        JLabel jl_pos = new JLabel("Attitude:");
        jl_pos.setFont(BIGFONT);
        JLabel jl_bat = new JLabel("Battery Voltage");
        jl_bat.setFont(BIGFONT);
        
        imuqchart = new LineChart("IMU","Time [s]","Quaternions");
        imurchart = new LineChart("IMU","Time [s]","Angular Rate [deg/s]");
        batchart = new LineChart("Battery Voltage","Time [s]","Voltage [V]");
        
        GroupLayout gl = new GroupLayout(this);
        this.setLayout(gl);
        
        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);
        
        gl.setVerticalGroup(gl.createSequentialGroup()
                    .addGroup(gl.createParallelGroup()
                            .addGroup(gl.createSequentialGroup()
                                .addComponent(statePanel,300,300,300)
                                .addComponent(mcup1)
                                .addComponent(mcup2)
                                .addComponent(mcup3)
                                .addComponent(ip)
                            )
                            .addGroup(gl.createSequentialGroup()
                                .addComponent(jl_temp)
                                .addGroup(gl.createParallelGroup()
                                        .addComponent(tcchart)
                                        .addComponent(cjchart)
                                )
                                .addGroup(gl.createParallelGroup()
                                        .addComponent(jl_therm)
                                        .addComponent(jl_p)
                                )
                                .addGroup(gl.createParallelGroup()
                                        .addComponent(tpchart)
                                        .addComponent(pschart)
                                )
                                .addGroup(gl.createParallelGroup()
                                        .addComponent(jl_pos)
                                )
                                .addGroup(gl.createParallelGroup()
                                        .addComponent(imuqchart)
                                        .addComponent(imurchart)
                                )
                                .addComponent(jl_bat)
                                .addGroup(gl.createParallelGroup()
                                        .addComponent(batchart)
                                        .addComponent(gpsp)
                                )
                            )
                    )
                    
        );
        
        gl.setHorizontalGroup(gl.createSequentialGroup()
                    .addGroup(gl.createParallelGroup()
                        .addComponent(statePanel)
                        .addComponent(mcup1)
                        .addComponent(mcup2)
                        .addComponent(mcup3) 
                        .addComponent(ip)
                    )
                    .addGroup(gl.createParallelGroup()
                        .addComponent(jl_temp)
                        .addComponent(tcchart)
                        .addComponent(jl_therm)
                        .addComponent(tpchart)
                        .addComponent(jl_pos)
                        .addComponent(imuqchart)
                        .addComponent(jl_bat)
                        .addComponent(batchart)
                    )
                    .addGroup(gl.createParallelGroup()
                        .addComponent(cjchart)
                        .addComponent(jl_p)
                        .addComponent(pschart)
                        .addComponent(imurchart)
                        .addComponent(gpsp)
                    )
                    
        );
        
    }
    
    void updateData(IridiumPackage ip) {
//        jta.setText(jta.getText()+"\n\n"+ip.toString());
        
        tcchart.addSeries("tc2", ip.getTc2());
        tcchart.addSeries("tc3", ip.getTc3());
        tcchart.addSeries("tc4", ip.getTc4());
        tcchart.addSeries("tc5", ip.getTc5());
        tcchart.addSeries("tc6", ip.getTc6());
        
        jl_tcs.get(0).setBackground(ip.getTc1().get(0).getState().getColor());
        jl_tcs.get(1).setBackground(ip.getTc2().get(0).getState().getColor());
        jl_tcs.get(2).setBackground(ip.getTc3().get(0).getState().getColor());
        jl_tcs.get(3).setBackground(ip.getTc4().get(0).getState().getColor());
        jl_tcs.get(4).setBackground(ip.getTc5().get(0).getState().getColor());
        jl_tcs.get(5).setBackground(ip.getTc6().get(0).getState().getColor());
        
        cjchart.addSeries("cj1", ip.getCj1());
        cjchart.addSeries("cj2", ip.getCj2());
        cjchart.addSeries("cj3", ip.getCj3());
        cjchart.addSeries("cj4", ip.getCj4());
        cjchart.addSeries("cj5", ip.getCj5());
        cjchart.addSeries("cj6", ip.getCj6());
        
        jl_cjs.get(0).setBackground(ip.getCj1().get(0).getState().getColor());
        jl_cjs.get(1).setBackground(ip.getCj2().get(0).getState().getColor());
        jl_cjs.get(2).setBackground(ip.getCj3().get(0).getState().getColor());
        jl_cjs.get(3).setBackground(ip.getCj4().get(0).getState().getColor());
        jl_cjs.get(4).setBackground(ip.getCj5().get(0).getState().getColor());
        jl_cjs.get(5).setBackground(ip.getCj6().get(0).getState().getColor());
        
        batchart.addSeries("battery", ip.getVb());
        jl_vbs.setBackground(ip.getVb().get(0).getState().getColor());
        
        if(ip.getMcus().get(0).getMode() != Mode.LOWALT){
            tpchart.addSeries("tp", ip.getTp());
            jl_tps.setBackground(ip.getTp().get(0).getState().getColor());
        }
        
        pschart.addSeries("spi1", ip.getSpi1());
        pschart.addSeries("spi2", ip.getSpi2());
        pschart.addSeries("analog", ip.getAp());
        
        jl_pss.get(0).setBackground(ip.getSpi1().get(0).getState().getColor());
        jl_pss.get(1).setBackground(ip.getSpi2().get(1).getState().getColor());
        jl_pss.get(2).setBackground(ip.getAp().get(2).getState().getColor());
        
        ArrayList<IMU> imu = ip.getImu();
        
        ArrayList<ChartSensor> imu_qw = new ArrayList<>();
        ArrayList<ChartSensor> imu_qx = new ArrayList<>();
        ArrayList<ChartSensor> imu_qy = new ArrayList<>();
        ArrayList<ChartSensor> imu_qz = new ArrayList<>();
        
        ArrayList<ChartSensor> imu_rx = new ArrayList<>();
        ArrayList<ChartSensor> imu_ry = new ArrayList<>();
        ArrayList<ChartSensor> imu_rz = new ArrayList<>();
        
        for (IMU imu1 : imu) {
            imu_qw.add(imu1.getQw());
            imu_qx.add(imu1.getQx());
            imu_qy.add(imu1.getQy());
            imu_qz.add(imu1.getQz());
            
            if(imu1.hasRs()){
                imu_rx.add(imu1.getRx());
                imu_ry.add(imu1.getRy());
                imu_rz.add(imu1.getRz());
            }
        }
        
        imuqchart.addSeries("qw", imu_qw);
        imuqchart.addSeries("qx", imu_qx);
        imuqchart.addSeries("qy", imu_qy);
        imuqchart.addSeries("qz", imu_qz);
       
        imurchart.addSeries("rx", imu_rx);
        imurchart.addSeries("ry", imu_ry);
        imurchart.addSeries("rz", imu_rz);
        
        jl_imus.setBackground(ip.getImu().get(0).getState().getColor());
        
        mcup1.setMode(ip.getMcus().get(0).getMode());
        mcup1.setTime(ip.getMcus().get(0).getSystemtime());
        mcup1.setRc(ip.getMcus().get(0).getResetcounter());
        mcup1.setMsgnum(ip.getMcus().get(0).getMsgnumber());
        
        mcup2.setMode(ip.getMcus().get(1).getMode());
        mcup2.setTime(ip.getMcus().get(1).getSystemtime());
        mcup2.setRc(ip.getMcus().get(1).getResetcounter());
        mcup2.setMsgnum(ip.getMcus().get(1).getMsgnumber());
        
        mcup3.setMode(ip.getMcus().get(2).getMode());
        mcup3.setTime(ip.getMcus().get(2).getSystemtime());
        mcup3.setRc(ip.getMcus().get(2).getResetcounter());
        mcup3.setMsgnum(ip.getMcus().get(2).getMsgnumber());
        
        jl_iss.setText(ip.getIridium().getSignalstrength()+"");
        jl_ihigh.setText(ip.getIridium().getAlltimeHigh()+""); 
        jl_ilow.setText(ip.getIridium().getAlltimeLow()+""); 
        jl_tries.setText(ip.getIridium().getTries()+"");
        
        jl_sds.setBackground(ip.getSd().getState().getColor());
        
        if(ip.getMcus().get(0).getMode() != Mode.HIGHALT){
            jl_gps.setBackground(ip.getGps().get(0).getState().getColor());
            
            for (GPS gp : ip.getGps()) {
                gpsp.updateGPS(gp);
            }
        }
    }
}

