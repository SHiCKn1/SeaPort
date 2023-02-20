package com.prokopovich.entity;

import com.prokopovich.Port;
import org.apache.logging.log4j.Level;

public class Ship implements Runnable {
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

        Port.logger.log(Level.INFO, "The ship " + shipName + " sailed into the port.");
        try {
            Port.SEMAPHORE.acquire();

            int berthNumber = -1;


            synchronized (Port.BERTHS) {
                for (int i = 0; i < 5; i++)
                    if (!Port.BERTHS[i]) {
                        Port.BERTHS[i] = true;
                        berthNumber = i;
                        Port.logger.log(Level.INFO, "The ship " + shipName + " has berthed " + berthNumber + ".");
                        break;
                    }
            }

            Thread.sleep(5000);

            synchronized (Port.BERTHS) {
                Port.BERTHS[berthNumber] = false;
            }


            Port.SEMAPHORE.release();
            Port.logger.log(Level.INFO, "The ship " + shipName + "  left the berthed");
        } catch (InterruptedException e) {
            //TODO написать warn
            Port.logger.log(Level.ERROR, e.getMessage());
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
