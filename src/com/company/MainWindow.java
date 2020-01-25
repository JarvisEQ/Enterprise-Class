package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainWindow extends JFrame{

    int processed = 0;
    Map Order = new HashMap();
    Map names;
    Map prices;
    StorePanel mainPanel;

    String filePath = "inventory.txt";


    public MainWindow(String title){
        super(title);

        //first, read the txt file
        readInText();

        //layout manager
        setLayout(new BorderLayout());

        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //adding components
        Container c = getContentPane();
        mainPanel = new StorePanel();

        JButton processItem = new JButton("Process Item");
        JButton confirmItem = new JButton("Confirm Item");
        JButton viewOrder = new JButton("View Order");
        JButton finishOrder = new JButton("Finish Order");
        JButton newOrder = new JButton("New Order");
        JButton exitStore = new JButton("Exit");

        JPanel buttons = new JPanel();

        buttons.add(processItem);
        buttons.add(confirmItem);
        buttons.add(viewOrder);
        buttons.add(finishOrder);
        buttons.add(newOrder);
        buttons.add(exitStore);


        c.add(mainPanel, BorderLayout.CENTER);
        c.add(buttons, BorderLayout.SOUTH);

        //action listeners
        exitStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        processItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addItem();
            }
        });


    }

    public void readInText(){

        names = new HashMap();
        prices = new HashMap();

        try{

            Scanner reader = new Scanner(new FileReader(filePath));
            reader.useDelimiter(",");

            while(reader.hasNext()){
                String tmp = reader.next();

                names.put(tmp, reader.next());
                prices.put(tmp, reader.next());

            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addItem(){

        String bookID = mainPanel.getBookID();
        int numBooks = mainPanel.getnumBooks();
        int quantity = mainPanel.getbookQuantity();

        // if there is no more books to add, return without appending orders
        if(numBooks <= processed){
            return;
        }

        //if there is no quantity specified, then return
        if(quantity <= 0){
            return;
        }

        if(Order.containsKey(bookID)){
            int tmp = (int) Order.get(bookID);
            quantity += tmp;
        }

        Order.put(bookID, quantity);

        System.out.println(Order);
    }
}


