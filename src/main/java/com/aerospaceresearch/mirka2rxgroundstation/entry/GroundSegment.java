/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.entry;

import com.aerospaceresearch.mirka2rxgroundstation.userinterface.MainFrame;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class GroundSegment {
    
    public static void main(String[] args) {
        new MainFrame();
    }
    
}


/**
    Setup JDK 8 in windows.
    1. Download JDK 8.
    2. Set JAVA_HOME environment variable to JDK 1.8:
        - Right Click on My Computer
            -> Properties
            -> Advanced System Settings
            -> Environment Variables 
        - Find JAVA_HOME on the list and set it's value to something like:
            C:\Program Files\Java\jdk1.8.0_31
    3. Restart the PC
*/

/**
    Setup JDK 8 in Ubuntu.
    1. Launch Terminal by pressing Ctrl+Alt+T on your keyboard.
    2. Enter the following command:
        $ sudo gedit /etc/environment
    3. Depending on where you installed your Java, you will need to provide 
       the full path. Scroll to the end of the file and enter the following:
       - JAVA_HOME=/usr/lib/jvm/java-7-oracle
         export JAVA_HOME
    4. Save your file and exit gedit.
    5. Reload the system PATH with the following command:
        $ . /etc/environment
*/

/**
    Setup JDK 1.8 in Netbeans.
    1. Rightclick on project
    2. Go to:
        - Properties 
            -> Build 
            -> Compile 
            -> Select Java Platform as JDK 1.8
    3. Click OK
*/
