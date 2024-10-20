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

    FloorPlan floorPlan;
    Tile[][] floorPlanArr;

    FloorPlan bareFloorPlan;
    Tile[][] bareFloorPlanArr;

    FloorPlan stuckFloorPlan;
    Tile[][] stuckFloorPlanArr;

    FloorPlan stairStuckFloorPlan;
    Tile[][] stairStuckFloorPlanArr;

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
        bareFloorPlanArr[2][1] = new Obstacle(null, null, null, null, 2, 1);

        // Place Stair at 2,3 (Directly above clean sweep start position (2,2))
        bareFloorPlanArr[2][3] = new StairDeclineTile(null, null, null, null, 2, 3);

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

        cleanSweep = new CleanSweep(startXTilePos,startYTilePos,true,bareFloorPlanArr[startXTilePos][startYTilePos]);

        stuckCleanSweep = new CleanSweep(stuckStartXTilePos, stuckStartYTilePos, true, stuckFloorPlanArr[stuckStartXTilePos][stuckStartYTilePos]);

        stairStuckCleanSweep = new CleanSweep(stuckStartXTilePos, stuckStartYTilePos, true, stairStuckFloorPlanArr[stuckStartXTilePos][stuckStartYTilePos]);

        leftOutBoundCleanSweep = new CleanSweep(0,0, true, stairStuckFloorPlanArr[0][0]);
        rightOutBoundCleanSweep = new CleanSweep(2,0, true, stairStuckFloorPlanArr[2][0]);
        upOutBoundCleanSweep = new CleanSweep(0,0, true, stairStuckFloorPlanArr[0][0]);
        downOutBoundCleanSweep = new CleanSweep(0,2, true, stairStuckFloorPlanArr[0][2]);
    }

    @Test
    void traverseLeftNoObstacle() {
        System.out.println("\ntraverseLeftNoObstacle()");
        assertTrue(cleanSweep.traverseLeft(bareFloorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]));
    }

    @Test
    void traverseRightNoObstacle() {
        System.out.println("\ntraverseRightNoObstacle()");
        assertTrue(cleanSweep.traverseRight(bareFloorPlanArr[cleanSweep.getXPos()+1][cleanSweep.getYPos()]));
    }

    @Test
    void traverseUpObstacle() {
        System.out.println("\ntraverseUpObstacle()");
        assertFalse(cleanSweep.traverseUp(bareFloorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()-1]));
    }

    @Test
    void traverseDownNoObstacle() {
        System.out.println("\ntraverseDownNoObstacle()");
        assertFalse(cleanSweep.traverseDown(bareFloorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()+1]));
    }

    @Test
    void traverseDownStairDecline() {
        System.out.println("\ntraverseDownStairDecline()");
        assertFalse(cleanSweep.traverseDown(bareFloorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()+1]));
    }

    @Test
    void stuckShutdown() {
        System.out.println("\nstuckShutdown()");
        stuckCleanSweep.traverseRight(stuckFloorPlanArr[stuckCleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        assertFalse(stuckCleanSweep.powerOn);
    }

    @Test
    void stairAvoidance() {
        System.out.println("\nstairAvoidance()");
        assertFalse(cleanSweep.traverseDown(bareFloorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()+1]));
    }

    @Test
    void stairStuckShutdown() {
        System.out.println("\nstairStuckShutdown()");
        stairStuckCleanSweep.traverseRight(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        assertFalse(stairStuckCleanSweep.powerOn);
    }

    @Test
    void notTraverseLeftOutOfBounds() {
        System.out.println("\nnotTraverseLeftOutOfBounds()");
        try {
            leftOutBoundCleanSweep.traverseLeft(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()-1][cleanSweep.getYPos()]);
        } catch (Exception e) {
            fail("Traversing out of bounds should not throw an exception");
        }
    }

    @Test
    void notTraverseRightOutOfBounds() {
        System.out.println("\nnotTraverseRightOutOfBounds()");
        try {
            rightOutBoundCleanSweep.traverseRight(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        } catch (Exception e) {
            fail("Traversing out of bounds should not throw an exception");
        }
    }

    @Test
    void notTraverseUpOutOfBounds() {
        System.out.println("\nnotTraverseUpOutOfBounds()");
        try {
            upOutBoundCleanSweep.traverseUp(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()][cleanSweep.getYPos()-1]);
        } catch (Exception e) {
            fail("Traversing out of bounds should not throw an exception");
        }
    }

    @Test
    void notTraverseDownOutOfBounds() {
        System.out.println("\nnotTraverseUpOutOfBounds()");
        try {
            downOutBoundCleanSweep.traverseDown(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()][cleanSweep.getYPos()+1]);
        } catch (Exception e) {
            fail("Traversing out of bounds should not throw an exception");
        }
    }

}