package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author valentinstarlinger
 */
public class TempPanel extends JPanel {

    private JLabel tempLabel, sensorNumberLabel;
    
    private static final Color OK = new Color(2, 100, 64);
    private static final Color HIGH = new Color(224, 5, 32);
    private static final Color LOW = new Color(6, 36, 249);
    
    private float minOK, maxOK;

    public TempPanel(String sensorName, float minOK, float maxOK, float temp) {
        this.minOK = minOK;
        this.maxOK = maxOK;

        this.tempLabel = new JLabel();
        setTemp(temp);
        this.sensorNumberLabel = new JLabel(sensorName);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 5;
        this.add(this.tempLabel, gbc);
        gbc.gridy++;
        this.add(this.sensorNumberLabel,gbc);
    }

    public void setTemp(float temp) {
        tempLabel.setText(temp + " °C");

        this.tempLabel.setForeground(OK);

        if (temp < minOK) {
            this.tempLabel.setForeground(LOW);
        }

        if (temp > maxOK) {
            this.tempLabel.setForeground(HIGH);
        }
    }

    public void setSensorNumber(String sensorNumber) {
        this.sensorNumberLabel.setText(sensorNumber);
    }

    public float getMinOK() {
        return minOK;
    }

    public void setMinOK(float minOK) {
        this.minOK = minOK;
    }

    public float getMaxOK() {
        return maxOK;
    }

    public void setMaxOK(float maxOK) {
        this.maxOK = maxOK;
    }

}
