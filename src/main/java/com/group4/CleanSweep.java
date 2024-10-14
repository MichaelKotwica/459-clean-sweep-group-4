package com.group4;

public class CleanSweep {
    private int xPos;
    private int yPos;

    public boolean powerOn;

    private Tile currentTile;

    private double batterypercentage;
    private int dirtCapacity;
    private final int MAX_CAPACITY = 50; // Max dirt capacity


    // Use a Stack to track traversals?

    public CleanSweep(int xPos, int yPos, boolean powerOn, Tile currentTile) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.powerOn = powerOn;
        this.currentTile = currentTile;
        this.dirtCapacity = 0; // Start with an empty dirt container

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


    /*
        public boolean clean(Tile tile) {
        if (dirtCapacity >= MAX_CAPACITY) {
            System.out.println("Dirt container is full! Returning to the charging station.");
            returnToChargingStation();
            return false;
        }
    
        int dirtAmount = tile.getDirtAmount();
        if (dirtAmount > 0) {
            int dirtToCollect = Math.min(MAX_CAPACITY - dirtCapacity, dirtAmount);
            dirtCapacity += dirtToCollect;
            tile.setDirtAmount(dirtAmount - dirtToCollect);
            System.out.println("Cleaned " + dirtToCollect + " dirt. Current Capacity: " + dirtCapacity + "/" + MAX_CAPACITY);
        }
        
        if (tile.getDirtAmount() == 0) {
            tile.cleanTile = true;
            System.out.println("Tile fully cleaned.");
        }
    
        return true;
    }
*/
    
    public boolean clean(Tile tile) {
        if (dirtCapacity < MAX_CAPACITY) {
            int dirtAmount = tile.getDirtAmount();
            if (dirtAmount > 0) {
                // Calculate how much dirt can be picked up
                int dirtToCollect = Math.min(MAX_CAPACITY - dirtCapacity, dirtAmount);
                while (dirtToCollect > 0) {
                    dirtCapacity += 1;
                    tile.setDirtAmount(dirtAmount - 1);
                    dirtToCollect -= 1;
                    System.out.println("Cleaned 1 dirt. Current Capacity: " + dirtCapacity + "/" + MAX_CAPACITY);
                }
            }
            // If tile is fully clean
            if (tile.getDirtAmount() == 0) {
                tile.cleanTile = true;
                System.out.println("Tile fully cleaned.");
                return true;
            }
        } else {
            System.out.println("Dirt container is full! Cannot clean more until emptied.");
            return false;
        }


        /*if (!tile.cleanTile) {
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

         */

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

    public int getDirtCapacity() {

        return dirtCapacity;
    }

    public void emptyDirtContainer() {
        dirtCapacity = 0;
        System.out.println("Dirt container emptied.");
    }

}
