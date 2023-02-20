package com.prokopovich;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

public class Port {

    private static final int NUMBER_OF_BERTHS = 5;
    private static final boolean[] BERTHS = new boolean[NUMBER_OF_BERTHS];
    static Logger logger = LogManager.getLogger();


    private int containerCapacity;
    private int amountOfContainer;

    private static final Semaphore SEMAPHORE = new Semaphore(NUMBER_OF_BERTHS, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 7; i++) {

            new Thread(new Ship("Ship №" + i,i)).start();
            Thread.sleep(400);
        }
    }

    public void PortManager() throws InterruptedException {

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
            System.out.printf("The ship %S sailed into the port.\n", shipName);
            try {
                SEMAPHORE.acquire();

                int berthNumber = -1;


                synchronized (BERTHS){
                    for (int i = 0; i < 5; i++)
                        if (!BERTHS[i]) {
                            BERTHS[i] = true;
                            berthNumber = i;
                            System.out.printf("The ship %S has berthed %d.\n", shipName, i);
                            break;
                        }
                }

                Thread.sleep(5000);

                synchronized (BERTHS) {
                    BERTHS[berthNumber] = false;
                }


                SEMAPHORE.release();
                System.out.printf("The ship %S  left the berthed.\n", shipName);
            } catch (InterruptedException e) {
                //TODO написать warn
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