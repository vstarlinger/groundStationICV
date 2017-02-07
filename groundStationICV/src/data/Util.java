package data;

import data.sensors.ChartPoint;
import data.sensors.ChartSensor;
import java.util.ArrayList;

/**
 * ACHTUNG!!! Gibt hier sehr viel unschönen code der gemacht wurde um auf die 
 * speziellen Anforderungen der Kapselsoftware einzugehen...
 * 
 * geht sicher auch schöner ;)
 * 
 * @author valentinstarlinger
 */
public class Util {
     
    
    public static int binToUInt(String t){
        int num = 0;
        
        String b = switchIntEndian(t);
        for (int i = 0; i < b.length(); i++) {
            if(b.charAt(i)=='1'){
                num+=Math.pow(2, b.length()-(i+1));
            }
        }
        
        return num;
    }
    
    public static String switchIntEndian(String i){
        return i.substring(8) + i.substring(0, 8);
    }
    
    public static int binToInt(String t){
        int num = 0;
        //System.out.println("bintoint: "+t);
        String b = (t.length()==16) ? switchIntEndian(t) : t;
        //System.out.println("aftercon: "+b);
        for (int i = 1; i < b.length(); i++) {
            if(b.charAt(i)=='1'){
                num+=Math.pow(2, b.length()-(i+1));
            }
        }
        //System.out.println("result: "+(b.charAt(0) == '1' ? num*(-1):num));
        return b.charAt(0) == '1' ? num*(-1):num;
    }
    
    public static String intToBits(int b){
        String s = Integer.toBinaryString(b);
        while(s.length()<8){
            s = "0"+s;
        }
        return s;
    }
    
    public static String bytesToBinString(String s) {
        String text = "";
        for (byte b : s.getBytes()) {
            text+=intToBits(b);
        }
        return text;
    }
    
    public static String floatEndianess(String s){
        return s.substring(24)+s.substring(16,24)+s.substring(8, 16)+s.substring(0, 8);
    }
    
    public static float stringToFloat(String s){
        String b = floatEndianess(s);
        int ii = Util.binToInt(b);
        float f = Float.intBitsToFloat(ii);
        return f;
    }
    
    public static float stringToUFloat(String s){
        return Float.intBitsToFloat(Integer.parseInt(s,2));
    }
    
    public static byte[] getContentFromPos(int x, int y, byte[] content){
        byte[] msg = new byte[y-x+1];
        for (int i = 0; i < msg.length; i++) {
            msg[i] = content[x+i];
        }
        return msg;
    }
    
    public static ArrayList<ChartPoint> getSensorData(ArrayList<ChartSensor> al){
        ArrayList<ChartPoint> a = new ArrayList<>();
        al.stream().forEach((cs) -> {
            a.add(cs.getData());
        });
        return a;
    }
    
}
