package com.group4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertFalse;

public class CleanSweepTest {

    int startXTilePos;
    int startYTilePos;

    int stuckStartXTilePos;
    int stuckStartYTilePos;

    int floorPlanLength;
    int floorPlanWidth;

    int stuckFloorPlanLength;
    int stuckFloorPlanWidth;

    CleanSweep cleanSweep;
    CleanSweep stuckCleanSweep;
    CleanSweep stairStuckCleanSweep;
    CleanSweep leftOutBoundCleanSweep;
    CleanSweep rightOutBoundCleanSweep;
    CleanSweep upOutBoundCleanSweep;
    CleanSweep downOutBoundCleanSweep;
    CleanSweep offCleanSweep;
    CleanSweep onCleanSweep;
    CleanSweep downAvoidanceCleanSweep;
    CleanSweep rightAvoidanceCleanSweep;

    FloorPlan floorPlan;
    Tile[][] floorPlanArr;

    FloorPlan bareFloorPlan;
    Tile[][] bareFloorPlanArr;

    FloorPlan stuckFloorPlan;
    Tile[][] stuckFloorPlanArr;

    FloorPlan stairStuckFloorPlan;
    Tile[][] stairStuckFloorPlanArr;

    FloorPlan bottomOpenFloorPlan;
    Tile[][] bottomOpenFloorPlanArr;

    FloorPlan rightOpenFloorPlan;
    Tile[][] rightOpenFloorPlanArr;


    @BeforeEach
    void setUp() {

        // 5 x 5 floor plan setup
        startXTilePos = 2;
        startYTilePos = 2;

        floorPlanLength = 5;
        floorPlanWidth = 5;

        // 3 x 3 stuck floor plan setup
        stuckStartXTilePos = 1;
        stuckStartYTilePos = 1;

        stuckFloorPlanLength = 3;
        stuckFloorPlanWidth = 3;

        floorPlan = new FloorPlan(floorPlanLength, floorPlanWidth);
        floorPlanArr = floorPlan.createFloorPlan();

        bareFloorPlan = floorPlan;
        bareFloorPlanArr = bareFloorPlan.createFloorPlan();

        // Make all Tiles Bare floor
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                bareFloorPlanArr[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }

        // Place Obstacle at 2,1 (Directly above clean sweep start position (2,2))
        // bareFloorPlanArr[2][1] = new Obstacle(null, null, null, null, 2, 1);

        // Place Stair at 2,3 (Directly above clean sweep start position (2,2))
        // bareFloorPlanArr[2][3] = new StairDeclineTile(null, null, null, null, 2, 3);

        // Place Stair at 3,2 (Directly to the right clean sweep start position (2,2))
        // bareFloorPlanArr[3][2] = new StairDeclineTile(null, null, null, null, 3, 2);

        floorPlan.connectFloorPlan();
        bareFloorPlan.connectFloorPlan();
        //bareFloorPlan.printFloorPlan();

        stuckFloorPlan = new FloorPlan(stuckFloorPlanLength, stuckFloorPlanWidth);
        stuckFloorPlanArr = stuckFloorPlan.createFloorPlan();

        // Make all stuck floor plan Tiles Bare floor
        for (int i = 0; i < stuckFloorPlanLength; i++) {
            for (int j = 0; j < stuckFloorPlanWidth; j++) {
                stuckFloorPlanArr[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }

        // Place Obstacles surrounding the clean sweep starting position
        stuckFloorPlanArr[1][0] = new Obstacle(null, null, null, null, 1, 0); // Above
        stuckFloorPlanArr[1][2] = new Obstacle(null, null, null, null, 1, 2); // Below
        stuckFloorPlanArr[0][1] = new Obstacle(null, null, null, null, 0, 1); // Left
        stuckFloorPlanArr[2][1] = new Obstacle(null, null, null, null, 2, 1); // Right

        stuckFloorPlan.connectFloorPlan();

        stairStuckFloorPlan = stuckFloorPlan;
        stairStuckFloorPlanArr = stuckFloorPlanArr;

        // Make all stair stuck floor plan Tiles Bare floor
        for (int i = 0; i < stuckFloorPlanLength; i++) {
            for (int j = 0; j < stuckFloorPlanWidth; j++) {
                stairStuckFloorPlanArr[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }

        // Place stairs / declines surrounding the clean sweep starting position
        stuckFloorPlanArr[1][0] = new StairDeclineTile(null, null, null, null, 1, 0); // Above
        stuckFloorPlanArr[1][2] = new StairDeclineTile(null, null, null, null, 1, 2); // Below
        stuckFloorPlanArr[0][1] = new StairDeclineTile(null, null, null, null, 0, 1); // Left
        stuckFloorPlanArr[2][1] = new StairDeclineTile(null, null, null, null, 2, 1); // Right
        stairStuckFloorPlan.connectFloorPlan();


        // Bottom Open Floor Plan
        bottomOpenFloorPlan = new FloorPlan(floorPlanLength, floorPlanWidth); // 5 x 5
        bottomOpenFloorPlanArr = bottomOpenFloorPlan.createFloorPlan();


        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                bottomOpenFloorPlanArr[i][j] = new BareFloorTile(null,null,null,null,i,j);
            }
        }

        // Place Obstacles surrounding the clean sweep starting position Except below
        bottomOpenFloorPlanArr[2][1] = new Obstacle(null, null, null, null, 2, 1); // Above
        // bottomOpenFloorPlanArr[2][3] = new Obstacle(null, null, null, null, 2, 3); // Below
        bottomOpenFloorPlanArr[1][2] = new Obstacle(null, null, null, null, 1, 2); // Left
        bottomOpenFloorPlanArr[3][2] = new Obstacle(null, null, null, null, 3, 2); // Right
        bottomOpenFloorPlan.connectFloorPlan();




        // Right Open Floor Plan
        rightOpenFloorPlan = new FloorPlan(floorPlanLength, floorPlanWidth); // 5 x 5
        rightOpenFloorPlanArr = rightOpenFloorPlan.createFloorPlan();


        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                rightOpenFloorPlanArr[i][j] = new BareFloorTile(null,null,null,null,i,j);
            }
        }

        // Place Obstacles surrounding the clean sweep starting position Except Right
        rightOpenFloorPlanArr[2][1] = new Obstacle(null, null, null, null, 2, 1); // Above
        rightOpenFloorPlanArr[2][3] = new Obstacle(null, null, null, null, 2, 3); // Below
        rightOpenFloorPlanArr[1][2] = new Obstacle(null, null, null, null, 1, 2); // Left
        // rightOpenFloorPlanArr[3][2] = new Obstacle(null, null, null, null, 3, 2); // Right
        rightOpenFloorPlan.connectFloorPlan();


        initCleanSweeps();
    }

    void initCleanSweeps() {
        cleanSweep = new CleanSweep(startXTilePos,startYTilePos,true,bareFloorPlanArr[startXTilePos][startYTilePos]);
        stuckCleanSweep = new CleanSweep(stuckStartXTilePos, stuckStartYTilePos, true, stuckFloorPlanArr[stuckStartXTilePos][stuckStartYTilePos]);
        stairStuckCleanSweep = new CleanSweep(stuckStartXTilePos, stuckStartYTilePos, true, stairStuckFloorPlanArr[stuckStartXTilePos][stuckStartYTilePos]);
        leftOutBoundCleanSweep = new CleanSweep(0,0, true, stairStuckFloorPlanArr[0][0]);
        rightOutBoundCleanSweep = new CleanSweep(2,0, true, stairStuckFloorPlanArr[2][0]);
        upOutBoundCleanSweep = new CleanSweep(0,0, true, stairStuckFloorPlanArr[0][0]);
        downOutBoundCleanSweep = new CleanSweep(0,2, true, stairStuckFloorPlanArr[0][2]);
        offCleanSweep = new CleanSweep(startXTilePos,startYTilePos,false,bareFloorPlanArr[startXTilePos][startYTilePos]);
        onCleanSweep = new CleanSweep(startXTilePos,startYTilePos,true,bareFloorPlanArr[startXTilePos][startYTilePos]);
        downAvoidanceCleanSweep = new CleanSweep(startXTilePos,startYTilePos,true,bottomOpenFloorPlanArr[startXTilePos][startYTilePos]);
        rightAvoidanceCleanSweep = new CleanSweep(startXTilePos,startYTilePos,true,rightOpenFloorPlanArr[startXTilePos][startYTilePos]);
    }

    // Traversals, Traversable, Bare floor

    @Test
    public void CleanSweepTraverseLeftTraversable() {
        System.out.println("\nCleanSweepTraverseLeftTraversable()");
        assertTrue(cleanSweep.traverseLeft(bareFloorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]));
    }

    @Test
    public void CleanSweepTraverseRightTraversable() {
        System.out.println("\ntraverseRightTraversable()");
        assertTrue(cleanSweep.traverseRight(bareFloorPlanArr[cleanSweep.getXPos()+1][cleanSweep.getYPos()]));
    }

    @Test
    public void CleanSweepTraverseUpTraversable() {
        System.out.println("\nCleanSweepTraverseUpTraversable()");
        assertTrue(cleanSweep.traverseUp(bareFloorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()-1]));
    }

    @Test
    public void traverseDownNoTraversable() {
        System.out.println("\ntraverseDownTraversable()");
        assertTrue(cleanSweep.traverseDown(bareFloorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()+1]));
    }

    // Traversals, Not Traversable, Bare floor

    @Test
    public void traverseUpNotTraversable() {
        System.out.println("\ntraverseUpNotTraversable()");
        assertFalse(stuckCleanSweep.traverseUp(stuckFloorPlanArr[stuckCleanSweep.getXPos()][stuckCleanSweep.getYPos()-1]));
    }

    @Test
    public void traverseDownNotTraversable() {
        System.out.println("\ntraverseDownNotTraversable()");
        assertFalse(stuckCleanSweep.traverseDown(stuckFloorPlanArr[stuckCleanSweep.getXPos()][stuckCleanSweep.getYPos()+1]));
    }

    @Test
    public void traverseLeftNotTraversable() {
        System.out.println("\ntraverseLeftNotTraversable()");
        assertFalse(stuckCleanSweep.traverseLeft(stuckFloorPlanArr[stuckCleanSweep.getXPos()-1][stuckCleanSweep.getYPos()]));
    }

    @Test
    public void traverseRightNotTraversable() {
        System.out.println("\ntraverseRightNotTraversable()");
        assertFalse(stuckCleanSweep.traverseRight(stuckFloorPlanArr[stuckCleanSweep.getXPos()+1][stuckCleanSweep.getYPos()]));
    }
    // Shut down if clean sweep cannot traverse to any adjacent tile

    @Test
    public void stuckShutdown() {
        System.out.println("\nstuckShutdown()");
        stuckCleanSweep.traverseRight(stuckFloorPlanArr[stuckCleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        assertFalse(stuckCleanSweep.powerOn);
    }


    // Avoidance Tests

    // Avoid Left Tests

    @Test
    public void AvoidLeftTraverseUp() {
        // TODO
    }

    @Test
    public void AvoidLeftTraverseDown() {
        System.out.println("\nAvoidLeftTraverseDown()");
        int xPreLeftTraversal = downAvoidanceCleanSweep.getXPos();
        int yPreLeftTraversal = downAvoidanceCleanSweep.getYPos();

        downAvoidanceCleanSweep.traverseLeft(bottomOpenFloorPlanArr[downAvoidanceCleanSweep.getXPos()-1][downAvoidanceCleanSweep.getYPos()]);

        int xPostLeftTraversal = downAvoidanceCleanSweep.getXPos();
        int yPostLeftTraversal = downAvoidanceCleanSweep.getYPos();

        assertEquals(yPreLeftTraversal + 1,yPostLeftTraversal);
    }

    // Avoid Left Traverse Left Redundant

    @Test
    public void AvoidLeftTraverseRight() {
        System.out.println("\nAvoidLeftTraverseRight()");
        int xPreLeftTraversal = rightAvoidanceCleanSweep.getXPos();
        int yPreLeftTraversal = rightAvoidanceCleanSweep.getYPos();

        rightAvoidanceCleanSweep.traverseLeft(rightOpenFloorPlanArr[rightAvoidanceCleanSweep.getXPos()-1][rightAvoidanceCleanSweep.getYPos()]);

        int xPostLeftTraversal = rightAvoidanceCleanSweep.getXPos();
        int yPostLeftTraversal = rightAvoidanceCleanSweep.getYPos();

        assertEquals(xPreLeftTraversal + 1,xPostLeftTraversal);
    }


    // Avoid Right Tests

    @Test
    public void AvoidRightTraverseUp() {
        // TODO
    }

    @Test
    public void AvoidRightTraverseDown() {
        System.out.println("\nAvoidRightTraverseDown()");
        int xPreRightTraversal = downAvoidanceCleanSweep.getXPos();
        int yPreRightTraversal = downAvoidanceCleanSweep.getYPos();

        downAvoidanceCleanSweep.traverseRight(bottomOpenFloorPlanArr[downAvoidanceCleanSweep.getXPos()+1][downAvoidanceCleanSweep.getYPos()]);

        int xPostRightTraversal = downAvoidanceCleanSweep.getXPos();
        int yPostRightTraversal = downAvoidanceCleanSweep.getYPos();

        assertEquals(yPreRightTraversal + 1,yPostRightTraversal);
    }

    @Test
    public void AvoidRightTraverseLeft() {
        //TODO
    }

    // Avoid Right Traverse Right Redundant


    // Avoid Up Tests
    // Avoid Up Traverse Up Redundant

    @Test
    public void AvoidUpTraverseDown() {
        System.out.println("\nAvoidUpTraverseDown()");
        int xPreUpTraversal = downAvoidanceCleanSweep.getXPos();
        int yPreUpTraversal = downAvoidanceCleanSweep.getYPos();

        downAvoidanceCleanSweep.traverseUp(bottomOpenFloorPlanArr[downAvoidanceCleanSweep.getXPos()][downAvoidanceCleanSweep.getYPos()-1]);

        int xPostUpTraversal = downAvoidanceCleanSweep.getXPos();
        int yPostUpTraversal = downAvoidanceCleanSweep.getYPos();

        assertEquals(yPreUpTraversal + 1,yPostUpTraversal);
    }

    @Test
    public void AvoidUpTraverseLeft() {
        //TODO
    }

    @Test
    public void AvoidUpTraverseRight() {
        System.out.println("\nAvoidUpTraverseRight()");
        int xPreUpTraversal = rightAvoidanceCleanSweep.getXPos();
        int yPreUpTraversal = rightAvoidanceCleanSweep.getYPos();

        rightAvoidanceCleanSweep.traverseUp(rightOpenFloorPlanArr[rightAvoidanceCleanSweep.getXPos()][rightAvoidanceCleanSweep.getYPos()-1]);

        int xPostUpTraversal = rightAvoidanceCleanSweep.getXPos();
        int yPostUpTraversal = rightAvoidanceCleanSweep.getYPos();

        assertEquals(xPreUpTraversal + 1,xPostUpTraversal);
    }

    // Avoid Down Tests

    @Test
    public void AvoidDownTraverseUp() {
        // TODO
    }

    // Avoid Down Traverse Down Redundant Test

    @Test
    public void AvoidDownTraverseLeft() {
        // TODO
    }

    @Test
    public void AvoidDownTraverseRight() {
        System.out.println("\nAvoidDownTraverseRight()");
        int xPreDownTraversal = rightAvoidanceCleanSweep.getXPos();
        int yPreDownTraversal = rightAvoidanceCleanSweep.getYPos();

        rightAvoidanceCleanSweep.traverseDown(rightOpenFloorPlanArr[rightAvoidanceCleanSweep.getXPos()][rightAvoidanceCleanSweep.getYPos()+1]);

        int xPostDownTraversal = rightAvoidanceCleanSweep.getXPos();
        int yPostDownTraversal = rightAvoidanceCleanSweep.getYPos();

        assertEquals(xPreDownTraversal + 1,xPostDownTraversal);
    }


    // Out of Bounds Tests

    @Test
    public void stairStuckShutdown() {
        System.out.println("\nstairStuckShutdown()");
        stairStuckCleanSweep.traverseRight(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        assertFalse(stairStuckCleanSweep.powerOn);
    }

    @Test
    public void notTraverseLeftOutOfBounds() {
        System.out.println("\nnotTraverseLeftOutOfBounds()");
        try {
            leftOutBoundCleanSweep.traverseLeft(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        } catch (Exception e) {
            fail("Traversing out of bounds should not throw an exception");
        }
    }

    @Test
    public void notTraverseRightOutOfBounds() {
        System.out.println("\nnotTraverseRightOutOfBounds()");
        try {
            rightOutBoundCleanSweep.traverseRight(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        } catch (Exception e) {
            fail("Traversing out of bounds should not throw an exception");
        }
    }

    @Test
    public void notTraverseUpOutOfBounds() {
        System.out.println("\nnotTraverseUpOutOfBounds()");
        try {
            upOutBoundCleanSweep.traverseUp(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        } catch (Exception e) {
            fail("Traversing out of bounds should not throw an exception");
        }
    }

    @Test
    public void notTraverseDownOutOfBounds() {
        System.out.println("\nnotTraverseUpOutOfBounds()");
        try {
            downOutBoundCleanSweep.traverseDown(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()][cleanSweep.getYPos()+1]);
        } catch (Exception e) {
            fail("Traversing out of bounds should not throw an exception");
        }
    }

    // Start up and shut down tests

    @Test
    public void CleanSweepStartUp() {
        System.out.println("\nCleanSweepStartUp()");
        offCleanSweep.startUp();
        assertTrue(offCleanSweep.powerOn);
    }

    @Test
    public void CleanSweepShutDown() {
        System.out.println("\nCleanSweepShutDown()");
        onCleanSweep.shutDown();
        assertFalse(onCleanSweep.powerOn);
    }

    // Cleaning Tests

    @Test
    public void CleanSweepCleanOneUnit() { // Clean Sweep clean 1 unit of dirt
        System.out.println("\nCleanSweepCleanOneUnit()");
        //TODO
    }

    @Test
    public void CleanSweepCleanCleanTile() { // Clean Sweep cleans tile until tile is clean
        System.out.println("\nCleanSweepCleanCleanTile()");
        //TODO
    }

    @Test
    public void CleanSweepDirtCapacityFull() { // Clean Sweep can not clean tile because dirt capacity is full
        System.out.println("\nCleanSweepDirtCapacityFull()");
        //TODO
    }

    @Test
    public void CleanSweepNotCleanTileDirtCapacityFull() { // Clean Sweep dirt capacity fills up while cleaning a tile, tile not fully clean
        System.out.println("\nCleanSweepNotCleanTileDirtCapacityFull()");
        //TODO
    }
}