package Project3;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

// the window that we do our work in
public class Gui {

    JFrame mainFrame;
    QueryPanel queryPanel;
    DBPanel dbPanel;
    OutputPanel outputPanel;

    GridBagConstraints layout;

    Gui(){

        // this is the base we're adding all of our
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new GridBagLayout());

        layout = new GridBagConstraints();
        layout.weightx = 1;
        layout.weighty = 1;
        layout.fill = GridBagConstraints.BOTH;

        // adding panels
        queryPanel = new QueryPanel();
        layout.gridx = 1;
        layout.gridy = 0;
        mainFrame.add(queryPanel, layout);

        dbPanel = new DBPanel();
        layout.gridx = 0;
        layout.gridy = 0;
        mainFrame.add(dbPanel, layout);

        outputPanel = new OutputPanel();
        layout.gridx = 0;
        layout.gridy = 1;
        layout.gridwidth = 2;
        mainFrame.add(outputPanel, layout);

        mainFrame.setVisible(true);


    }

    // this is where we put the SQL query text
    class QueryPanel extends JPanel{

        JTextArea query;

        QueryPanel(){

            // panel specific
            this.setSize(400, 300);
            this.setLayout(new BorderLayout());

            //making a scrolly boi
            
            query = new JTextArea();

            //Quality of life stuff
            query.setTabSize(4);
            query.insert("//Write your SQL here!", 0);


            this.add(query, BorderLayout.CENTER);
        }

        // get what we need for execute our SQL query
        public String getQuery() {
            return query.getText();
        }
    }

    // This Panel has the information that the
    class DBPanel extends JPanel{

        DBPanel(){
            this.setSize(400, 300);
        }
    }

    // this is placed at the bottom of the frame
    //include the buttons we interact with as well as the
    class OutputPanel extends JPanel{

        OutputPanel(){
            this.setSize(800, 300);
        }
    }
}

