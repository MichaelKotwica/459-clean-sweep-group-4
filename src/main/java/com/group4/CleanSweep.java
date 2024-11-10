package com.group4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class CleanSweep {
    private int xPos;
    private int yPos;

    public boolean powerOn;

    private Tile currentTile;
    private double batteryLevel;
    private int dirtCapacity;

    private static final Logger cleanSweepLogger = LogManager.getLogger(CleanSweep.class.getName());

    protected final int MAX_CAPACITY = 50; // Max dirt capacity
    protected final double MAX_BATTERY = 250; // Maximum battery level
    private final double BARE_FLOOR_COST = 1.0;
    private final double LOW_PILE_COST = 2.0;
    private final double HIGH_PILE_COST = 3.0;

    public CleanSweep(int xPos, int yPos, boolean powerOn, Tile currentTile) {
        this.xPos = xPos;
        this.yPos = yPos;

        this.powerOn = powerOn;

        this.currentTile = currentTile;

        this.dirtCapacity = 0; // Start with an empty dirt container
        this.batteryLevel = MAX_BATTERY; // Initialize battery level

        cleanSweepLogger.info("Created Clean Sweep at ({},{})", xPos, yPos);
    }

    protected double getSurfaceCost(Tile tile) {
        if (tile instanceof BareFloorTile) return BARE_FLOOR_COST;
        else if (tile instanceof LowPileTile) return LOW_PILE_COST;
        else if (tile instanceof HighPileTile) return HIGH_PILE_COST;
        return BARE_FLOOR_COST; // Default to bare floor if type unknown
    }

    protected boolean hasEnoughBattery(Tile destinationTile) {
        double moveCost = (getSurfaceCost(currentTile) + getSurfaceCost(destinationTile)) / 2.0;
        double cleaningCost = getSurfaceCost(destinationTile);
        return batteryLevel >= (moveCost + cleaningCost);
    }


    protected void consumeBattery(Tile destinationTile) {
        double moveCost = (getSurfaceCost(currentTile) + getSurfaceCost(destinationTile)) / 2.0;
        double cleaningCost = getSurfaceCost(destinationTile);
        batteryLevel -= moveCost;
        cleanSweepLogger.info("Battery level after move: {}", batteryLevel);
        if (destinationTile.getDirtAmount() > 0) {
            cleaningCost = getSurfaceCost(destinationTile);
            batteryLevel -= cleaningCost;
            cleanSweepLogger.info("Battery level after cleaning: {}", batteryLevel);
        } else {
            cleanSweepLogger.info("No cleaning needed. Battery level: {}", batteryLevel);
        }
    }

    public boolean traverseLeft(Tile tile) {
        if (currentTile.getLeft() != null && currentTile.getLeft() == tile && tile.traversable()) {
            if (hasEnoughBattery(tile)) {
                consumeBattery(tile);
                this.currentTile = tile;
                this.xPos--;
                cleanSweepLogger.info("Traversing Left");
                printPos();
                return true;
            } else {
                cleanSweepLogger.fatal("Not enough battery to move left.");
                returnToChargingStation();
                return false;
            }
        } else {
            cleanSweepLogger.warn("Left tile ({},{}) not traversable...", tile.xPos, tile.yPos);
            //avoid();
            return false;
        }
    }

    public boolean traverseRight(Tile tile) {
        if (currentTile.getRight() != null && currentTile.getRight() == tile && tile.traversable()) {
            if (hasEnoughBattery(tile)) {
                consumeBattery(tile);
                this.currentTile = tile;
                this.xPos++;
                cleanSweepLogger.info("Traversing Right");
                printPos();
                return true;
            } else {
                cleanSweepLogger.fatal("Not enough battery to move right.");
                returnToChargingStation();
                return false;
            }
        } else {
            cleanSweepLogger.warn("Right tile ({},{}) not traversable...", tile.xPos, tile.yPos);
            //avoid();
            return false;
        }
    }

    public boolean traverseUp(Tile tile) {
        if (currentTile.getTop() != null && currentTile.getTop() == tile && tile.traversable()) {
            if (hasEnoughBattery(tile)) {
                consumeBattery(tile);
                this.currentTile = tile;
                this.yPos--;
                cleanSweepLogger.info("Traversing Up");
                printPos();
                return true;
            } else {
                cleanSweepLogger.fatal("Not enough battery to move up.");
                returnToChargingStation();
                return false;
            }
        } else {
            cleanSweepLogger.warn("Above tile ({},{}) not traversable...", tile.xPos, tile.yPos);
            //avoid();
            return false;
        }
    }

    public boolean traverseDown(Tile tile) {
        if (currentTile.getBottom() != null && currentTile.getBottom() == tile && tile.traversable()) {
            if (hasEnoughBattery(tile)) {
                consumeBattery(tile);
                this.currentTile = tile;
                this.yPos++;
                cleanSweepLogger.info("Traversing Down");
                printPos();
                return true;
            } else {
                cleanSweepLogger.fatal("Not enough battery to move down.");
                returnToChargingStation();
                return false;
            }
        } else {
            cleanSweepLogger.warn("Below tile ({},{}) not traversable...", tile.xPos, tile.yPos);
            //avoid();
            return false;
        }
    }

    public void moveToPosition(int targetX, int targetY, Tile[][] floorPlanArr) {
        // Simplified movement logic: moves to target position step-by-step.

        if (xPos != targetX || yPos != targetY) {

            if (getTile().getRight() != null && xPos < targetX && getTile().getRight().traversable()) {
                traverseRight(currentTile.getRight());
                //clean(currentTile);
                currentTile.setDirtAmount(0);
            } else if (getTile().getLeft() != null && xPos > targetX && getTile().getLeft().traversable()) {
                traverseLeft(currentTile.getLeft());
                //clean(currentTile);
                currentTile.setDirtAmount(0);
            } else if (getTile().getBottom() != null && yPos < targetY && getTile().getBottom().traversable()) {
                traverseDown(currentTile.getBottom());
                //clean(currentTile);
                currentTile.setDirtAmount(0);
            } else if (getTile().getTop() != null && yPos > targetY && getTile().getTop().traversable()) {
                traverseUp(currentTile.getTop());
                //clean(currentTile);
                currentTile.setDirtAmount(0);
            }
            /* else {

                                          BRUTE FORCE
                if (!(xPos < targetX)) {
                    if (getTile().getRight() != null && getTile().getRight().traversable()) {
                        traverseRight(currentTile.getRight());
                    }

                    if (getTile().getBottom() != null && yPos < targetY && getTile().getBottom().traversable()) {
                        traverseDown(currentTile.getBottom());
                    }
                }

            }*/

            cleanSweepLogger.debug("Target Position: ({},{})", targetX, targetY);
            List<Tile> traversalList = pathToNonAdjTile(floorPlanArr[xPos][yPos], floorPlanArr[targetX][targetY]);
            List<Tile> shortTraversalList = shortenPath(traversalList, floorPlanArr[targetX][targetY]);

            //cleanSweepLogger.debug(Arrays.toString(traversalList.toArray()));
            cleanSweepLogger.debug(Arrays.toString(shortTraversalList.toArray()));

            //followPath(traversalList, floorPlanArr[targetX][targetY]);
            followPath(shortTraversalList, floorPlanArr[targetX][targetY]);
        }
    }

    protected List<Tile> pathToNonAdjTile(Tile start, Tile goal) {
        List<Tile> visited = new ArrayList<Tile>();
        Queue<Tile> collection = new LinkedList<>();
        visited.add(start);
        finderHelperNonAdjTile(start, goal, visited, collection);
        int xCoord = visited.get(visited.size() - 1).xPos;
        int yCoord = visited.get(visited.size() - 1).yPos;

        for (int i = visited.size() - 1; i > 0; i--) {
            if (Math.abs(xCoord - visited.get(i - 1).xPos) + Math.abs(yCoord - visited.get(i - 1).yPos) > 1) {
                visited.set(i - 1, null);
            } else {
                xCoord = visited.get(i - 1).xPos;
                yCoord = visited.get(i - 1).yPos;
            }

        }
        List<Tile> visitedClean = new ArrayList<Tile>();
        for (Tile node : visited) {
            if (node != null)
                visitedClean.add(node);
        }
        return visitedClean;
    }

    protected void finderHelperNonAdjTile(Tile node, Tile goal, List<Tile> visited, Queue<Tile> collection) {
        if (node != null) {
            if (node.getTop() != null && !visited.contains(node.getTop()) && node.getTop().traversable()) {
                collection.add(node.getTop());
                visited.add(node.getTop());
                if (node == goal) {     // check if node is the adjacent tile
                    return;

                }

            }
            if (node.getLeft() != null && !visited.contains(node.getLeft()) && node.getLeft().traversable()) {
                collection.add(node.getLeft());
                visited.add(node.getLeft());
                if (node == goal) {
                    return;

                }

            }
            if (node.getBottom() != null && !visited.contains(node.getBottom()) && node.getBottom().traversable()) {
                collection.add(node.getBottom());
                visited.add(node.getBottom());
                if (node == goal) {
                    return;

                }

            }
            if (node.getRight() != null && !visited.contains(node.getRight()) && node.getRight().traversable()) { // Change if statement
                collection.add(node.getRight());
                visited.add(node.getRight());
                if (node == goal) {
                    return;

                }

            }
            collection.remove();
            finderHelperNonAdjTile(collection.peek(), goal, visited, collection);

        }
    }

    private List<Tile> shortenPath (List<Tile> traversalList, Tile goal) {
        List<Tile> shortPath = new ArrayList<Tile>();
        if (!traversalList.contains(goal)) {
            return shortPath;
        }
        for (Tile tile : traversalList) {
            if (tile == goal) {
                //shortPath.add(tile);
                break;
            }
            shortPath.add(tile);
        }
        return shortPath;
    }

    private void followPath(List<Tile> tiles, Tile goal) {
        for (Tile tile : tiles) {
            if (tile != currentTile && currentTile.bottomNext == tile && tile.traversable()) {
                traverseDown(tile);
                if (!currentTile.cleanTile) {
                    //clean(currentTile);
                    currentTile.setDirtAmount(0);
                }
                if (currentTile == goal) {
                    break;
                }
            }
            if (tile != currentTile && currentTile.rightNext == tile && tile.traversable()) {
                traverseRight(tile);
                if (!currentTile.cleanTile) {
                    //clean(currentTile);
                    currentTile.setDirtAmount(0);
                }
                if (currentTile == goal) {
                    break;
                }
            }
            if (tile != currentTile && currentTile.topNext == tile && tile.traversable()) {
                traverseUp(tile);
                if (!currentTile.cleanTile) {
                    //clean(currentTile);
                    currentTile.setDirtAmount(0);
                }
                if (currentTile == goal) {
                    break;
                }
            }
            if (tile != currentTile && currentTile.leftNext == tile && tile.traversable()) {
                traverseLeft(tile);
                if (!currentTile.cleanTile) {
                    //clean(currentTile);
                    currentTile.setDirtAmount(0);
                }
                if (currentTile == goal) {
                    break;
                }
            }
        }
    }

    public Tile getTile() {
        return currentTile;
    }

    public void charge() {
        if (Objects.equals(currentTile.getTypeStr(), "Charging Station")) {
            this.batteryLevel = MAX_BATTERY;
            cleanSweepLogger.info("Charging");
            return;
        }
        cleanSweepLogger.warn("The clean sweeper cannot be charged at this tile, please navigate to a charging station");
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

        if (dirtCapacity < MAX_CAPACITY) {
            if (!tile.cleanTile) {
                cleanSweepLogger.info("Found Dirty Tile");
                int dirtToCollect = Math.min(MAX_CAPACITY - dirtCapacity, tile.getDirtAmount());
                while (dirtToCollect > 0) {
                    //System.out.println("before set:" + tile.getDirtAmount());
                    dirtCapacity++;
                    tile.setDirtAmount(tile.getDirtAmount() - 1);
                    cleanSweepLogger.info("Cleaned 1 dirt. Current Capacity: {}/" + MAX_CAPACITY, dirtCapacity);
                    dirtToCollect--;
                    //System.out.println("after set:" + tile.getDirtAmount());
                }
                if (tile.getDirtAmount() == 0) {
                    tile.cleanTile = true;
                } else {
                    cleanSweepLogger.warn("Dirt container is full! Cannot clean more until emptied.");
                }

            } else {
                cleanSweepLogger.info("This is a clean tile.");
            }

        } else {
            cleanSweepLogger.warn("Dirt container is full! Cannot clean this tile.");
        }
    }

    public void avoid() {
        //System.out.println("in avoid");
        if (currentTile.getRight() != null && currentTile.getRight().traversable()) { // Try to traverse right to avoid obstacle
            cleanSweepLogger.info("Traversing right to avoid obstacle...");
            traverseRight(currentTile.getRight());
        } else if (currentTile.getBottom() != null && currentTile.getBottom().traversable()) { // Try to traverse down to avoid obstacle
            cleanSweepLogger.info("Traversing down to avoid obstacle...");
            traverseDown(currentTile.getBottom());
        } else if (currentTile.getLeft() != null && currentTile.getLeft().traversable()) { // Try to traverse left to avoid obstacle
            cleanSweepLogger.info("Traversing left to avoid obstacle...");
            traverseLeft(currentTile.getLeft());
        } else if (currentTile.getTop() != null && currentTile.getTop().traversable()) { // Try to traverse up to avoid obstacle
            cleanSweepLogger.info("Traversing up to avoid obstacle...");
            traverseUp(currentTile.getTop());
        } else {
            cleanSweepLogger.fatal("Cannot avoid obstacle(s)");
            shutDown();
        }
    }

    public void showBatteryPercentage() {
        cleanSweepLogger.info("Battery: {} units", batteryLevel);
    }

    public void shutDown() {
        cleanSweepLogger.info("Shutting Down...");
        powerOn = false;
    }

    public void startUp() {
        cleanSweepLogger.info("Powering on...");
        powerOn = true;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void printPos() {
        cleanSweepLogger.info("Current Position: ({}, {})", getXPos(), getYPos());
    }

    public int getDirtCapacity() {
        return dirtCapacity;
    }

    public void emptyDirtContainer() {
        dirtCapacity = 0;
        cleanSweepLogger.info("Dirt container emptied.");
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    private void returnToChargingStation() {
        cleanSweepLogger.info("Returning to charging station...");
        batteryLevel = MAX_BATTERY;
        cleanSweepLogger.info("Recharged to full battery capacity.");
    }
}
