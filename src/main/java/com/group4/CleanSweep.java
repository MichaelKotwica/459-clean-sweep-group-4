package com.group4;

public class CleanSweep {
    private int xPos;
    private int yPos;

    public boolean powerOn;

    private Tile currentTile;

    private double batterypercentage;

    // Use a Stack to track traversals?

    public CleanSweep(int xPos, int yPos, boolean powerOn, Tile currentTile) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.powerOn = powerOn;
        this.currentTile = currentTile;
    }

    public boolean traverseLeft(Tile tile) {
        if (currentTile.getLeft() == tile) {
            this.currentTile = tile;
            this.xPos--;
            return true;
        } else return false;
    }

    public boolean traverseRight(Tile tile) {
        if (currentTile.getRight() == tile) {
            this.currentTile = tile;
            this.xPos++;
            return true;
        } else return false;
    }

    public boolean traverseUp(Tile tile) {
        if (currentTile.getTop() == tile) {
            this.currentTile = tile;
            this.yPos--;
            return true;
        } else return false;
    }

    public boolean traverseDown(Tile tile) {
        if (currentTile.getBottom() == tile) {
            this.currentTile = tile;
            this.yPos++;
            return true;
        } else return false;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public boolean clean(Tile tile) {
        if (!tile.cleanTile) {
            tile.setDirtAmount(tile.getDirtAmount() - 1); // Clean 1 unit of dirt

            if (tile.getDirtAmount() <= 0) {
                tile.cleanTile = true;
                System.out.println("Done Cleaning! :)");
                return true;
            } else {
                System.out.println("Cleaning...");
                return false;
            }
        }
        System.out.println("Already Clean! :D");
        return true;
    }


    public void showBatteryPercentage() {
        System.out.println("Battery Percentage: " + batterypercentage + "%");
    }
    public void shutDown() {
        System.out.println("Shutting Down...");
        powerOn = false;
    }

    public void startUp() {
        System.out.println("Powering on...");
        powerOn = true;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void printPos() {
        System.out.println("(" + getXPos() + ", " + getYPos() + ")");
    }
}