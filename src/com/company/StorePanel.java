package com.company;

import javax.swing.*;
import java.awt.*;

public class StorePanel extends JPanel {

    public StorePanel(){
        Dimension size = getPreferredSize();
        //size.width = 250;

        setBorder(BorderFactory.createTitledBorder("Ye Olde Book Store"));

        JLabel numBooksLabel = new JLabel("Enter the number of books you wish to buy: ");
        JTextField numBooks = new JTextField(10);

        JLabel bookIDLabel = new JLabel("Enter Book ID");
        JTextField bookID = new JTextField(10);

        JLabel bookQuantiyLabel = new JLabel("Enter qauntity");
        JTextField bookQuantity = new JTextField(10);

        JLabel itemInfoLabel = new JLabel("Item Info");
        JTextField itemInfo = new JTextField(10);

        JLabel subtotalLabel = new JLabel("Order Subtotal");
        JTextField subtotal = new JTextField(10);

        setLayout(new GridBagLayout());

        // one letter variables, fight me m8
        GridBagConstraints c = new GridBagConstraints();

        // column
        c.weightx = 0.5;
        c.weighty = 0.5;

        c.gridx = 0;
        c.gridy = 0;
        add(numBooksLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        add(bookIDLabel, c);

        c.gridx = 0;
        c.gridy = 2;
        add(bookQuantiyLabel, c);

        c.gridx = 0;
        c.gridy = 3;
        add(itemInfoLabel, c);

        c.gridx = 0;
        c.gridy = 4;
        add(subtotalLabel, c);

    }
}
