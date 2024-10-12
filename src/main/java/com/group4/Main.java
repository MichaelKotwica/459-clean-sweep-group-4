package com.group4;

public class Main {
    public static void main(String[] args) {

        int floorPlanLength = 3;
        int floorPlanWidth = 3;

        int startXTilePos = 2;
        int startYTilePos = 2;

        FloorPlan floorPlan = new FloorPlan(floorPlanLength, floorPlanWidth);
        Tile[][] floorPlanArr = floorPlan.createFloorPlan();
        floorPlan.connectFloorPlan();
        floorPlan.addDirt();
        floorPlan.printFloorPlan(floorPlanLength, floorPlanWidth, floorPlanArr);

        CleanSweep cleanSweep = new CleanSweep(startXTilePos,startYTilePos,false,floorPlanArr[startXTilePos][startYTilePos]);

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

        // Traverse Left (2,2) -> (1,2)
        System.out.println("Traversing Left...");
        cleanSweep.traverseLeft(floorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());


        // Traverse Left (1,2) -> (0,2)
        System.out.println("Traversing Left...");
        cleanSweep.traverseLeft(floorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Right (0,2) -> (1,2)
        System.out.println("Traversing Right...");
        cleanSweep.traverseRight(floorPlanArr[cleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Right (1,2) -> (2,2)
        System.out.println("Traversing Right...");
        cleanSweep.traverseRight(floorPlanArr[cleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Up (2,2) -> (2,1)
        System.out.println("Traversing Up...");
        cleanSweep.traverseUp(floorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());


        // Traverse  Up (2,1) -> (2,0)
        System.out.println("Traversing Up...");
        cleanSweep.traverseUp(floorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Left (2,0) -> (1,0)
        System.out.println("Traversing Left...");
        cleanSweep.traverseLeft(floorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Down (1,0) -> (1,1)
        System.out.println("Traversing Down...");
        cleanSweep.traverseDown(floorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()+1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();
        // Check dirt per tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        cleanSweep.shutDown();

    }

}