package com.group4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
    CleanSweep leftAvoidanceCleanSweep;
    CleanSweep upAvoidanceCleanSweep;
    CleanSweep cleaningCleanSweep;

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

    FloorPlan leftOpenFloorPlan;
    Tile[][] leftOpenFloorPlanArr;

    FloorPlan upOpenFloorPlan;
    Tile[][] upOpenFloorPlanArr;

    FloorPlan cleaningFloorPlan;
    Tile[][] cleaningFloorPlanArr;


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
        initBottomOpenFloorPlan();

        // Right Open Floor Plan
        initRightOpenFloorPlan();

        // Left Open Floor Plan
        initLeftOpenFloorPlan();

        // Up Open Floor Plan
        initUpOpenFloorPlan();

        // Cleaning Floor Plan
        initCleaningFloorPlan();

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
        leftAvoidanceCleanSweep = new CleanSweep(startXTilePos,startYTilePos,true,leftOpenFloorPlanArr[startXTilePos][startYTilePos]);
        upAvoidanceCleanSweep = new CleanSweep(startXTilePos,startYTilePos,true,upOpenFloorPlanArr[startXTilePos][startYTilePos]);
        cleaningCleanSweep = new CleanSweep(startXTilePos,startYTilePos,true,cleaningFloorPlanArr[startXTilePos][startYTilePos]);
    }

    void initBottomOpenFloorPlan() {
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
    }

    void initRightOpenFloorPlan() {
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
    }

    void initLeftOpenFloorPlan() {
        leftOpenFloorPlan = new FloorPlan(floorPlanLength, floorPlanWidth); // 5 x 5
        leftOpenFloorPlanArr = leftOpenFloorPlan.createFloorPlan();


        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                leftOpenFloorPlanArr[i][j] = new BareFloorTile(null,null,null,null,i,j);
            }
        }

        // Place Obstacles surrounding the clean sweep starting position Except Left
        leftOpenFloorPlanArr[2][1] = new Obstacle(null, null, null, null, 2, 1); // Above
        leftOpenFloorPlanArr[2][3] = new Obstacle(null, null, null, null, 2, 3); // Below
        // leftOpenFloorPlanArr[1][2] = new Obstacle(null, null, null, null, 1, 2); // Left
        leftOpenFloorPlanArr[3][2] = new Obstacle(null, null, null, null, 3, 2); // Right
        leftOpenFloorPlan.connectFloorPlan();
    }

    void initUpOpenFloorPlan() {
        upOpenFloorPlan = new FloorPlan(floorPlanLength, floorPlanWidth); // 5 x 5
        upOpenFloorPlanArr = upOpenFloorPlan.createFloorPlan();


        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                upOpenFloorPlanArr[i][j] = new BareFloorTile(null,null,null,null,i,j);
            }
        }

        // Place Obstacles surrounding the clean sweep starting position Except above
        // upOpenFloorPlanArr[2][1] = new Obstacle(null, null, null, null, 2, 1); // Above
        upOpenFloorPlanArr[2][3] = new Obstacle(null, null, null, null, 2, 3); // Below
        upOpenFloorPlanArr[1][2] = new Obstacle(null, null, null, null, 1, 2); // Left
        upOpenFloorPlanArr[3][2] = new Obstacle(null, null, null, null, 3, 2); // Right
        upOpenFloorPlan.connectFloorPlan();
    }

    void initCleaningFloorPlan() {
        cleaningFloorPlan = new FloorPlan(floorPlanLength, floorPlanWidth); // 5 x 5
        cleaningFloorPlanArr = cleaningFloorPlan.createFloorPlan();


        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                cleaningFloorPlanArr[i][j] = new BareFloorTile(null,null,null,null,i,j);
            }
        }

        // Add specific dirt amounts to specific tiles
        cleaningFloorPlanArr[2][1].setDirtAmount(1); // 1 dirt above clean sweep
        cleaningFloorPlanArr[3][2].setDirtAmount(2); // 2 dirt right clean sweep
        cleaningFloorPlanArr[2][3].setDirtAmount(48); // 48 dirt below clean sweep
        cleaningFloorPlanArr[1][3].setDirtAmount(1); // 1 dirt left clean sweep
        cleaningFloorPlanArr[2][0].setDirtAmount(0); // 0 dirt 2 tiles above clean sweep

        cleaningFloorPlan.connectFloorPlan();
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
        System.out.println("\nAvoidLeftTraverseUp()");
        int yPreLeftTraversal = upAvoidanceCleanSweep.getYPos();

        upAvoidanceCleanSweep.traverseLeft(upOpenFloorPlanArr[upAvoidanceCleanSweep.getXPos()-1][upAvoidanceCleanSweep.getYPos()]);

        int yPostLeftTraversal = upAvoidanceCleanSweep.getYPos();

        assertEquals(yPreLeftTraversal - 1,yPostLeftTraversal);
    }

    @Test
    public void AvoidLeftTraverseDown() {
        System.out.println("\nAvoidLeftTraverseDown()");
        int yPreLeftTraversal = downAvoidanceCleanSweep.getYPos();

        downAvoidanceCleanSweep.traverseLeft(bottomOpenFloorPlanArr[downAvoidanceCleanSweep.getXPos()-1][downAvoidanceCleanSweep.getYPos()]);

        int yPostLeftTraversal = downAvoidanceCleanSweep.getYPos();

        assertEquals(yPreLeftTraversal + 1,yPostLeftTraversal);
    }

    @Test
    public void AvoidLeftTraverseRight() {
        System.out.println("\nAvoidLeftTraverseRight()");
        int xPreLeftTraversal = rightAvoidanceCleanSweep.getXPos();

        rightAvoidanceCleanSweep.traverseLeft(rightOpenFloorPlanArr[rightAvoidanceCleanSweep.getXPos()-1][rightAvoidanceCleanSweep.getYPos()]);

        int xPostLeftTraversal = rightAvoidanceCleanSweep.getXPos();

        assertEquals(xPreLeftTraversal + 1,xPostLeftTraversal);
    }


    // Avoid Right Tests

    @Test
    public void AvoidRightTraverseUp() {
        System.out.println("\nAvoidRightTraverseUp()");
        int yPreRightTraversal = upAvoidanceCleanSweep.getYPos();

        upAvoidanceCleanSweep.traverseRight(upOpenFloorPlanArr[upAvoidanceCleanSweep.getXPos()+1][upAvoidanceCleanSweep.getYPos()]);

        int yPostRightTraversal = upAvoidanceCleanSweep.getYPos();

        assertEquals(yPreRightTraversal - 1,yPostRightTraversal);
    }

    @Test
    public void AvoidRightTraverseDown() {
        System.out.println("\nAvoidRightTraverseDown()");
        int yPreRightTraversal = downAvoidanceCleanSweep.getYPos();

        downAvoidanceCleanSweep.traverseRight(bottomOpenFloorPlanArr[downAvoidanceCleanSweep.getXPos()+1][downAvoidanceCleanSweep.getYPos()]);

        int yPostRightTraversal = downAvoidanceCleanSweep.getYPos();

        assertEquals(yPreRightTraversal + 1,yPostRightTraversal);
    }

    @Test
    public void AvoidRightTraverseLeft() {
        System.out.println("\nAvoidRightTraverseLeft()");
        int xPreRightTraversal = leftAvoidanceCleanSweep.getXPos();

        leftAvoidanceCleanSweep.traverseRight(leftOpenFloorPlanArr[leftAvoidanceCleanSweep.getXPos()+1][leftAvoidanceCleanSweep.getYPos()]);

        int xPostRightTraversal = leftAvoidanceCleanSweep.getXPos();

        assertEquals(xPreRightTraversal - 1,xPostRightTraversal);
    }

    // Avoid Up Tests

    @Test
    public void AvoidUpTraverseDown() {
        System.out.println("\nAvoidUpTraverseDown()");
        int yPreUpTraversal = downAvoidanceCleanSweep.getYPos();

        downAvoidanceCleanSweep.traverseUp(bottomOpenFloorPlanArr[downAvoidanceCleanSweep.getXPos()][downAvoidanceCleanSweep.getYPos()-1]);

        int yPostUpTraversal = downAvoidanceCleanSweep.getYPos();

        assertEquals(yPreUpTraversal + 1,yPostUpTraversal);
    }

    @Test
    public void AvoidUpTraverseLeft() {
        System.out.println("\nAvoidUpTraverseLeft()");
        int xPreUpTraversal = leftAvoidanceCleanSweep.getXPos();

        leftAvoidanceCleanSweep.traverseUp(leftOpenFloorPlanArr[leftAvoidanceCleanSweep.getXPos()][leftAvoidanceCleanSweep.getYPos()-1]);

        int xPostUpTraversal = leftAvoidanceCleanSweep.getXPos();

        assertEquals(xPreUpTraversal - 1,xPostUpTraversal);
    }

    @Test
    public void AvoidUpTraverseRight() {
        System.out.println("\nAvoidUpTraverseRight()");
        int xPreUpTraversal = rightAvoidanceCleanSweep.getXPos();

        rightAvoidanceCleanSweep.traverseUp(rightOpenFloorPlanArr[rightAvoidanceCleanSweep.getXPos()][rightAvoidanceCleanSweep.getYPos()-1]);

        int xPostUpTraversal = rightAvoidanceCleanSweep.getXPos();

        assertEquals(xPreUpTraversal + 1,xPostUpTraversal);
    }

    // Avoid Down Tests

    @Test
    public void AvoidDownTraverseUp() {
        System.out.println("\nAvoidDownTraverseUp()");
        int yPreUpTraversal = upAvoidanceCleanSweep.getYPos();

        upAvoidanceCleanSweep.traverseDown(upOpenFloorPlanArr[upAvoidanceCleanSweep.getXPos()][upAvoidanceCleanSweep.getYPos()+1]);

        int yPostUpTraversal = upAvoidanceCleanSweep.getYPos();

        assertEquals(yPreUpTraversal - 1,yPostUpTraversal);
    }

    @Test
    public void AvoidDownTraverseLeft() {
        System.out.println("\nAvoidDownTraverseLeft()");
        int xPreDownTraversal = leftAvoidanceCleanSweep.getXPos();

        leftAvoidanceCleanSweep.traverseDown(leftOpenFloorPlanArr[leftAvoidanceCleanSweep.getXPos()][leftAvoidanceCleanSweep.getYPos()+1]);

        int xPostDownTraversal = leftAvoidanceCleanSweep.getXPos();

        assertEquals(xPreDownTraversal - 1,xPostDownTraversal);
    }

    @Test
    public void AvoidDownTraverseRight() {
        System.out.println("\nAvoidDownTraverseRight()");
        int xPreDownTraversal = rightAvoidanceCleanSweep.getXPos();

        rightAvoidanceCleanSweep.traverseDown(rightOpenFloorPlanArr[rightAvoidanceCleanSweep.getXPos()][rightAvoidanceCleanSweep.getYPos()+1]);

        int xPostDownTraversal = rightAvoidanceCleanSweep.getXPos();

        assertEquals(xPreDownTraversal + 1,xPostDownTraversal);
    }

    // Wall Avoidance Test
    @Test
    public void CleanSweepAvoidWall() {
        System.out.println("\nCleanSweepAvoidWall()");
        cleaningFloorPlan.addWall(cleaningFloorPlanArr[2][3], cleaningFloorPlanArr[1][3]);
        cleaningCleanSweep.traverseDown(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos() + 1]);
        assertFalse(cleaningCleanSweep.traverseLeft(cleaningFloorPlanArr[cleaningCleanSweep.getXPos() - 1][cleaningCleanSweep.getYPos()]));
        cleaningCleanSweep.printPos();
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
        cleaningCleanSweep.traverseUp(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()-1]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getCurrentTile());
        assertEquals(1, cleaningCleanSweep.getDirtCapacity());
    }

    @Test
    public void CleanSweepCleanCleanUntilClean() { // Clean Sweep cleans tile until tile is clean
        System.out.println("\nCleanSweepCleanCleanUntilClean()");
        cleaningCleanSweep.traverseRight(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()+1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getCurrentTile());
        assertTrue(cleaningCleanSweep.getCurrentTile().cleanTile);
    }

    @Test
    public void CleanSweepDirtCapacityFull() { // Clean Sweep can not clean tile because dirt capacity is full
        System.out.println("\nCleanSweepDirtCapacityFull()");
        cleaningCleanSweep.traverseUp(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()-1]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getCurrentTile());
        cleaningCleanSweep.traverseDown(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()+1]);
        cleaningCleanSweep.traverseRight(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()+1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getCurrentTile());
        cleaningCleanSweep.traverseLeft(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()-1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.traverseDown(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()+1]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getCurrentTile());
        cleaningCleanSweep.traverseUp(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()-1]);
        cleaningCleanSweep.traverseLeft(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()-1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getCurrentTile());
        assertFalse(cleaningCleanSweep.getCurrentTile().cleanTile);
    }

    @Test
    public void CleanSweepNotCleanTileDirtCapacityFull() { // Clean Sweep dirt capacity fills up while cleaning a tile, tile not fully clean
        System.out.println("\nCleanSweepNotCleanTileDirtCapacityFull()");
        cleaningCleanSweep.traverseUp(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()-1]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getCurrentTile());
        cleaningCleanSweep.traverseDown(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()+1]);
        cleaningCleanSweep.traverseRight(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()+1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getCurrentTile());
        cleaningCleanSweep.traverseLeft(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()-1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.traverseDown(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()+1]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getCurrentTile());
        assertAll(
                () -> assertFalse(cleaningCleanSweep.getCurrentTile().cleanTile),
                () -> assertEquals(50, cleaningCleanSweep.getDirtCapacity())
        );
    }

    @Test
    public void CleanSweepCleanCleanTile() {
        System.out.println("\nCleanSweepCleanCleanTile()");
        cleaningCleanSweep.traverseUp(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()-1]);
        cleaningCleanSweep.traverseUp(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()-1]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getCurrentTile());
        assertAll(
                () -> assertTrue(cleaningCleanSweep.getCurrentTile().cleanTile),
                () -> assertEquals(0, cleaningCleanSweep.getDirtCapacity()),
                () -> assertEquals(0,cleaningCleanSweep.getYPos())
        );
    }

    // Empty Dirt Container Test

    @Test
    public void CleanSweepEmptyDirtContainer() {
        System.out.println("\nCleanSweepEmptyDirtContainer()");
        cleaningCleanSweep.traverseRight(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()+1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getCurrentTile());
        cleaningCleanSweep.emptyDirtContainer();
        assertEquals(0,cleaningCleanSweep.getDirtCapacity());
    }

    // Current Tile Test

    @Test
    public void CleanSweepGetCurrentTile() {
        System.out.println("\nCleanSweepGetCurrentTile()");
        assertSame(bareFloorPlanArr[startXTilePos][startYTilePos],cleanSweep.getCurrentTile());
    }
}