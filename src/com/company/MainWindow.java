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
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainWindow extends JFrame{

    String bookID;
    int quantity;
    int numBooks;
    int totalBooks = 0;
    int discount = 0;
    float subTotal;
    float itemCost;
    String info;
    int processed = 0;
    ArrayList<String> cart = new ArrayList<String>();
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

        //set confirm button false initially
        confirmItem.setEnabled(false);

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
                
                // if checkItem() worked properly, change button
                if(checkItem()) {
                    processItem.setEnabled(false);
                    confirmItem.setEnabled(true);
                }
            }
        });

        //adds the item to the order
        confirmItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addItem();
                confirmItem.setEnabled(false);

                if(numBooks != processed){
                    processItem.setEnabled(true);
                }
            }
        });

        //clears out orders
        newOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newOrder();
            }
        });

        //gives an alert that shows the current cart
        viewOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                viewOrder();
            }
        });

        //give alert for total order and clear out the order
        finishOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                finishOrder();
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
    //true if item is can move onto get part, false if failed.
    public boolean checkItem(){

        bookID = mainPanel.getBookID();
        numBooks = mainPanel.getnumBooks();
        quantity = mainPanel.getbookQuantity();

        //if there is no quantity specified, then return
        if(quantity <= 0){
            return false;
        }

        //message if there is no book id found
        if(!prices.containsKey((String)bookID)){
            JOptionPane.showMessageDialog(null, "Book ID " + bookID + " not in file");
            return false;
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

        info = bookID + " " + names.get(bookID) + " " + prices.get(bookID) + " " + quantity + " " + discount + "% $" + df.format(itemCost);
        mainPanel.setItemInfo(info);

        return true;
    }

    public void addItem(){

        cart.add(info);
        totalBooks = quantity;
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
        JOptionPane.showMessageDialog(null, "Item #" + (processed) + " accepted");
    }

    // clears everything out
    public void newOrder(){

        Order = new HashMap();
        cart = new ArrayList<String>();
        totalBooks = 0;
        subTotal = 0;
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
            int quant = (int) entry.getValue();
            String priceString = (String) prices.get(key);

            Float price = Float.parseFloat(priceString);
            subTotal += price * quant;
        }

        //apply discount
        subTotal = (float) ((0.01 * (100 - discount)) * subTotal);

        mainPanel.setSubtotal(df.format(subTotal));
    }

    //append the current cart and then give it as an alert
    public void viewOrder(){
        String tmp = "";

        for(int i = 0; i < cart.size(); i++){
            tmp += i + ". " + cart.get(i);
            tmp += "\n";
        }

        JOptionPane.showMessageDialog(null, tmp);
    }

    public void finishOrder(){
        // everything will be appended to this string
        String tmp = "";

        //EST time-zone
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));

        //adding date
        Date date = new Date();
        tmp += "Date: " + dateFormat.format(date) + "\n\n";

        // number of items added to the order
        tmp += "Numbers of line items: " + processed + "\n\n";

        // adding cart items
        tmp += "Item# / ID / Title / Price / QTY / Disc % / Subtotal\n\n";
        for(String item: cart){
            tmp += item + "\n";
        }

        //sub total amount
        tmp += "\n\nOrder subtotal: " + df.format(subTotal) + "\n\n";

        //tax rate is hard-coded, this probably won't work IRL
        tmp += "Tax rate: 6%\n\n";

        //adding tax amount
        double tax = subTotal * 0.06;
        tmp += "Tax amount: " + df.format(tax) + "\n\n";

        //adding total
        double total = tax + subTotal;
        tmp += "Order Total: " + df.format(total) + "\n\n";

        //giving final message as a alert
        tmp += "Thank you for shopping at the Ye Olde Book Shoppe!";

        writeToFile();
        JOptionPane.showMessageDialog(null, tmp);
    }

    //writes items in the cart to the transaction files
    public void writeToFile(){

        //find
        String file = "transactions.txt";
        Date date = new Date();

        //Date formatting
        SimpleDateFormat frontDate = new SimpleDateFormat("ddMMyyyyHHmm");
        SimpleDateFormat backDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
        frontDate.setTimeZone(TimeZone.getTimeZone("EST"));
        backDate.setTimeZone(TimeZone.getTimeZone("EST"));

        //these dates will be the same across all items in the cart
        String front = frontDate.format(date);
        String back = backDate.format(date);

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true));

            //add items from the cart to the transaction file
            for(String entry: cart){
                String tmp = front + ", " + entry + ", " + back;
                writer.newLine();
                writer.write(tmp);
            }

            writer.close();

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}

