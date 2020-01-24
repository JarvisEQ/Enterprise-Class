package com.company;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

    public MainWindow(String title){
        super(title);

        //layout manager
        setLayout(new BorderLayout());

        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //creating components
        JButton processItem = new JButton();
        JButton confirmItem = new JButton();
        JButton viewOrder = new JButton("View Order");
        JButton finishOrder = new JButton("Finish Order");
        JButton newOrder = new JButton("New Order");
        JButton exitStore = new JButton("Exit");

        //adding components
        Container c = getContentPane();
        StorePanel mainPanel = new StorePanel();
        c.add(mainPanel, BorderLayout.CENTER);
        c.add(exitStore, BorderLayout.SOUTH);

    }


}
