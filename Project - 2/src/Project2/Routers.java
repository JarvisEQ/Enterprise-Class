/*
Name: Liam Jarvis
Course: CNT 4714 Spring 2020
Assignment title: Project 2 – Multi-threaded programming in Java
Date: February 9, 2020
Class: Routers.java
*/
package Project2;

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
        System.out.println("Station " + this.routerNum + ": Out-Connection set to conveyor " + ((this.routerNum + 1) % (totalRouters + 1)));
        System.out.println("Station " + this.routerNum + ": Workload set. Station " + this.routerNum + " has " + this.numberOps + " packages to move");
    }

    // do this to start the sim
    public void run(){

        int i = 0;

        while(i < numberOps){

            lock1.lock();

            // TODO, implement a timer to break dead-lock if they happen

            lock2.lock();
            try{

                this.doWork();


            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }

            i++
        }

        System.out.println("* * Station " + this.routerNum + ": Workload successfully completed. * *");

    }

    // In this sim, doing work is just sleeping, just like me irl....
    private void doWork(){
        try {
            Thread.sleep(420);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
