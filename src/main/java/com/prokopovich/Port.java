package com.prokopovich;

import com.prokopovich.action.PortManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Port {
    //TODO разнести процедуры и классы по пакетам

    public static final int NUMBER_OF_BERTHS = 5;
    public static final boolean[] BERTHS = new boolean[NUMBER_OF_BERTHS];
    public static Logger logger = LogManager.getLogger();
    public static final Semaphore SEMAPHORE = new Semaphore(NUMBER_OF_BERTHS, true);

    private int containerCapacity;
    private int amountOfContainer;

    public static void main(String[] args) {
        try {
            PortManager.startWork();
            //PortManager
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

}