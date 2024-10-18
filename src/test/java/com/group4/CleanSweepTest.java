package com.group4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertFalse;

class CleanSweepTest {

    int startXTilePos;
    int startYTilePos;

    int floorPlanLength;
    int floorPlanWidth;

    CleanSweep cleanSweep;

    FloorPlan floorPlan;
    Tile[][] floorPlanArr;

    FloorPlan bareFloorPlan;
    Tile[][] bareFloorPlanArr;

    @BeforeEach
    void setUp() {
        startXTilePos = 2;
        startYTilePos = 2;

        floorPlanLength = 5;
        floorPlanWidth = 5;

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

        // Place Star at 2,3 (Directly above clean sweep start position (2,2))
        bareFloorPlanArr[2][3] = new StairDeclineTile(null, null, null, null, 2, 3);

        floorPlan.connectFloorPlan();
        bareFloorPlan.connectFloorPlan();

        cleanSweep = new CleanSweep(startXTilePos,startYTilePos,true,bareFloorPlanArr[startXTilePos][startYTilePos]);
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

}