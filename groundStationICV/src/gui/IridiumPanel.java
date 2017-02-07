package gui;

import data.MailEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.mail.Address;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import processing.MailReceiver;

/**
 *
 * @author valentinstarlinger
 */
public class IridiumPanel extends JPanel {

    private final LogPanel lp;
    private final JLabel lastSubject, lastFrom, lastFilename, lastTimestamp;
    private final JButton reconnect;
    MailReceiver mr;

    public IridiumPanel(MailReceiver mr) {

        this.mr = mr;
        
        lp = new IridiumLogPanel(mr);

        JPanel infoPanel = new JPanel();

        infoPanel.setLayout(new GridLayout(5, 2));

        JLabel from = new JLabel("Last Message from: ");
        lastFrom = new JLabel();
        infoPanel.add(from);
        infoPanel.add(lastFrom);

        JLabel subject = new JLabel("Subject: ");
        lastSubject = new JLabel();
        infoPanel.add(subject);
        infoPanel.add(lastSubject);

        JLabel filename = new JLabel("Filename: ");
        lastFilename = new JLabel();
        infoPanel.add(filename);
        infoPanel.add(lastFilename);

        JLabel received = new JLabel("Received At: ");
        lastTimestamp = new JLabel();
        infoPanel.add(received);
        infoPanel.add(lastTimestamp);
        
        reconnect = new JButton("Reconnect");
        reconnect.addActionListener(new ReconnectListener());
        infoPanel.add(new JLabel());
        infoPanel.add(reconnect);

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout(5,5));
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(lp, BorderLayout.CENTER);

    }

    public void updateData(MailEvent e) {
        String s = "";
        for (Address from : e.getFrom()) {
            s += "," + from.toString();
        }
        lastFrom.setText(s.substring(1));
        lastSubject.setText(e.getSubject());
        lastFilename.setText(e.getFilename());
        lastTimestamp.setText(new Date(e.getTimeStampGmail()).toString());
        
        lp.updateData(e.getIridiumPackage().toString(), e.getTimeStamp());
    }

    public void updateError(String msg) {
        lp.updateError(msg);
    }

    private class ReconnectListener implements ActionListener {

        @Override 
        public void actionPerformed(ActionEvent e){
            System.out.println("Status is: "+mr.isConnected());
            mr.reconnect();
            System.out.println("Status is: "+mr.isConnected());
        }
        
    }
}
