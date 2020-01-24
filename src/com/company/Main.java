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
