package com.group4;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int floorPlanLength = 3;
        int floorPlanWidth = 3;

        int startXTilePos = 2;
        int startYTilePos = 2;

        Random randomDirtAmount = new Random();

        Tile[][] floorPlan = new Tile[floorPlanLength+1][floorPlanWidth+1];

        // Create Empty Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                //System.out.println(i + ", " + j);
                floorPlan[i][j] = new Tile(null, null, null, null, i, j);

                //System.out.println(floorPlan[i][j].toString());
            }
        }

        // Connect Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {

                // Connect Next Right
                if((floorPlan[i+1][j]) != null) {
                    //System.out.println("test");
                    floorPlan[i][j].setRightNext(floorPlan[i+1][j]);

                    // Connect Next Left
                    if (floorPlan[i][j].getRight().xPos - 1 == floorPlan[i][j].xPos) {
                        //    System.out.println("test");
                        floorPlan[i+1][j].setLeftNext(floorPlan[i][j]);
                    }
                }

                // Connect Next Bottom
                if((floorPlan[i][j+1]) != null) {
                    //    System.out.println("test");
                    floorPlan[i][j].setBottomNext(floorPlan[i][j+1]);

                    // Connect Next Top
                    if (floorPlan[i][j].getBottom().yPos - 1 == floorPlan[i][j].yPos) {
                        //            System.out.println("test");
                        floorPlan[i][j+1].setTopNext(floorPlan[i][j]);
                    }
                }
            }
        }

        // Add Dirt to Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                int dirtAmount = randomDirtAmount.nextInt(10);
                floorPlan[i][j].setDirtAmount(dirtAmount);
            }
        }

        //System.out.println(floorPlan[0][0]);

        //      printFloorPlan(floorPlanLength, floorPlanWidth, floorPlan);

        CleanSweep cleanSweep = new CleanSweep(startXTilePos,startYTilePos,false,floorPlan[startXTilePos][startYTilePos]);

        cleanSweep.startUp();

        System.out.println("Starting Position:");
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        while(!cleanSweep.getCurrentTile().cleanTile) {
            cleanSweep.clean(cleanSweep.getCurrentTile());
        }

        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        cleanSweep.shutDown();

        // Traverse Left (2,2) -> (1,2)
        System.out.println("Traversing Left...");
        cleanSweep.traverseLeft(floorPlan[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());


        // Traverse Left (1,2) -> (0,2)
        System.out.println("Traversing Left...");
        cleanSweep.traverseLeft(floorPlan[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Right (0,2) -> (1,2)
        System.out.println("Traversing Right...");
        cleanSweep.traverseRight(floorPlan[cleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Right (1,2) -> (2,2)
        System.out.println("Traversing Right...");
        cleanSweep.traverseRight(floorPlan[cleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Up (2,2) -> (2,1)
        System.out.println("Traversing Up...");
        cleanSweep.traverseUp(floorPlan[cleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());


        // Traverse  Up (2,1) -> (2,0)
        System.out.println("Traversing Up...");
        cleanSweep.traverseUp(floorPlan[cleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Left (2,0) -> (1,0)
        System.out.println("Traversing Left...");
        cleanSweep.traverseLeft(floorPlan[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Down (1,0) -> (1,1)
        System.out.println("Traversing Down...");
        cleanSweep.traverseDown(floorPlan[cleanSweep.getXPos()][cleanSweep.getYPos()+1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

    }

    public static void printFloorPlan(int floorPlanLength, int floorPlanWidth, Tile[][] floorPlan) {
        // Print Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {

                System.out.println(floorPlan[i][j].toString());

            }
        }
    }

}