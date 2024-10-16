package com.group4;

public class CleanSweep {
    private int xPos;
    private int yPos;

    public boolean powerOn;

    private Tile currentTile;

    private double batterypercentage;

    private int dirtCapacity;
    private final int MAX_CAPACITY; // Max dirt capacity


    // Use a Stack to track traversals?

    public CleanSweep(int xPos, int yPos, boolean powerOn, Tile currentTile) {
        this.xPos = xPos;
        this.yPos = yPos;

        this.powerOn = powerOn;

        this.currentTile = currentTile;

        this.dirtCapacity = 0; // Start with an empty dirt container
        MAX_CAPACITY = 50;
    }

    public boolean traverseLeft(Tile tile) {
        if (currentTile.getLeft() == tile && tile.traversible()) {
            this.currentTile = tile;
            this.xPos--;
            return true;
        } else return false;
    }

    public boolean traverseRight(Tile tile) {
        if (currentTile.getRight() == tile && tile.traversible()) {
            this.currentTile = tile;
            this.xPos++;
            return true;
        } else return false;
    }

    public boolean traverseUp(Tile tile) {
        if (currentTile.getTop() == tile && tile.traversible()) {
            this.currentTile = tile;
            this.yPos--;
            return true;
        } else return false;
    }

    public boolean traverseDown(Tile tile) {
        if (currentTile.getBottom() == tile && tile.traversible()) {
            this.currentTile = tile;
            this.yPos++;
            return true;
        } else return false;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    /*

    Yash's Implementation:

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

    public void clean(Tile tile) {

/*

    Janki's Implementation

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
*/


/*

        Michael's Initial Implementation

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
*/

        if (dirtCapacity < MAX_CAPACITY) {
            if (!tile.cleanTile) {
                int dirtToCollect = Math.min(MAX_CAPACITY - dirtCapacity, tile.getDirtAmount());
                while (dirtToCollect > 0) {
                    //System.out.println("before set:" + tile.getDirtAmount());
                    dirtCapacity++;
                    tile.setDirtAmount(tile.getDirtAmount() - 1);
                    System.out.println("Cleaned 1 dirt. Current Capacity: " + dirtCapacity + "/" + MAX_CAPACITY);
                    dirtToCollect--;
                    //System.out.println("after set:" + tile.getDirtAmount());
                }
                if (tile.getDirtAmount() == 0) {
                    tile.cleanTile = true;
                } else {
                    System.out.println("Dirt container is full! Cannot clean more until emptied.");
                }

            } else {
                System.out.println("This is a clean tile.");
            }

        } else {
            System.out.println("Dirt container is full! Cannot clean more until emptied.");
        }

    }


    public void showBatteryPercentage() {
        System.out.println("Battery Percentage: " + batterypercentage + "%");
    }
    public void shutDown() {
        System.out.println("Shutting Down...\n");
        powerOn = false;
    }

    public void startUp() {
        System.out.println("\nPowering on...");
        powerOn = true;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void printPos() {
        System.out.println("Current Position: (" + getXPos() + ", " + getYPos() + ")");
    }

    public int getDirtCapacity() {

        return dirtCapacity;
    }

    public void emptyDirtContainer() {
        dirtCapacity = 0;
        System.out.println("Dirt container emptied.");
    }

}
