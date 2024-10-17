package com.group4;

public class Main {
    public static void main(String[] args) {

        int floorPlanLength = 5;
        int floorPlanWidth = 5;

        int startXTilePos = 2;
        int startYTilePos = 2;

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
        cleanSweep.traverseLeft(floorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Left (1,2) -> (0,2)
        System.out.println("\nTraversing Left...");
        cleanSweep.traverseLeft(floorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Right (0,2) -> (1,2)
        System.out.println("\nTraversing Right...");
        cleanSweep.traverseRight(floorPlanArr[cleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Right (1,2) -> (2,2)
        System.out.println("\nTraversing Right...");
        cleanSweep.traverseRight(floorPlanArr[cleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Up (2,2) -> (2,1)
        System.out.println("\nTraversing Up...");
        cleanSweep.traverseUp(floorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse  Up (2,1) -> (2,0)
        System.out.println("\nTraversing Up...");
        cleanSweep.traverseUp(floorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Left (2,0) -> (1,0)
        System.out.println("\nTraversing Left...");
        cleanSweep.traverseLeft(floorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Down (1,0) -> (1,1)
        System.out.println("\nTraversing Down...");
        cleanSweep.traverseDown(floorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()+1]);
        cleanSweep.printPos();
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        cleanSweep.shutDown();

        System.out.println("Floor after cleaning:");
        floorPlan.printFloorPlan(floorPlanLength, floorPlanWidth, floorPlanArr);
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

}