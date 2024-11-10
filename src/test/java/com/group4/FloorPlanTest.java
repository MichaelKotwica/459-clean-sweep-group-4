package com.group4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorPlanTest {

    FloorPlan floorPlan;
    Tile[][] floorPlanArr;

    int floorPlanLength;
    int floorPlanWidth;

    @BeforeEach
    void setUp() {
        floorPlanLength = 5;
        floorPlanWidth = 5;

        floorPlan = new FloorPlan(floorPlanLength, floorPlanLength);
        floorPlanArr = floorPlan.createFloorPlan();

        // Make all Tiles Bare floor
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                floorPlanArr[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }

        floorPlan.connectFloorPlan();
    }


    @Test
    public void addVerticalWallLeftRight() {
        System.out.println("\naddVerticalWallLeftRight()");
        floorPlan.addWall(floorPlanArr[0][0],floorPlanArr[1][0]);
        //floorPlan.addWall(floorPlanArr[3][2],floorPlanArr[2][2]);

        assertAll(
                () -> assertNull(floorPlanArr[0][0].rightNext),
                () -> assertNull(floorPlanArr[0][1].leftNext)
        );
    }

    @Test
    public void addVerticalWallRightLeft() {
        System.out.println("\naddVerticalWallRightLeft()");
        floorPlan.addWall(floorPlanArr[1][0],floorPlanArr[0][0]);

        assertAll(
                () -> assertNull(floorPlanArr[0][0].rightNext),
                () -> assertNull(floorPlanArr[0][1].leftNext)
        );
    }

    @Test
    public void addVerticalWallsLeftRightTooFar() {
        System.out.println("\naddVerticalWallsLeftRightTooFar()");
        floorPlan.addWall(floorPlanArr[0][0],floorPlanArr[2][0]);
        //floorPlan.addWall(floorPlanArr[3][2],floorPlanArr[2][2]);

        assertAll(
                () -> assertNotNull(floorPlanArr[0][0].rightNext),
                () -> assertNotNull(floorPlanArr[2][0].leftNext)
        );
    }

    @Test
    public void addVerticalWallRightLeftTooFar() {
        System.out.println("\naddVerticalWallRightLeftTooFar()");
        floorPlan.addWall(floorPlanArr[2][0],floorPlanArr[0][0]);

        assertAll(
                () -> assertNotNull(floorPlanArr[0][0].rightNext),
                () -> assertNotNull(floorPlanArr[2][0].leftNext)
        );
    }

    @Test
    public void addHorizontalWallTopBottom() {
        System.out.println("\naddHorizontalWallTopBottom()");
        floorPlan.addWall(floorPlanArr[0][0],floorPlanArr[0][1]);

        assertAll(
                () -> assertNull(floorPlanArr[0][0].bottomNext),
                () -> assertNull(floorPlanArr[1][0].topNext)
        );
    }

    @Test
    public void addHorizontalWallBottomTop() {
        System.out.println("\naddHorizontalWallBottomTop()");
        floorPlan.addWall(floorPlanArr[0][1],floorPlanArr[0][0]);

        assertAll(
                () -> assertNull(floorPlanArr[0][0].bottomNext),
                () -> assertNull(floorPlanArr[1][0].topNext)
        );
    }

    @Test
    public void addHorizontalWallTopBottomTooFar() {
        System.out.println("\naddHorizontalWallTopBottomTooFar()");
        floorPlan.addWall(floorPlanArr[0][0],floorPlanArr[0][2]);

        assertAll(
                () -> assertNotNull(floorPlanArr[0][0].bottomNext),
                () -> assertNotNull(floorPlanArr[0][2].topNext)
        );
    }

    @Test
    public void addHorizontalWallBottomTopTooFar() {
        System.out.println("\naddHorizontalWallBottomTopTooFar()");
        floorPlan.addWall(floorPlanArr[0][2],floorPlanArr[0][0]);

        assertAll(
                () -> assertNotNull(floorPlanArr[0][0].bottomNext),
                () -> assertNotNull(floorPlanArr[0][2].topNext)
        );
    }

    @Test
    public void addWallTopLeftBottomRight() {
        System.out.println("\naddWallTopLeftBottomRight()");
        floorPlan.addWall(floorPlanArr[0][0],floorPlanArr[1][1]);

        assertAll(
                () -> assertNotNull(floorPlanArr[0][0].rightNext),
                () -> assertNotNull(floorPlanArr[0][0].bottomNext),
                () -> assertNull(floorPlanArr[0][0].leftNext),
                () -> assertNull(floorPlanArr[0][0].topNext),
                () -> assertNotNull(floorPlanArr[1][1].rightNext),
                () -> assertNotNull(floorPlanArr[1][1].bottomNext),
                () -> assertNotNull(floorPlanArr[1][1].leftNext),
                () -> assertNotNull(floorPlanArr[1][1].topNext)
        );
    }

    @Test
    public void addWallTopRightBottomLeft() {
        System.out.println("\naddWallTopRightBottomLeft()");
        floorPlan.addWall(floorPlanArr[1][0],floorPlanArr[0][1]);

        assertAll(
                () -> assertNotNull(floorPlanArr[1][0].rightNext),
                () -> assertNotNull(floorPlanArr[1][0].bottomNext),
                () -> assertNotNull(floorPlanArr[1][0].leftNext),
                () -> assertNull(floorPlanArr[1][0].topNext),
                () -> assertNotNull(floorPlanArr[0][1].rightNext),
                () -> assertNotNull(floorPlanArr[0][1].bottomNext),
                () -> assertNull(floorPlanArr[0][1].leftNext),
                () -> assertNotNull(floorPlanArr[0][1].topNext)
        );
    }

    @Test
    public void addWallBottomRightTopLeft() {
        System.out.println("\naddWallBottomRightTopLeft()");
        floorPlan.addWall(floorPlanArr[1][1], floorPlanArr[0][0]);

        assertAll(
                () -> assertNotNull(floorPlanArr[0][0].rightNext),
                () -> assertNotNull(floorPlanArr[0][0].bottomNext),
                () -> assertNull(floorPlanArr[0][0].leftNext),
                () -> assertNull(floorPlanArr[0][0].topNext),
                () -> assertNotNull(floorPlanArr[1][1].rightNext),
                () -> assertNotNull(floorPlanArr[1][1].bottomNext),
                () -> assertNotNull(floorPlanArr[1][1].leftNext),
                () -> assertNotNull(floorPlanArr[1][1].topNext)
        );
    }

    @Test
    public void addWallBottomLeftTopRight() {
        System.out.println("\naddWallBottomLeftTopRight()");
        floorPlan.addWall(floorPlanArr[0][1],floorPlanArr[1][0]);

        assertAll(
                () -> assertNotNull(floorPlanArr[1][0].rightNext),
                () -> assertNotNull(floorPlanArr[1][0].bottomNext),
                () -> assertNotNull(floorPlanArr[1][0].leftNext),
                () -> assertNull(floorPlanArr[1][0].topNext),
                () -> assertNotNull(floorPlanArr[0][1].rightNext),
                () -> assertNotNull(floorPlanArr[0][1].bottomNext),
                () -> assertNull(floorPlanArr[0][1].leftNext),
                () -> assertNotNull(floorPlanArr[0][1].topNext)
        );
    }
}