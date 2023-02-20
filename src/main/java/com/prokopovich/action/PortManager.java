package com.prokopovich.action;

import com.prokopovich.Port;

import java.util.concurrent.TimeUnit;

public class PortManager {
    public static void startWork() throws InterruptedException {
        for (int i = 1; i <= 7; i++) {
            //TODO переделать под ExecutorService
            new Thread(new Port.Ship("Ship №" + i,i)).start();
            TimeUnit.MILLISECONDS.sleep(400);
        }
    }
}
