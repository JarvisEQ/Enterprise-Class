/*
Name: Liam Jarvis
Course: CNT 4714 Spring 2020
Assignment title: Project 2 – Multi-threaded programming in Java
Date: February 9, 2020
Class: Routers.java
*/
package Project2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Routers implements Runnable{

    int numberOps;
    int routerNum;
    int totalRouters;
    ReentrantLock lock1;
    ReentrantLock lock2;

    // make the class, but not run it yet
    public Routers(int numberOps, int routerNum, int totalRouters, ReentrantLock lock1, ReentrantLock lock2){
        this.numberOps = numberOps;
        this.routerNum = routerNum;
        this.totalRouters = totalRouters;
        this.lock1 = lock1;
        this.lock2 = lock2;

        // saying the things that need to be said
        System.out.println("Station " + this.routerNum + ": In-Connection set to conveyor " + this.routerNum);
        System.out.println("Station " + this.routerNum + ": Out-Connection set to conveyor " + ((this.routerNum + 1) % totalRouters ));
        System.out.println("Station " + this.routerNum + ": Workload set. Station " + this.routerNum + " has " + this.numberOps + " packages to move");
    }

    // do this to start the sim
    public void run(){

        int i = 0;

        while(i < numberOps){

            // lock conveyor one
            lock1.lock();
            System.out.println("Station " + this.routerNum + ": holds lock on (granted access) to conveyor" + this.routerNum);

            // was getting three-way deadlocks in my program, so I did this
            // It causes more outputs in the terminal, but it stops those deadlocks
            // also, it seems to run faster this way?
            try {
                // try to lock the second lock
                if(lock2.tryLock(  (int) Math.random() * 10, TimeUnit.SECONDS)){
                    System.out.println("Station " + this.routerNum + ": holds lock on (granted access) to conveyor" + ((this.routerNum + 1) % totalRouters));
                } else {
                    // if we couldn't lock lock2, unlock1 and start again
                    lock1.unlock();
                    System.out.println("Station " + this.routerNum +": unlocks (released access) to conveyor"  + this.routerNum);
                    continue;
                }

            // need to watch for Interrupts when waiting on lock2, could mess up our day
            } catch (InterruptedException e){
                lock1.unlock();
                continue;
            }
            try {

                this.doWork();

            } finally {
                lock1.unlock();
                lock2.unlock();

                System.out.println("Station " + this.routerNum +": unlocks (released access) to conveyor"  + this.routerNum);
                System.out.println("Station " + this.routerNum +": unlocks (released access) to conveyor"  + ((this.routerNum + 1) % totalRouters ));
            }


            i++;
            System.out.println("Station " + this.routerNum + ": successfully moves packages on conveyor " + this.routerNum);
        }

        System.out.println("Station " + this.routerNum + ": has " +(this.numberOps - i) + " package groups left to move.");

    }

    // In this sim, doing work is just sleeping, just like me irl....
    private void doWork(){
        try {
            Thread.sleep(420);
            System.out.println("Station " + this.routerNum + ": successfully moves packages on conveyor " + this.routerNum);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
