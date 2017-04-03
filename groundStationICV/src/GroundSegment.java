/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import gui.MainFrame;

/**
 *
 * @author valentinstarlinger
 */
public class GroundSegment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :  javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
    } catch (Exception e) {
        /* Will not cause */
    }
        // Startup
        new MainFrame();
    }
    
}
