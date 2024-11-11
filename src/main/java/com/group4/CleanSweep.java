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

    private static final double LOW_BATTERY_THRESHOLD = 50.0;
    private boolean returningToChargingStation = false;// Threshold for low battery level
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

    protected Tile findChargingStation(Tile start) {
        Tile target = null;
        List<Tile> visited = new ArrayList<Tile>();
        Queue<Tile> collection = new LinkedList<>();
        visited.add(start);
        finderHelper(start, visited, collection);
        target = visited.get(visited.size() - 1);
        if (target.getTypeStr() != "Charging Station") {
            return null;
        }
        return target;
    }

    protected void finderHelper(Tile node, List<Tile> visited, Queue<Tile> collection) {
        if (node != null) {

            if (node.getTop() != null && !visited.contains(node.getTop()) && node.getTop().traversable()) {
                collection.add(node.getTop());
                visited.add(node.getTop());
                if (node.getTop().getTypeStr() == "Charging Station") {
                    return;

                }

            }
            if (node.getLeft() != null && !visited.contains(node.getLeft()) && node.getLeft().traversable()) {
                collection.add(node.getLeft());
                visited.add(node.getLeft());
                if (node.getLeft().getTypeStr() == "Charging Station") {
                    return;

                }

            }
            if (node.getBottom() != null && !visited.contains(node.getBottom()) && node.getBottom().traversable()) {
                collection.add(node.getBottom());
                visited.add(node.getBottom());
                if (node.getBottom().getTypeStr() == "Charging Station") {
                    return;

                }

            }
            if (node.getRight() != null && !visited.contains(node.getRight()) && node.getRight().traversable()) {
                collection.add(node.getRight());
                visited.add(node.getRight());
                if (node.getRight().getTypeStr() == "Charging Station") {
                    return;

                }

            }
            collection.remove();
            finderHelper(collection.peek(), visited, collection);

        }
        return;
    }

    protected List<Tile> pathTo(Tile start) {
        List<Tile> visited = new ArrayList<Tile>();
        Queue<Tile> collection = new LinkedList<>();
        visited.add(start);
        finderHelper(start, visited, collection);
        Tile target = visited.get(visited.size()-1);

        for (int i = visited.size() - 1; i > 0; i--) {
            Boolean adjacent = visited.get(i-1).getTop() == target || visited.get(i-1).getBottom() == target || visited.get(i-1).getRight() == target || visited.get(i-1).getLeft() == target;
            if (!adjacent) {
                visited.set(i - 1, null);
            } else {
                target = visited.get(i-1);
            }

        }
        List<Tile> visitedClean = new ArrayList<Tile>();
        for (Tile node : visited) {
            if (node != null)
                visitedClean.add(node);
        }
        return visitedClean;
    }

    protected double calculateTravelCost(Tile start) {
        List<Tile> path = pathTo(start);
        double totalPower = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            totalPower += getSurfaceCost(path.get(i)) + getSurfaceCost(path.get(i + 1)) / 2.0;
        }
        return totalPower;
    }


    protected void consumeBattery(Tile destinationTile) {
        // Calculate the cost of moving to the destination tile
        double moveCost = (getSurfaceCost(currentTile) + getSurfaceCost(destinationTile)) / 2.0;

        // Check if the battery level is below the low battery threshold
        if (batteryLevel <= LOW_BATTERY_THRESHOLD) {
            if(!returningToChargingStation) {
                cleanSweepLogger.fatal("Battery level is low. Returning to charging station.");
                returningToChargingStation = true;
                returnToChargingStation();
            }
            return;
        }

        // Deduct the move cost from the battery
        batteryLevel -= moveCost;
        cleanSweepLogger.info("Battery level after move: {} units", batteryLevel);

        // If the destination tile has dirt, deduct the cleaning cost
        if (destinationTile.getDirtAmount() > 0) {
            double cleaningCost = getSurfaceCost(destinationTile);
            batteryLevel -= cleaningCost;
            cleanSweepLogger.info("Battery level after cleaning: {} units", batteryLevel);
        } else {
            cleanSweepLogger.info("No cleaning needed. Battery level: {} units", batteryLevel);
        }

        // Ensure battery level does not drop below zero
        if (batteryLevel < 0) {
            batteryLevel = 0;
        }
    }

    public boolean traverseLeft(Tile tile) {
        if (currentTile.getLeft() != null && currentTile.getLeft() == tile && tile.traversable()) {
            if (hasEnoughBattery(tile)) {
                consumeBattery(tile);/*
                if (batteryLevel <= LOW_BATTERY_THRESHOLD) {
                    returnToChargingStation();
                    return false;
                }*/
                this.currentTile = tile;
                this.xPos--;
                cleanSweepLogger.info("Traversing Left");
                printPos();
                showBatteryPercentage();
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
                consumeBattery(tile);/*
                if (batteryLevel <= LOW_BATTERY_THRESHOLD) {
                    returnToChargingStation();
                    return false;
                }*/
                this.currentTile = tile;
                this.xPos++;
                cleanSweepLogger.info("Traversing Right");
                printPos();
                showBatteryPercentage();
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
                consumeBattery(tile);/*
                if (batteryLevel <= LOW_BATTERY_THRESHOLD) {
                    returnToChargingStation();
                    return false;
                }*/
                this.currentTile = tile;
                this.yPos--;
                cleanSweepLogger.info("Traversing Up");
                printPos();
                showBatteryPercentage();
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
                consumeBattery(tile);/*
                if (batteryLevel <= LOW_BATTERY_THRESHOLD) {
                    returnToChargingStation();
                    return false;
                }*/
                this.currentTile = tile;
                this.yPos++;
                cleanSweepLogger.info("Traversing Down");
                printPos();
                showBatteryPercentage();
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

    private List<Tile> shortenPath(List<Tile> traversalList, Tile goal) {
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

    private void followPathtochargingstation(List<Tile> tiles, Tile goal) {
        for (Tile tile : tiles) {
            if (tile == null) continue; // Skip null tiles if any

            if (tile.equals(currentTile)) continue; // Skip if already on this tile

            // Try to move to the tile in the correct direction
            if (currentTile.getBottom() == tile && tile.traversable()) {
                cleanSweepLogger.info("Attempting to move down to tile: ({}, {})", tile.xPos, tile.yPos);
                if (!traverseDown(tile)) break; // Stop if movement fails
            } else if (currentTile.getRight() == tile && tile.traversable()) {
                cleanSweepLogger.info("Attempting to move right to tile: ({}, {})", tile.xPos, tile.yPos);
                if (!traverseRight(tile)) break; // Stop if movement fails
            } else if (currentTile.getTop() == tile && tile.traversable()) {
                cleanSweepLogger.info("Attempting to move up to tile: ({}, {})", tile.xPos, tile.yPos);
                if (!traverseUp(tile)) break; // Stop if movement fails
            } else if (currentTile.getLeft() == tile && tile.traversable()) {
                cleanSweepLogger.info("Attempting to move left to tile: ({}, {})", tile.xPos, tile.yPos);
                if (!traverseLeft(tile)) break; // Stop if movement fails
            } else {
                cleanSweepLogger.error("Tile at ({}, {}) is not traversable or not in the correct direction.", tile.xPos, tile.yPos);
                continue; // Skip to the next tile
            }

            // Check if we have reached the goal
            if (currentTile.equals(goal)) {
                cleanSweepLogger.info("Successfully reached the charging station at ({}, {}).", goal.xPos, goal.yPos);
                return;
            }
        }

        // If we exit the loop without reaching the goal, log an error
        cleanSweepLogger.error("Failed to reach the charging station after following the path.");
    }


    private void returnToChargingStation() {
        Tile chargingStation = findChargingStation(currentTile);
        if (chargingStation == null) {
            cleanSweepLogger.info("There is no charging station on this floor");
            return;
        }
        List<Tile> guide = pathTo(currentTile);
        Tile preReturnTile = currentTile;
        List<Tile> returnPath = guide.reversed();

        /*
        if(findChargingStation(currentTile) == null){
            cleanSweepLogger.info("There is no charging station on this floor");
            return;
        }
        List<Tile> guide = pathTo(currentTile);
        cleanSweepLogger.debug("Path to charging station: {}", guide);
        followPathtochargingstation(guide, findChargingStation(currentTile));*/

        //followPathtochargingstation(guide, findChargingStation(currentTile));


        if (guide == null || guide.isEmpty()) {
            cleanSweepLogger.error("Failed to calculate a path to the charging station.");
            return;
        }
        cleanSweepLogger.debug("Path to charging station: " + guide);
        cleanSweepLogger.info("Returning to charging station...");
        followPathtochargingstation(guide, chargingStation);
        if (currentTile.getXPos() == chargingStation.getXPos() && currentTile.getYPos() == chargingStation.getYPos()) {
            if (currentTile instanceof ChargingStation) {
                //batteryLevel = MAX_BATTERY; // Recharge the battery
                charge();
                cleanSweepLogger.info("Recharged to full battery capacity: {} units.", batteryLevel);
                emptyDirtContainer();
                followPath(returnPath, preReturnTile);
            }
        } else {
            cleanSweepLogger.error("Failed to reach the charging station.");
        }
    }
}
