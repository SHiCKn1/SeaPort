package com.prokopovich;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Port {
    //TODO разнести процедуры и классы по пакетам

    private static final int NUMBER_OF_BERTHS = 5;
    private static final boolean[] BERTHS = new boolean[NUMBER_OF_BERTHS];
    static Logger logger = LogManager.getLogger();


    private int containerCapacity;
    private int amountOfContainer;

    private static final Semaphore SEMAPHORE = new Semaphore(NUMBER_OF_BERTHS, true);

    public static void main(String[] args) throws InterruptedException {
        try {
            PortManager();
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    public static void PortManager() throws InterruptedException {
        for (int i = 1; i <= 7; i++) {
            //TODO переделать под ExecutorService
            new Thread(new Ship("Ship №" + i,i)).start();
            TimeUnit.MILLISECONDS.sleep(400);
        }
    }


    public static class Ship implements Runnable {
        private String shipName;
        private int containerCapacity;
        private int amountOfContainer;



        public Ship(String shipName, int containerCapacity) {
            this.shipName = shipName;
            this.containerCapacity = containerCapacity;
            this.amountOfContainer = 0;
        }

        @Override
        public void run() {

            logger.log(Level.INFO, "The ship " +  shipName + " sailed into the port.");
            try {
                SEMAPHORE.acquire();

                int berthNumber = -1;


                synchronized (BERTHS){
                    for (int i = 0; i < 5; i++)
                        if (!BERTHS[i]) {
                            BERTHS[i] = true;
                            berthNumber = i;
                            logger.log(Level.INFO, "The ship " +  shipName + " has berthed " + berthNumber + ".");
                            break;
                        }
                }

                Thread.sleep(5000);

                synchronized (BERTHS) {
                    BERTHS[berthNumber] = false;
                }


                SEMAPHORE.release();
                logger.log(Level.INFO, "The ship " +  shipName + "  left the berthed");
            } catch (InterruptedException e) {
                //TODO написать warn
                logger.log(Level.ERROR, e.getMessage());
            }

        }

        public String getShipName() {
            return shipName;
        }

        public int getContainerCapacity() {
            return containerCapacity;
        }

        public int getAmountOfContainer() {
            return amountOfContainer;
        }

        public void setShipName(String shipName) {
            this.shipName = shipName;
        }

        public void setContainerCapacity(int containerCapacity) {
            this.containerCapacity = containerCapacity;
        }

        public void setAmountOfContainer(int amountOfContainer) {
            this.amountOfContainer = amountOfContainer;
        }
    }

}