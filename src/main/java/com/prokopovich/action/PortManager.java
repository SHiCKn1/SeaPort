package com.prokopovich.action;

import com.prokopovich.entity.Ship;

import java.util.concurrent.TimeUnit;

public class PortManager {
    public static void startWork() throws InterruptedException {
        int NumberOfShip = 7;
        for (int i = 1; i <= NumberOfShip; i++) {
            //TODO переделать под ExecutorService
            new Thread(new Ship("Ship №" + i,i)).start();
            TimeUnit.MILLISECONDS.sleep(400);
        }
    }
}
