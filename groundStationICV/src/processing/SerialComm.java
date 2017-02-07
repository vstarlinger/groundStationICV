package processing;

import data.SerialEvent;
import java.util.ArrayList;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

public class SerialComm {

    SerialPort serialPort;

    private ArrayList<SerialListener> listener = new ArrayList<>();
    

    public void start(String sp, int baudrate) {
        if (!sp.isEmpty()) {
            if (serialPort != null && serialPort.isOpened()) {
                close();
            }
            serialPort = new SerialPort(sp);//"/dev/tty.usbserial-A702WKDS");
            try {
                serialPort.openPort();//Open serial port //serialPort.setParams(9600, 8, 1, 0); 
                serialPort.setParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
                serialPort.setEventsMask(mask);//Set mask
                serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener

            } catch (SerialPortException e) {
                System.out.println(e);
                error("Error: Couldn't open Serialport.\n");
            }
        }
    }

    public void close() {
        try {
            serialPort.closePort();
        } catch (SerialPortException ex) {
            error("Error: Couldn't close Serialport.");
        }
    }

    public void send(String msg) {
        try {
            //add a $ to the beginning and a # at the end
            serialPort.writeBytes(("$" + msg + "#").getBytes());
        } catch (SerialPortException ex) {
            error("Error: Couldn't send message: " + msg + ".");
        }
    }
    
    public ArrayList<String> getPorts(){
        ArrayList<String> al = new ArrayList<>();
        for (String s : jssc.SerialPortList.getPortNames()) {
            al.add(s);
        }
        return al;
    }

    public boolean isOpen() {
        return serialPort != null && serialPort.isOpened();
    }

    /*
     * In this class must implement the method serialEvent, through it we learn about 
     * events that happened to our port. But we will not report on all events but only 
     * those that we put in the mask. In this case the arrival of the data and change the 
     * status lines CTS and DSR
     */
    private class SerialPortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR()) {//If data is available
                try {
                    byte buffer[] = serialPort.readBytes(event.getEventValue());
                    String s = "";
                    for (byte c : buffer) {
                        s += (char) c;
                    }
                    messageReceived(new SerialEvent(s, event.getPortName(), System.currentTimeMillis()));
                } catch (SerialPortException ex) {
                    error("Error: Couldn't read incoming Bytes.");
                }
            }
        }
    }

    private void messageReceived(SerialEvent e) {
        for (SerialListener l : listener) {
            l.messageReceived(e);
        }
    }

    private void error(String msg) {
        for (SerialListener l : listener) {
            l.error(msg);
        }
    }

    public void addSerialListener(SerialListener l) {
        listener.add(l);
    }

    public void removeSerialListener(SerialListener l) {
        listener.remove(l);
    }

}
