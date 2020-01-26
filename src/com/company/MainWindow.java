/* Name: Liam Jarvis
 Course: CNT 4714 – Spring 2020
 Assignment title: Project 1 – Event-driven Enterprise Simulation
 Date: Sunday January 26, 2020
*/

package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;

public class MainWindow extends JFrame{

    String bookID;
    int quantity;
    int numBooks;
    int processed = 0;
    int totalBooks = 0;
    int discount = 0;
    float subTotal;
    float itemCost;
    DecimalFormat df = new DecimalFormat("0.00");
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

        setSize(900, 300);
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

        // exits the program
        exitStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        //process an item, makes sure that it can be bought
        processItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkItem();
            }
        });

        //adds the item to the order
        confirmItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addItem();
            }
        });

        newOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newOrder();
            }
        });
    }

    //parse in the csv that we are initially using
    //going to be changed to a DB at some point in the semester
    public void readInText(){

        names = new HashMap();
        prices = new HashMap();

        try{

            BufferedReader buffy = new BufferedReader(new FileReader(filePath));
            String line;

            while((line = buffy.readLine()) != null){
                String[] values = line.split(",");

                names.put(values[0], values[1]);
                prices.put(values[0], values[2]);

            }

            buffy.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // checks that the Item is value and returns the values to the user
    public void checkItem(){

        bookID = mainPanel.getBookID();
        numBooks = mainPanel.getnumBooks();
        quantity = mainPanel.getbookQuantity();

        // if there is no more books to add, return without appending orders
        if(numBooks <= processed){
            return;
        }

        //if there is no quantity specified, then return
        if(quantity <= 0){
            JOptionPane.showMessageDialog(null, "Please specify a quantity");
            return;
        }

        //message if there is no book id found
        if(!prices.containsKey((String)bookID)){
            JOptionPane.showMessageDialog(null, "Book ID " + bookID + " not in file");
            return;
        }

        int tmp = totalBooks + quantity;
        if(tmp > 9){
            discount = 15;
        } else if (tmp > 4){
            discount = 10;
        }

        // calculate the item cost
        itemCost = Float.parseFloat((String) prices.get(bookID));
        itemCost = (float) ((quantity *  itemCost) * ((100-discount) * 0.01));

        String info = bookID + " " + names.get(bookID) + " " + prices.get(bookID) + " " + quantity + " " + discount + "% $" + df.format(itemCost);
        mainPanel.setItemInfo(info);
    }

    public void addItem(){

        //check if an item is already in the Order
        //if so, we're just going to append the quanity
        if(Order.containsKey(bookID)){
            int tmp = (int) Order.get(bookID);
            quantity += tmp;
        }

        Order.put(bookID, quantity);
        processed++;

        calculateSub();
        mainPanel.clearForNextItem();
        JOptionPane.showMessageDialog(null, "Item #" + (processed+1) + " accepted");
    }

    public void newOrder(){

        Order = new HashMap();
        processed = 0;
        totalBooks = 0;
        mainPanel.clearAll();
    }

    public void calculateSub(){

        //Zero-out the sub
        subTotal = 0;

        //Iterate over the Order set to calculate the sub total
        Iterator orderIte = Order.entrySet().iterator();

        while(orderIte.hasNext()){
            Map.Entry entry = (Map.Entry) orderIte.next();
            String key = (String) entry.getKey();
            int value = entry.setValue();

            Float price = Float.parseFloat(prices.get(key));
            subTotal += price * key;
        }

        mainPanel.setSubtotal(String.valueOf(subTotal));
    }
}

