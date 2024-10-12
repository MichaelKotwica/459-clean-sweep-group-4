package com.group4;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int floorPlanLength = 3;
        int floorPlanWidth = 3;

        int startXTilePos = 2;
        int startYTilePos = 2;

        Random randomDirtAmount = new Random();

        Random randomTileType = new Random();

        int fixedDirtAmount = 25;

        Tile[][] floorPlan = new Tile[floorPlanLength+1][floorPlanWidth+1];

        // Create Empty Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                //System.out.println(i + ", " + j);

                int tileType = randomTileType.nextInt(3);
                //System.out.println(tileType);

                if (tileType == 0) {
                    floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
                } else if (tileType == 1) {
                    floorPlan[i][j] = new LowPileTile(null, null, null, null, i, j);
                } else {
                    floorPlan[i][j] = new HighPileTile(null, null, null, null, i, j);
                }

                //floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
                //System.out.println(floorPlan[i][j].toString());
            }
        }

        // Connect Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
        FloorPlan floorPlan = new FloorPlan(floorPlanLength, floorPlanWidth);
        Tile[][] floorPlanArr = floorPlan.createFloorPlan();
        floorPlan.connectFloorPlan();
        floorPlan.addDirt();
        floorPlan.printFloorPlan(floorPlanLength, floorPlanWidth, floorPlanArr);

        CleanSweep cleanSweep = new CleanSweep(startXTilePos,startYTilePos,false,floorPlanArr[startXTilePos][startYTilePos]);

        // Startup
        cleanSweep.startUp();
        //System.out.println("Starting Position:");
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        //System.out.println(cleanSweep.getCurrentTile().cleanTile);

        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Left (2,2) -> (1,2)
        System.out.println("\nTraversing Left...");
        cleanSweep.traverseLeft(floorPlan[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Left (1,2) -> (0,2)
        System.out.println("\nTraversing Left...");
        cleanSweep.traverseLeft(floorPlan[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Right (0,2) -> (1,2)
        System.out.println("\nTraversing Right...");
        cleanSweep.traverseRight(floorPlan[cleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Right (1,2) -> (2,2)
        System.out.println("\nTraversing Right...");
        cleanSweep.traverseRight(floorPlan[cleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Up (2,2) -> (2,1)
        System.out.println("\nTraversing Up...");
        cleanSweep.traverseUp(floorPlan[cleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse  Up (2,1) -> (2,0)
        System.out.println("\nTraversing Up...");
        cleanSweep.traverseUp(floorPlan[cleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Left (2,0) -> (1,0)
        System.out.println("\nTraversing Left...");
        cleanSweep.traverseLeft(floorPlan[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Down (1,0) -> (1,1)
        System.out.println("\nTraversing Down...");
        cleanSweep.traverseDown(floorPlan[cleanSweep.getXPos()][cleanSweep.getYPos()+1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        cleanSweep.shutDown();

        System.out.println("Floor after cleaning:");
        printFloorPlan(floorPlanLength, floorPlanWidth, floorPlan);
    }

   /*     public void returnToChargingStation() {
        System.out.println("Returning to starting charging station...");
        moveToPosition(0, 0); // Assuming (0, 0) is the charging station position
        emptyDirtContainer();
        System.out.println("Reached charging station. Dirt container emptied.");
    }

    private void moveToPosition(int targetX, int targetY) {
        // Simplified movement logic: moves to target position step-by-step.
        while (xPos != targetX || yPos != targetY) {
            if (xPos < targetX) traverseRight(floorPlan[xPos + 1][yPos]);
            else if (xPos > targetX) traverseLeft(floorPlan[xPos - 1][yPos]);
            if (yPos < targetY) traverseDown(floorPlan[xPos][yPos + 1]);
            else if (yPos > targetY) traverseUp(floorPlan[xPos][yPos - 1]);
        }
    }    */


    public static void printFloorPlan(int floorPlanLength, int floorPlanWidth, Tile[][] floorPlan) {
        // Print Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                System.out.println(floorPlan[i][j].toString());
            }
        }
    }

}
