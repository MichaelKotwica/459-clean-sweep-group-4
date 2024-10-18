package com.group4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertFalse;

class CleanSweepTest {

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
    }

    @Test
    void traverseLeftNoObstacle() {
        assertTrue(cleanSweep.traverseLeft(bareFloorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]));
    }

    @Test
    void traverseRightNoObstacle() {
        assertTrue(cleanSweep.traverseRight(bareFloorPlanArr[cleanSweep.getXPos()+1][cleanSweep.getYPos()]));
    }

    @Test
    void traverseUpObstacle() {
        assertFalse(cleanSweep.traverseUp(bareFloorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()-1]));
    }

    @Test
    void traverseDownNoObstacle() {
        assertFalse(cleanSweep.traverseDown(bareFloorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()+1]));
    }

    @Test
    void traverseDownStairDecline() {
        assertFalse(cleanSweep.traverseDown(bareFloorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()+1]));
    }

    @Test
    void stuckShutdown() {
        stuckCleanSweep.traverseRight(stuckFloorPlanArr[stuckCleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        assertFalse(stuckCleanSweep.powerOn);
    }

    @Test
    void stairAvoidance(){
        assertFalse(cleanSweep.traverseDown(bareFloorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()+1]));
    }

    @Test
    void stairStuckShutdown() {
        stairStuckCleanSweep.traverseRight(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()+1][cleanSweep.getYPos()]);
        assertFalse(stairStuckCleanSweep.powerOn);
    }

}