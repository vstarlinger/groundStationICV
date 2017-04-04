/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.process;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class LogSaver {
    
    private static File parent;
    
    private static final String PARENTNAME = "GS_Data";

    public static void saveIridium(String s) {
        File dir = new File(getParent()+"Iridium");
        if (!dir.exists() || dir.isDirectory()) {
            dir.mkdir();
        }

        File iFile = new File(getParent()+"Iridium/iridium_" +getTimeField()+ ".txt");

        FileWriter fw = null;
        try {
            fw = new FileWriter(iFile);
            fw.write(s);
            fw.flush();
        } catch (IOException ex) {
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    public static void saveDownlink(String s, boolean isIridium) {

        File dir = new File(getParent()+"Downlink");
        if (!dir.exists() || dir.isDirectory()) {
            dir.mkdir();
        }
        
        if (!isIridium) {
            File dFile = new File(getParent()+"Downlink/downlink_data.txt");
            File bFile = new File(getParent()+"Downlink/downlink_backup.txt");
            
            FileWriter fw = null;
            try {
                fw = new FileWriter(dFile,true);
                fw.write(s);
                fw.flush();
                fw.close();
                fw = new FileWriter(bFile,true);
                fw.write(s);
                fw.flush();
                fw.close();
            } catch (IOException ex) {
                
            } finally {
                try {
                    if (fw != null) {
                        fw.close();
                    }
                } catch (IOException ex) {
                }
            }
        } else {
            File idir = new File(getParent()+"Downlink/Iridium");
            
            if (!idir.exists() || idir.isDirectory()) {
                idir.mkdir();
            }

            File iFile = new File(getParent()+"Downlink/Iridium/iridium_" + getTimeField() + ".txt");

            FileWriter fw = null;
            try {
                fw = new FileWriter(iFile);
                fw.write(s);
                fw.flush();
            } catch (IOException ex) {
            } finally {
                try {
                    if (fw != null) {
                        fw.close();
                    }
                } catch (IOException ex) {
                }
            }
        }
    }
    
    private static String getParent(){
        if(parent == null){
            parent = new File(PARENTNAME+getTimeField());
            parent.mkdir();
        } 
        return parent.getAbsolutePath()+File.separator;
    }
    
    private static String getTimeField(){
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.SECOND);
            
            return year+"-"+month+"-"+day+"_"+hour+"-"+min+"-"+sec;
    }

    private static boolean isIridium(String s) {
        return false;
    }
}

