package com.group4;


import java.util.*;

public class Main {

    static Set<String> visited = new HashSet<>();

    public static void main(String[] args) {
/*
        int floorPlanLength = 5;
        int floorPlanWidth = 5;

        int startXTilePos = 2;
        int startYTilePos = 2;

        FloorPlan floorPlan = new FloorPlan(floorPlanLength, floorPlanWidth);
        Tile[][] floorPlanArr = floorPlan.createFloorPlan();
        floorPlan.connectFloorPlan();
        floorPlan.addDirt();
        floorPlan.printFloorPlan();

        CleanSweep cleanSweep = new CleanSweep(startXTilePos,startYTilePos,false,floorPlanArr[startXTilePos][startYTilePos]);

        // Startup
        cleanSweep.startUp();
        //System.out.println("Starting Position:");
        cleanSweep.printPos();
        System.out.println("Current Tile type: " + cleanSweep.getCurrentTile().getTypeStr());
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Left (2,2) -> (1,2)
        System.out.println("\nTraversing Left...");
        cleanSweep.traverseLeft(floorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        System.out.println("Current Tile type: " + cleanSweep.getCurrentTile().getTypeStr());
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Left (1,2) -> (0,2)
        System.out.println("\nTraversing Left...");
        cleanSweep.traverseLeft(floorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        System.out.println("Current Tile type: " + cleanSweep.getCurrentTile().getTypeStr());
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Right (0,2) -> (1,2)
        System.out.println("\nTraversing Right...");
        cleanSweep.traverseRight(floorPlanArr[cleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        System.out.println("Current Tile type: " + cleanSweep.getCurrentTile().getTypeStr());
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Right (1,2) -> (2,2)
        System.out.println("\nTraversing Right...");
        cleanSweep.traverseRight(floorPlanArr[cleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        System.out.println("Current Tile type: " + cleanSweep.getCurrentTile().getTypeStr());
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Up (2,2) -> (2,1)
        System.out.println("\nTraversing Up...");
        cleanSweep.traverseUp(floorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        cleanSweep.printPos();
        System.out.println("Current Tile type:" + cleanSweep.getCurrentTile().getTypeStr());
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse  Up (2,1) -> (2,0)
        System.out.println("\nTraversing Up...");
        cleanSweep.traverseUp(floorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        cleanSweep.printPos();
        System.out.println("Current Tile type: " + cleanSweep.getCurrentTile().getTypeStr());
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Left (2,0) -> (1,0)
        System.out.println("\nTraversing Left...");
        cleanSweep.traverseLeft(floorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        cleanSweep.printPos();
        System.out.println("Current Tile type: " + cleanSweep.getCurrentTile().getTypeStr());
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        // Traverse Down (1,0) -> (1,1)
        System.out.println("\nTraversing Down...");
        cleanSweep.traverseDown(floorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()+1]);
        cleanSweep.printPos();
        System.out.println("Current Tile type: " + cleanSweep.getCurrentTile().getTypeStr());
        cleanSweep.showBatteryPercentage();

        // Clean current tile
        System.out.println("Tile Dirt Amount Before Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());
        cleanSweep.clean(cleanSweep.getCurrentTile());
        System.out.println("Tile Dirt Amount After Cleaning: " + cleanSweep.getCurrentTile().getDirtAmount());

        cleanSweep.shutDown();

        System.out.println("Floor after cleaning:");
        floorPlan.printFloorPlan();

        */

        int sampleFloorPlanLength = 14;
        int sampleFloorPlanWidth = 10;

        int sampleFloorPlanStartXTilePos = 7;
        int sampleFloorPlanStartYTilePos = 3;

        FloorPlan sampleFloorPlan = new FloorPlan(sampleFloorPlanLength, sampleFloorPlanWidth);

        Tile[][] sampleFloorPlanArr = sampleFloorPlan.createSampleFloorPlan();

        sampleFloorPlan.connectFloorPlan();


        //sampleFloorPlan.printFloorPlan();

        System.out.println("\n========================================================================");
        System.out.println("                 Floor Plan:              ");

        sampleFloorPlan.representFloorPlan();

        sampleFloorPlan.addDirt();

        System.out.println("========================================================================");
        System.out.println("                       Dirt Amount Before Cleaning:                       ");
        sampleFloorPlan.representDirt();
        System.out.println("\n========================================================================");

        System.out.println("                             Adding Walls:                              \n");
        sampleFloorPlan.addSampleFloorPlanWalls();
        System.out.println("\n========================================================================");

        System.out.println("                           Clean Sweep Actions:                           ");
        CleanSweep sampleFloorPlanCleanSweep = new CleanSweep(sampleFloorPlanStartXTilePos, sampleFloorPlanStartYTilePos,
                false, sampleFloorPlanArr[sampleFloorPlanStartXTilePos][sampleFloorPlanStartYTilePos]);

        sampleFloorPlanCleanSweep.startUp();
        /*
        sampleFloorPlanCleanSweep.printPos();
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseUp(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()-1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseUp(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()-1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseUp(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()-1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseUp(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()-1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseUp(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()-1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseUp(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()-1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseUp(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()-1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseUp(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()-1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseUp(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()-1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseLeft(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()-1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseDown(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()][sampleFloorPlanCleanSweep.getYPos()+1]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());

        sampleFloorPlanCleanSweep.traverseRight(sampleFloorPlanArr[sampleFloorPlanCleanSweep.getXPos()+1][sampleFloorPlanCleanSweep.getYPos()]);
        System.out.println("Current Tile type: " + sampleFloorPlanCleanSweep.getTile().getTypeStr());
        sampleFloorPlanCleanSweep.printPos();
        sampleFloorPlanCleanSweep.clean(sampleFloorPlanCleanSweep.getTile());
        */

        //dfs(sampleFloorPlanCleanSweep, sampleFloorPlanArr, sampleFloorPlan);
        DepthFirstSearch dfs = new DepthFirstSearch();
        dfs.traverse(sampleFloorPlanCleanSweep.getTile(), sampleFloorPlanCleanSweep, sampleFloorPlan);

        sampleFloorPlanCleanSweep.shutDown();

        System.out.println("========================================================================");
        System.out.println("                Floor Plan Dirt Remaining After Cleaning:               ");
        sampleFloorPlan.representDirt();
        System.out.println("\n========================================================================");

        //sampleFloorPlan.representFloorPlan();

    }

   /*     public void returnToChargingStation() {
        System.out.println("Returning to starting charging station...");
        moveToPosition(0, 0); // Assuming (0, 0) is the charging station position
        emptyDirtContainer();
        System.out.println("Reached charging station. Dirt container emptied.");
    }*/

}