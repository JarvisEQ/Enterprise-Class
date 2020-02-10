/* Name: Liam Jarvis
 Course: CNT 4714 – Spring 2020
 Assignment title: Project 1 – Event-driven Enterprise Simulation
 Date: Sunday January 26, 2020
*/

package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // make the main window in our application
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow frame = new MainWindow("Ye Olde Shoppe - Spring 2020");
            }
        });
    }


}
