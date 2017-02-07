package gui;

import data.SerialEvent;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import processing.SerialComm;

/**
 *
 * @author valentinstarlinger
 */
public class SerialPanel extends JPanel {

    private final JComboBox commandBox;
    private final JButton send, connect;
    private final LogPanel lp;
    private final JComboBox ports, baudrate;
    
    private static final String[] baudrates = {"38400","57600"};
    private static final String[] commands = {"Ping","Testing","Camtest","Cameras","Abort","Prelaunch","Active","Reset","LaunchMacro"};
    
    private ArrayList<ActionListener> listeners = new ArrayList<>();
    

    public SerialPanel(SerialComm sc) {
        commandBox = new JComboBox(commands);
        send = new JButton("Send Command");
        send.addActionListener(new SendListener());
        ports = new JComboBox();
        baudrate = new JComboBox(baudrates);
        connect = new JButton("Connect");
        connect.addActionListener(new ConnectListener());
        
        JPanel cp = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        cp.setLayout(new GridLayout(3,2));
        
        cp.add(baudrate);
        cp.add(new JLabel());
        cp.add(ports);
        cp.add(connect);
        cp.add(commandBox);
        cp.add(send);
        
        lp = new SerialLogPanel(sc);
        
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout(5,5));
        this.add(cp,BorderLayout.NORTH);
        this.add(lp, BorderLayout.CENTER);
        this.setPreferredSize(new java.awt.Dimension(0, 250));

    }

    public void updateData(SerialEvent e) {
        lp.updateData(e.getMsg(), e.getTimeStamp());
        
    }
    
    public void updateError(String msg){
        lp.updateError(msg);
    }
    
    public void addActionListener(ActionListener l){
        listeners.add(l);
    }
    
    public void removeActionListener(ActionListener l){
        listeners.remove(l);
    }

    public String getPort() {
        return ((String)ports.getSelectedItem());
    }
    
    public void updatePortList(ArrayList<String> ports){
        for (String port : ports) {
            if(!portsContains(port)){
                this.ports.addItem(port);
            }
        }
        for (int i = 0; i < this.ports.getItemCount(); i++) {
            boolean contains = false;
            for (String port : ports) {
                if(port.equals(this.ports.getItemAt(i))){
                    contains = true;
                }
            }
            if(!contains){
                this.ports.removeItemAt(i);
            }
        }
    }
    
    private boolean portsContains(String s){
        for (int i = 0; i < ports.getItemCount(); i++) {
            if(((String)ports.getItemAt(i)).equals(s)){
                return true;
            }
        }
        return false;
    }

    public int getBaudrate() {
        return Integer.parseInt((String)baudrate.getSelectedItem());
    }
    
    private class SendListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ActionEvent ae = new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, (String)commandBox.getSelectedItem());
            for (ActionListener listener : listeners) {
                listener.actionPerformed(ae);
            }
        }
        
    }
    
    private class ConnectListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ActionEvent ae = new ActionEvent(e.getSource(), ActionEvent.ACTION_PERFORMED, (String)ports.getSelectedItem());
            for (ActionListener listener : listeners) {
                listener.actionPerformed(ae);
            }
        }
        
    }
}
