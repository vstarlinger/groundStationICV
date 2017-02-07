package gui;

import data.sensors.GPS;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * !!!! Falls ihr GPS visualisierung machen wollt sprecht nochmal mit mir (Valentin)
 * 
 * Habe in Kiruna damals dann noch versucht visualisierung auf Karten einzubinden.
 * Hat allerdings nur mäßig geklappt darum hab ich den Code wieder rausgenommen damit 
 * er nicht verwirrend ist.
 *
 * @author valentinstarlinger
 */
public class GPSPanel extends JPanel{
    JLabel lastRec, predictedImpact, jlA, jlB, jlPhi;
    
    
    public GPSPanel(){
        
        this.setBorder(BorderFactory.createTitledBorder("GPS"));
        
        GridLayout gl = new GridLayout(5,2);
        gl.setHgap(5);
        gl.setVgap(5);
        
        this.setLayout(gl);
        
        this.add(new JLabel("Last Recoreded Pos: "));
        lastRec = new JLabel();
        this.add(lastRec);
        
        this.add(new JLabel("Predicted Impact: "));
        predictedImpact = new JLabel();
        this.add(predictedImpact);
        
        this.add(new JLabel("A: "));
        jlA = new JLabel();
        this.add(jlA);
        
        this.add(new JLabel("B: "));
        jlB = new JLabel();
        this.add(jlB);
        
        this.add(new JLabel("Phi: "));
        jlPhi = new JLabel();
        this.add(jlPhi);
    }
    
    public void updateGPS(GPS gps){
        //nothing to see here...
    }
}
