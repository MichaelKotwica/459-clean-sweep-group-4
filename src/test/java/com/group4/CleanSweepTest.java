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

    @BeforeEach
    void setUp() {
        startXTilePos = 2;
        startYTilePos = 2;

        floorPlanLength = 5;
        floorPlanWidth = 5;

        floorPlan = new FloorPlan(floorPlanLength, floorPlanWidth);
        floorPlanArr = floorPlan.createFloorPlan();

        // Make all Tiles Bare floor
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                floorPlanArr[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }

        // Place Obstacle at 2,1 (Directly above clean sweep start position (2,2))
        floorPlanArr[2][1] = new Obstacle(null, null, null, null, 2, 1);

        floorPlan.connectFloorPlan();

        cleanSweep = new CleanSweep(startXTilePos,startYTilePos,true,floorPlanArr[startXTilePos][startYTilePos]);
    }

    @Test
    void traverseLeftNoObstacle() {
        assertTrue(cleanSweep.traverseLeft(floorPlanArr[cleanSweep.getXPos()-1][cleanSweep.getYPos()]));
    }

    @Test
    void traverseRightNoObstacle() {
        assertTrue(cleanSweep.traverseRight(floorPlanArr[cleanSweep.getXPos()+1][cleanSweep.getYPos()]));
    }

    @Test
    void traverseUpObstacle() {
        assertFalse(cleanSweep.traverseUp(floorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()-1]));
    }

    @Test
    void traverseDownNoObstacle() {
        assertTrue(cleanSweep.traverseDown(floorPlanArr[cleanSweep.getXPos()][cleanSweep.getYPos()+1]));
    }
}