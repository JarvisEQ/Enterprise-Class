/* Name: Liam Jarvis
 Course: CNT 4714 – Spring 2020
 Assignment title: Project 1 – Event-driven Enterprise Simulation
 Date: Sunday January 26, 2020
*/

package com.company;

import javax.swing.*;
import java.awt.*;

public class StorePanel extends JPanel {

    JTextField numBooks;
    JTextField bookID;
    JTextField bookQuantity;
    JTextField itemInfo;
    JTextField subtotal;

    public StorePanel(){
        Dimension size = getPreferredSize();
        //size.width = 700;
        //size.height = 300;

        setBorder(BorderFactory.createTitledBorder("Ye Olde Book Store"));

        JLabel numBooksLabel = new JLabel("Enter the number of books you wish to buy: ");
        numBooks = new JTextField(40);

        JLabel bookIDLabel = new JLabel("Enter Book ID");
        bookID = new JTextField(40);

        JLabel bookQuantiyLabel = new JLabel("Enter qauntity");
        bookQuantity = new JTextField(40);

        JLabel itemInfoLabel = new JLabel("Item Info");
        itemInfo = new JTextField(40);

        JLabel subtotalLabel = new JLabel("Order Subtotal");
        subtotal = new JTextField(40);

        setLayout(new GridBagLayout());

        // one letter variables, fight me m8
        GridBagConstraints c = new GridBagConstraints();

        // column 0 - labels
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

        //column 1 - text area

        c.weightx = 0.5;
        c.weighty = 0.5;

        c.gridx = 3;
        c.gridy = 0;
        add(numBooks, c);

        c.gridx = 3;
        c.gridy = 1;
        add(bookID, c);

        c.gridx = 3;
        c.gridy = 2;
        add(bookQuantity, c);

        c.gridx = 3;
        c.gridy = 3;
        add(itemInfo, c);

        c.gridx = 3;
        c.gridy = 4;
        add(subtotal, c);

    }

    public String getBookID(){
        return bookID.getText();
    }

    public int getnumBooks(){
        try {
            return Integer.parseInt(numBooks.getText());
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter a number for number of books");
            return 0;
        }
    }

    public int getbookQuantity(){
        try {
            return Integer.parseInt(bookQuantity.getText());
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter a number for number of books");
            return 0;
        }
    }

    public void clearAll(){
        numBooks.setText("");
        bookID.setText("");
        bookQuantity.setText("");
        itemInfo.setText("");
        subtotal.setText("");
    }

    public void setItemInfo(String info){
        itemInfo.setText(info);
    }

    public void clearForNextItem(){
        bookID.setText("");
        bookQuantity.setText("");
    }

    public void setSubtotal(String sub){
        subtotal.setText(sub);
    }
}
