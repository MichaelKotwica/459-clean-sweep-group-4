package com.group4;


public class Main {


    public static void main(String[] args) {


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

        //dfs(sampleFloorPlanCleanSweep, sampleFloorPlanArr, sampleFloorPlan);
        DepthFirstSearch dfs = new DepthFirstSearch();
        dfs.traverse(sampleFloorPlanCleanSweep.getTile(), sampleFloorPlanCleanSweep, sampleFloorPlanArr);

        sampleFloorPlanCleanSweep.shutDown();

        System.out.println("\n========================================================================");
        System.out.println("                 Floor Plan:              ");
        sampleFloorPlan.representFloorPlan();
        System.out.println("\n========================================================================");
        System.out.println("                Floor Plan Dirt Remaining After Cleaning:               ");
        sampleFloorPlan.representDirt();
        System.out.println("\n========================================================================");
        System.out.println("                    Clean Sweep Info After Cleaning:                      ");
        System.out.println("  Powered On: " + sampleFloorPlanCleanSweep.powerOn +
                "   Dirt Capacity: " + sampleFloorPlanCleanSweep.getDirtCapacity() + "/" + sampleFloorPlanCleanSweep.MAX_CAPACITY +
                "   Battery Level: " + sampleFloorPlanCleanSweep.getBatteryLevel() + "/" + sampleFloorPlanCleanSweep.MAX_BATTERY
        );
    }

}