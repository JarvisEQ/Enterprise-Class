package Project3;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
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
        mainFrame = new JFrame("Project 3");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(1000, 600));
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
    // DONE
    class QueryPanel extends JPanel{

        TitledBorder title;
        JTextArea query;
        JScrollPane scrollQuery;

        QueryPanel(){

            // panel specific
            this.setPreferredSize(new Dimension(400, 300));
            this.setLayout(new BorderLayout());
            title = BorderFactory.createTitledBorder("Enter Database Information");
            this.setBorder(title);

            //making a scroll-y boi
            query = new JTextArea();
            scrollQuery = new JScrollPane(query);


            //Quality of life stuff
            query.setTabSize(2);
            query.insert("// Write your SQL here!", 0);


            this.add(scrollQuery, BorderLayout.CENTER);
        }

        // get what we need for execute our SQL query
        public String getQuery() {
            return query.getText();
        }
    }

    // This Panel has the information that the
    // TODO, make pretty
    class DBPanel extends JPanel{

        // main panel stuff
        GridBagConstraints layout;
        TitledBorder title;

        // this is what is in the drop-down boxes
        String[] jdbcDrivers = {"Place Holder"};
        String[] dbURL = {"Place Holder"};

        // areas that will hold information
        JComboBox jdbcList;
        JComboBox dbURLList;
        JTextField username;
        JPasswordField password;

        // labels for our things
        JLabel jdbcLabel;
        JLabel dbURLLabel;
        JLabel usernameLabel;
        JLabel passwordLabel;

        DBPanel(){

            this.setPreferredSize(new Dimension(400, 300));
            this.setLayout(new GridBagLayout());
            layout =  new GridBagConstraints();


            title = BorderFactory.createTitledBorder("Enter Database Information");
            this.setBorder(title);

            layout.fill = GridBagConstraints.HORIZONTAL;
            layout.weightx = 1;
            layout.weighty = 1;

            // for the driver part
            jdbcList = new JComboBox(jdbcDrivers);
            jdbcLabel = new JLabel("JDBC Driver");

            layout.gridx = 0;
            layout.gridy = 0;
            this.add(jdbcLabel, layout);

            layout.gridx = 1;
            layout.gridy = 0;
            this.add(jdbcList, layout);

            // for the DB list
            dbURLList = new JComboBox(dbURL);
            dbURLLabel = new JLabel("Database URL");

            layout.gridx = 0;
            layout.gridy = 1;
            this.add(dbURLLabel, layout);

            layout.gridx = 1;
            layout.gridy = 1;
            this.add(dbURLList, layout);

            // for username
            username = new JTextField(20);
            usernameLabel = new JLabel("Username");

            layout.gridx = 0;
            layout.gridy = 2;
            this.add(usernameLabel, layout);

            layout.gridx = 1;
            layout.gridy = 2;
            this.add(username, layout);

            // for pass, include obscuring password
            password = new JPasswordField(20);
            passwordLabel = new JLabel("Password");

            layout.gridx = 0;
            layout.gridy = 3;
            this.add(passwordLabel, layout);

            layout.gridx = 1;
            layout.gridy = 3;
            this.add(password, layout);

        }
    }

    // this is placed at the bottom of the frame
    // includes the buttons we interact with as well as the
    // TODO
    class OutputPanel extends JPanel{

        //panel specifics
        GridBagConstraints layout;

        // items we're placing
        JButton connect;
        JButton clearSQL;
        JButton executeSQL;
        JButton clearResults;
        JPanel status;
        JLabel statusText;
        TitledBorder resultsLable;
        JScrollPane resultsScroll;
        JTextArea results;

        OutputPanel(){
            this.setPreferredSize(new Dimension(800, 300));
            this.setLayout(new GridBagLayout());
            layout = new GridBagConstraints();

            layout.fill =  GridBagConstraints.HORIZONTAL;
            layout.weighty = 1;
            layout.weightx = 1;

            // status - non interactive
            status = new JPanel();
            statusText = new JLabel("No Connection Now");
            statusText.setForeground(Color.RED);
            status.add(statusText);
            status.setBackground(Color.BLACK);
            layout.gridx = 0;
            layout.gridy = 0;
            this.add(status, layout);

            // connect button
            connect = new JButton("Connect to Database");
            connect.setBackground(Color.BLUE);
            connect.setForeground(Color.YELLOW);
            layout.gridx = 1;
            layout.gridy = 0;
            this.add(connect, layout);

            // Clear Sql button
            clearSQL = new JButton("Clear SQL Command");
            clearSQL.setBackground(Color.WHITE);
            clearSQL.setForeground(Color.RED);
            layout.gridx = 2;
            layout.gridy = 0;
            this.add(clearSQL, layout);

            // Execute SQL
            executeSQL = new JButton("Execute SQL Command");
            executeSQL.setBackground(Color.GREEN);
            executeSQL.setForeground(Color.BLACK);
            layout.gridx = 3;
            layout.gridy = 0;
            this.add(executeSQL, layout);

            // Clear Results
            clearResults = new JButton("Clear Results Window");
            clearResults.setBackground(Color.YELLOW);
            layout.gridx = 4;
            layout.gridy = 0;
            this.add(clearResults, layout);

            // results
            resultsLable = BorderFactory.createTitledBorder("SQL Execution Result Window");
            results = new JTextArea();
            resultsScroll = new JScrollPane();
            results.setEditable(false);
            resultsScroll.setBorder(resultsLable);
            resultsScroll.add(results);
            layout.fill = GridBagConstraints.BOTH;
            layout.weighty = 5;
            layout.ipadx = 50;
            layout.ipady = 50;
            layout.gridwidth = 5;
            layout.gridx = 0;
            layout.gridy = 1;
            this.add(resultsScroll, layout);

        }
    }
}



