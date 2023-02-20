package com.prokopovich.entity;

public class Ship1 {
    private String shipName;
    private int containerCapacity;
    private int amountOfContainer;

    public Ship1(String shipName, int containerCapacity) {
        this.shipName = shipName;
        this.containerCapacity = containerCapacity;
        this.amountOfContainer = 0;
    }

    public String getShipName() {
        return shipName;
    }

    public int getContainerСapacity() {
        return containerCapacity;
    }

    public int getAmountOfContainer() {
        return amountOfContainer;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public void setContainerСapacity(int containerCapacity) {
        this.containerCapacity = containerCapacity;
    }

    public void setAmountOfContainer(int amountOfContainer) {
        this.amountOfContainer = amountOfContainer;
    }
}
