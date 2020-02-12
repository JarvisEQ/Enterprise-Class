/*
Name: Liam Jarvis
Course: CNT 4714 Spring 2020
Assignment title: Project 2 – Multi-threaded programming in Java
Date: February 9, 2020
Class: Conveyors.java
*/
package Project2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Conveyors {

    int numRouters;
    ArrayList<Integer> opInformation;
    ArrayList<Thread> threads;
    ArrayList<ReentrantLock> locks;

    public Conveyors(){

        // read file information
        this.readFile();

        // make the locks
        this.locks = new ArrayList<ReentrantLock>();
        for(int i = 0; i < this.numRouters; i++){
            this.locks.add(new ReentrantLock());
        }


        // create threads
        this.threads = new ArrayList<Thread>();

        // give it a number, opInfo, and locks
        for(int i = 0; i < this.numRouters; i++){
            this.threads.add(new Thread(new Routers(
                    opInformation.get(i),
                    i,
                    this.numRouters,
                    locks.get(i),
                    locks.get((i+1) % (this.numRouters - 1)))));
        }

        // run the routers
        for(Thread thread: threads){
            thread.start();
        }

    }

    // this does all of the messy file reading stuff
    public void readFile(){
        try{
            // get file and read stuff in
            // first item is the number of routers in the sim, rest is number of ops per router
            File config = new File("config.txt");
            Scanner scanner = new Scanner(config);

            this.numRouters = scanner.nextInt();
            this.opInformation = new ArrayList<Integer>();

            // get the op info
            for(int i = 0; i < this.numRouters; i++){
                opInformation.add(scanner.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
