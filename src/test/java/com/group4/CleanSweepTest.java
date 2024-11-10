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

    int traversalFloorPlanLength;
    int traversalFloorPlanWidth;

    int traversalStartXTilePos;
    int traversalStartYTilePos;

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
    CleanSweep powerLevelCleanSweep;
    CleanSweep lowPileCleanSweep;
    CleanSweep highPileCleanSweep;
    CleanSweep lowHighPileCleanSweep;
    CleanSweep chargingCleanSweep;
    CleanSweep traversalCleanSweep;

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

    FloorPlan powerLevelFloorPlan;
    Tile[][] powerLevelFloorPlanArr;

    FloorPlan lowPileFloorPlan;
    Tile[][] lowPileFloorPlanArr;

    FloorPlan highPileFloorPlan;
    Tile[][] highPileFloorPlanArr;

    FloorPlan lowHighPileFloorPlan;
    Tile[][] lowHighPileFloorPlanArr;

    FloorPlan bareFloorWithChargingStationFloorPlan;
    Tile[][] bareFloorWithChargingStationFloorPlanArr;

    FloorPlan traversalFloorPlan;
    Tile[][] traversalFloorPlanArr;

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

        traversalFloorPlanLength = 10;
        traversalFloorPlanWidth = 10;

        traversalStartXTilePos = 2;
        traversalStartYTilePos = 7;

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

        floorPlan.connectFloorPlan();
        bareFloorPlan.connectFloorPlan();

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

        // Power Level Floor Plan
        initPowerLevelFloorPlan();

        // Low Pile Floor Plan
        initLowPileFloorPlan();

        // High Pile Floor Plan
        initHighPileFloorPlan();

        // Low High Pile Floor Plan
        initLowHighPileFloorPlan();

        // Bare Floor with Charging Station
        initBareFloorWithChargingStationFloorPlan();

        // Traversal Algorithm Floor Plan
        initTraversalFloorPlan();

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
        powerLevelCleanSweep = new CleanSweep(stuckStartXTilePos, stuckStartYTilePos, true, powerLevelFloorPlanArr[stuckStartXTilePos][stuckStartYTilePos]);
        lowPileCleanSweep = new CleanSweep(stuckStartXTilePos, stuckStartYTilePos, true, lowPileFloorPlanArr[stuckStartXTilePos][stuckStartYTilePos]);
        highPileCleanSweep = new CleanSweep(stuckStartXTilePos, stuckStartYTilePos, true, highPileFloorPlanArr[stuckStartXTilePos][stuckStartYTilePos]);
        lowHighPileCleanSweep = new CleanSweep(stuckStartXTilePos, stuckStartYTilePos, true, lowHighPileFloorPlanArr[stuckStartXTilePos][stuckStartYTilePos]);
        chargingCleanSweep = new CleanSweep(stuckStartXTilePos, stuckStartYTilePos, true, bareFloorWithChargingStationFloorPlanArr[stuckStartXTilePos][stuckStartYTilePos]);
        traversalCleanSweep = new CleanSweep(traversalStartXTilePos, traversalStartYTilePos, true, traversalFloorPlanArr[traversalStartXTilePos][traversalStartYTilePos]);
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

    void initPowerLevelFloorPlan() {
        powerLevelFloorPlan = new FloorPlan(stuckFloorPlanLength, stuckFloorPlanWidth); // 3x3
        powerLevelFloorPlanArr = powerLevelFloorPlan.createFloorPlan();

        // Make All Tiles Bare Floor
        for (int i = 0; i < stuckFloorPlanLength; i++) {
            for (int j = 0; j < stuckFloorPlanWidth; j++) {
                powerLevelFloorPlanArr[i][j] = new BareFloorTile(null,null,null,null,i,j);
            }
        }

        powerLevelFloorPlanArr[stuckStartXTilePos+1][stuckStartYTilePos] = new LowPileTile(null,null,null,null,stuckStartXTilePos+1,stuckStartYTilePos);
        powerLevelFloorPlanArr[stuckStartXTilePos][stuckStartYTilePos-1] = new HighPileTile(null,null,null,null,stuckStartXTilePos,stuckStartYTilePos-1);

        powerLevelFloorPlan.connectFloorPlan();
    }

    void initLowPileFloorPlan() {
        lowPileFloorPlan = new FloorPlan(stuckFloorPlanLength, stuckFloorPlanWidth); // 3x3
        lowPileFloorPlanArr = lowPileFloorPlan.createFloorPlan();

        // Make All Tiles Low Pile
        for (int i = 0; i < stuckFloorPlanLength; i++) {
            for (int j = 0; j < stuckFloorPlanWidth; j++) {
                lowPileFloorPlanArr[i][j] = new LowPileTile(null,null,null,null,i,j);
            }
        }

        lowPileFloorPlan.connectFloorPlan();
    }

    void initHighPileFloorPlan() {
        highPileFloorPlan = new FloorPlan(stuckFloorPlanLength, stuckFloorPlanWidth); // 3x3
        highPileFloorPlanArr = highPileFloorPlan.createFloorPlan();

        // Make All Tiles Low Pile
        for (int i = 0; i < stuckFloorPlanLength; i++) {
            for (int j = 0; j < stuckFloorPlanWidth; j++) {
                highPileFloorPlanArr[i][j] = new HighPileTile(null,null,null,null,i,j);
            }
        }

        highPileFloorPlan.connectFloorPlan();
    }

    void initLowHighPileFloorPlan() {
        lowHighPileFloorPlan = new FloorPlan(stuckFloorPlanLength, stuckFloorPlanWidth); // 3x3
        lowHighPileFloorPlanArr = lowHighPileFloorPlan.createFloorPlan();

        // Make All Tiles Low Pile
        for (int i = 0; i < stuckFloorPlanLength; i++) {
            for (int j = 0; j < stuckFloorPlanWidth; j++) {
                lowHighPileFloorPlanArr[i][j] = new LowPileTile(null,null,null,null,i,j);
            }
        }

        lowHighPileFloorPlanArr[stuckStartXTilePos+1][stuckStartYTilePos] = new HighPileTile(null,null,null,null,
                stuckStartXTilePos+1,stuckStartYTilePos);

        lowHighPileFloorPlan.connectFloorPlan();
    }

    void initBareFloorWithChargingStationFloorPlan() {
        bareFloorWithChargingStationFloorPlan = new FloorPlan(stuckFloorPlanLength, stuckFloorPlanWidth); // 3x3
        bareFloorWithChargingStationFloorPlanArr = bareFloorWithChargingStationFloorPlan.createFloorPlan();

        // Make All Bare Floor
        for (int i = 0; i < stuckFloorPlanLength; i++) {
            for (int j = 0; j < stuckFloorPlanWidth; j++) {
                bareFloorWithChargingStationFloorPlanArr[i][j] = new BareFloorTile(null,null,null,null,i,j);
            }
        }
        bareFloorWithChargingStationFloorPlanArr[0][0] = new ChargingStation(null, null, null, null,0,0);
        bareFloorWithChargingStationFloorPlan.connectFloorPlan();
    }

    void initTraversalFloorPlan() {
        traversalFloorPlan = new FloorPlan(traversalFloorPlanLength, traversalFloorPlanWidth); // 10x10
        traversalFloorPlanArr = traversalFloorPlan.createFloorPlan();

        // Make All Bare Floor
        for (int i = 0; i < traversalFloorPlanLength; i++) {
            for (int j = 0; j < traversalFloorPlanWidth; j++) {
                traversalFloorPlanArr[i][j] = new BareFloorTile(null,null,null,null,i,j);
            }
        }
        traversalFloorPlanArr[traversalStartXTilePos][traversalStartYTilePos] = new ChargingStation(null, null, null, null,traversalStartXTilePos,traversalStartYTilePos);
        traversalFloorPlan.connectFloorPlan();
        traversalFloorPlan.addDirt();

        // Vertical Walls
        traversalFloorPlan.addWall(traversalFloorPlanArr[4][1], traversalFloorPlanArr[5][1]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[4][2], traversalFloorPlanArr[5][2]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[4][3], traversalFloorPlanArr[5][3]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[4][5], traversalFloorPlanArr[5][5]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[4][6], traversalFloorPlanArr[5][6]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[4][7], traversalFloorPlanArr[5][7]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[4][8], traversalFloorPlanArr[5][8]);

        // Horizontal Walls
        traversalFloorPlan.addWall(traversalFloorPlanArr[1][4], traversalFloorPlanArr[1][5]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[2][4], traversalFloorPlanArr[2][5]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[3][4], traversalFloorPlanArr[3][5]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[4][4], traversalFloorPlanArr[4][5]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[6][4], traversalFloorPlanArr[6][5]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[7][4], traversalFloorPlanArr[7][5]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[8][4], traversalFloorPlanArr[8][5]);
        traversalFloorPlan.addWall(traversalFloorPlanArr[9][4], traversalFloorPlanArr[9][5]);
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
    public void shutdownTest() {
        System.out.println("\nshutdownTest()");
        stuckCleanSweep.shutDown();
        assertFalse(stuckCleanSweep.powerOn);
    }


    // Avoidance Tests

    // Avoid Left Tests

    @Test
    @Disabled
    public void AvoidLeftTraverseUp() {
        System.out.println("\nAvoidLeftTraverseUp()");
        int yPreLeftTraversal = upAvoidanceCleanSweep.getYPos();

        upAvoidanceCleanSweep.traverseLeft(upOpenFloorPlanArr[upAvoidanceCleanSweep.getXPos()-1][upAvoidanceCleanSweep.getYPos()]);

        int yPostLeftTraversal = upAvoidanceCleanSweep.getYPos();

        assertEquals(yPreLeftTraversal - 1,yPostLeftTraversal);
    }

    @Test
    @Disabled
    public void AvoidLeftTraverseDown() {
        System.out.println("\nAvoidLeftTraverseDown()");
        int yPreLeftTraversal = downAvoidanceCleanSweep.getYPos();

        downAvoidanceCleanSweep.traverseLeft(bottomOpenFloorPlanArr[downAvoidanceCleanSweep.getXPos()-1][downAvoidanceCleanSweep.getYPos()]);

        int yPostLeftTraversal = downAvoidanceCleanSweep.getYPos();

        assertEquals(yPreLeftTraversal + 1,yPostLeftTraversal);
    }

    @Test
    @Disabled
    public void AvoidLeftTraverseRight() {
        System.out.println("\nAvoidLeftTraverseRight()");
        int xPreLeftTraversal = rightAvoidanceCleanSweep.getXPos();

        rightAvoidanceCleanSweep.traverseLeft(rightOpenFloorPlanArr[rightAvoidanceCleanSweep.getXPos()-1][rightAvoidanceCleanSweep.getYPos()]);

        int xPostLeftTraversal = rightAvoidanceCleanSweep.getXPos();

        assertEquals(xPreLeftTraversal + 1,xPostLeftTraversal);
    }


    // Avoid Right Tests

    @Test
    @Disabled
    public void AvoidRightTraverseUp() {
        System.out.println("\nAvoidRightTraverseUp()");
        int yPreRightTraversal = upAvoidanceCleanSweep.getYPos();

        upAvoidanceCleanSweep.traverseRight(upOpenFloorPlanArr[upAvoidanceCleanSweep.getXPos()+1][upAvoidanceCleanSweep.getYPos()]);

        int yPostRightTraversal = upAvoidanceCleanSweep.getYPos();

        assertEquals(yPreRightTraversal - 1,yPostRightTraversal);
    }

    @Test
    @Disabled
    public void AvoidRightTraverseDown() {
        System.out.println("\nAvoidRightTraverseDown()");
        int yPreRightTraversal = downAvoidanceCleanSweep.getYPos();

        downAvoidanceCleanSweep.traverseRight(bottomOpenFloorPlanArr[downAvoidanceCleanSweep.getXPos()+1][downAvoidanceCleanSweep.getYPos()]);

        int yPostRightTraversal = downAvoidanceCleanSweep.getYPos();

        assertEquals(yPreRightTraversal + 1,yPostRightTraversal);
    }

    @Test
    @Disabled
    public void AvoidRightTraverseLeft() {
        System.out.println("\nAvoidRightTraverseLeft()");
        int xPreRightTraversal = leftAvoidanceCleanSweep.getXPos();

        leftAvoidanceCleanSweep.traverseRight(leftOpenFloorPlanArr[leftAvoidanceCleanSweep.getXPos()+1][leftAvoidanceCleanSweep.getYPos()]);

        int xPostRightTraversal = leftAvoidanceCleanSweep.getXPos();

        assertEquals(xPreRightTraversal - 1,xPostRightTraversal);
    }

    // Avoid Up Tests

    @Test
    @Disabled
    public void AvoidUpTraverseDown() {
        System.out.println("\nAvoidUpTraverseDown()");
        int yPreUpTraversal = downAvoidanceCleanSweep.getYPos();

        downAvoidanceCleanSweep.traverseUp(bottomOpenFloorPlanArr[downAvoidanceCleanSweep.getXPos()][downAvoidanceCleanSweep.getYPos()-1]);

        int yPostUpTraversal = downAvoidanceCleanSweep.getYPos();

        assertEquals(yPreUpTraversal + 1,yPostUpTraversal);
    }

    @Test
    @Disabled
    public void AvoidUpTraverseLeft() {
        System.out.println("\nAvoidUpTraverseLeft()");
        int xPreUpTraversal = leftAvoidanceCleanSweep.getXPos();

        leftAvoidanceCleanSweep.traverseUp(leftOpenFloorPlanArr[leftAvoidanceCleanSweep.getXPos()][leftAvoidanceCleanSweep.getYPos()-1]);

        int xPostUpTraversal = leftAvoidanceCleanSweep.getXPos();

        assertEquals(xPreUpTraversal - 1,xPostUpTraversal);
    }

    @Test
    @Disabled
    public void AvoidUpTraverseRight() {
        System.out.println("\nAvoidUpTraverseRight()");
        int xPreUpTraversal = rightAvoidanceCleanSweep.getXPos();

        rightAvoidanceCleanSweep.traverseUp(rightOpenFloorPlanArr[rightAvoidanceCleanSweep.getXPos()][rightAvoidanceCleanSweep.getYPos()-1]);

        int xPostUpTraversal = rightAvoidanceCleanSweep.getXPos();

        assertEquals(xPreUpTraversal + 1,xPostUpTraversal);
    }

    // Avoid Down Tests

    @Test
    @Disabled
    public void AvoidDownTraverseUp() {
        System.out.println("\nAvoidDownTraverseUp()");
        int yPreUpTraversal = upAvoidanceCleanSweep.getYPos();

        upAvoidanceCleanSweep.traverseDown(upOpenFloorPlanArr[upAvoidanceCleanSweep.getXPos()][upAvoidanceCleanSweep.getYPos()+1]);

        int yPostUpTraversal = upAvoidanceCleanSweep.getYPos();

        assertEquals(yPreUpTraversal - 1,yPostUpTraversal);
    }

    @Test
    @Disabled
    public void AvoidDownTraverseLeft() {
        System.out.println("\nAvoidDownTraverseLeft()");
        int xPreDownTraversal = leftAvoidanceCleanSweep.getXPos();

        leftAvoidanceCleanSweep.traverseDown(leftOpenFloorPlanArr[leftAvoidanceCleanSweep.getXPos()][leftAvoidanceCleanSweep.getYPos()+1]);

        int xPostDownTraversal = leftAvoidanceCleanSweep.getXPos();

        assertEquals(xPreDownTraversal - 1,xPostDownTraversal);
    }

    @Test
    @Disabled
    public void AvoidDownTraverseRight() {
        System.out.println("\nAvoidDownTraverseRight()");
        int xPreDownTraversal = rightAvoidanceCleanSweep.getXPos();

        rightAvoidanceCleanSweep.traverseDown(rightOpenFloorPlanArr[rightAvoidanceCleanSweep.getXPos()][rightAvoidanceCleanSweep.getYPos()+1]);

        int xPostDownTraversal = rightAvoidanceCleanSweep.getXPos();

        assertEquals(xPreDownTraversal + 1,xPostDownTraversal);
    }

    // Wall Avoidance Test
    @Test
    @Disabled
    public void CleanSweepAvoidWall() {
        System.out.println("\nCleanSweepAvoidWall()");
        cleaningFloorPlan.addWall(cleaningFloorPlanArr[2][3], cleaningFloorPlanArr[1][3]);
        cleaningCleanSweep.traverseDown(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos() + 1]);
        assertFalse(cleaningCleanSweep.traverseLeft(cleaningFloorPlanArr[cleaningCleanSweep.getXPos() - 1][cleaningCleanSweep.getYPos()]));
        cleaningCleanSweep.printPos();
    }


    // Out of Bounds Tests

    @Test
    @Disabled
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
    @Disabled
    public void notTraverseDownOutOfBounds() {
        System.out.println("\nnotTraverseUpOutOfBounds()");
        try {
            downOutBoundCleanSweep.traverseDown(stairStuckFloorPlanArr[stuckCleanSweep.getXPos()][cleanSweep.getYPos()+1]);
        } catch (Exception e) {
            e.printStackTrace();
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
        cleaningCleanSweep.clean(cleaningCleanSweep.getTile());
        assertEquals(1, cleaningCleanSweep.getDirtCapacity());
    }

    @Test
    public void CleanSweepCleanCleanUntilClean() { // Clean Sweep cleans tile until tile is clean
        System.out.println("\nCleanSweepCleanCleanUntilClean()");
        cleaningCleanSweep.traverseRight(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()+1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getTile());
        assertTrue(cleaningCleanSweep.getTile().cleanTile);
    }

    @Test
    public void CleanSweepDirtCapacityFull() { // Clean Sweep can not clean tile because dirt capacity is full
        System.out.println("\nCleanSweepDirtCapacityFull()");
        cleaningCleanSweep.traverseUp(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()-1]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getTile());
        cleaningCleanSweep.traverseDown(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()+1]);
        cleaningCleanSweep.traverseRight(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()+1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getTile());
        cleaningCleanSweep.traverseLeft(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()-1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.traverseDown(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()+1]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getTile());
        cleaningCleanSweep.traverseUp(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()-1]);
        cleaningCleanSweep.traverseLeft(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()-1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getTile());
        assertFalse(cleaningCleanSweep.getTile().cleanTile);
    }

    @Test
    public void CleanSweepNotCleanTileDirtCapacityFull() { // Clean Sweep dirt capacity fills up while cleaning a tile, tile not fully clean
        System.out.println("\nCleanSweepNotCleanTileDirtCapacityFull()");
        cleaningCleanSweep.traverseUp(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()-1]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getTile());
        cleaningCleanSweep.traverseDown(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()+1]);
        cleaningCleanSweep.traverseRight(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()+1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getTile());
        cleaningCleanSweep.traverseLeft(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()-1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.traverseDown(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()+1]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getTile());
        assertAll(
                () -> assertFalse(cleaningCleanSweep.getTile().cleanTile),
                () -> assertEquals(50, cleaningCleanSweep.getDirtCapacity())
        );
    }

    @Test
    public void CleanSweepCleanCleanTile() {
        System.out.println("\nCleanSweepCleanCleanTile()");
        cleaningCleanSweep.traverseUp(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()-1]);
        cleaningCleanSweep.traverseUp(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()][cleaningCleanSweep.getYPos()-1]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getTile());
        assertAll(
                () -> assertTrue(cleaningCleanSweep.getTile().cleanTile),
                () -> assertEquals(0, cleaningCleanSweep.getDirtCapacity()),
                () -> assertEquals(0,cleaningCleanSweep.getYPos())
        );
    }

    // Empty Dirt Container Test

    @Test
    public void CleanSweepEmptyDirtContainer() {
        System.out.println("\nCleanSweepEmptyDirtContainer()");
        cleaningCleanSweep.traverseRight(cleaningFloorPlanArr[cleaningCleanSweep.getXPos()+1][cleaningCleanSweep.getYPos()]);
        cleaningCleanSweep.clean(cleaningCleanSweep.getTile());
        cleaningCleanSweep.emptyDirtContainer();
        assertEquals(0,cleaningCleanSweep.getDirtCapacity());
    }

    // Current Tile Test

    @Test
    public void CleanSweepGetTile() {
        System.out.println("\nCleanSweepGetTile()");
        assertSame(bareFloorPlanArr[startXTilePos][startYTilePos],cleanSweep.getTile());
    }

    // Power Level Tests

    @Test
    public void CleanSweepGetSurfaceCostBareFloorTest() {
        Tile tile = bareFloorPlanArr[0][0];
        assertEquals(1.0, cleanSweep.getSurfaceCost(tile));
    }

    @Test
    public void CleanSweepGetSurfaceCostLowPileFloorTest() {
       Tile tile = new LowPileTile(null,null,null,null,0,0);
       assertEquals(2.0,cleanSweep.getSurfaceCost(tile),"surface cost for low pile is 2.0 units");
    }

    @Test
    public void CleanSweepGetSurfaceCostHighPileTest() {
        Tile tile = new HighPileTile(null, null, null,null,0,0 );
        assertEquals(3.0,cleanSweep.getSurfaceCost(tile),"Surface cost for High Pile is 3.0 units");
    }

    @Test
    public void testInitialBatteryLevel() {
        // Initialize a tile (e.g., a BareFloorTile) to set as the starting position
        Tile initialTile = new BareFloorTile(null, null, null, null, 0, 0);

        // Initialize CleanSweep starting on the initialTile
        CleanSweep cleanSweep = new CleanSweep(0, 0, true, initialTile);

        // Assert that the initial battery level is as expected
        assertEquals(250, cleanSweep.getBatteryLevel(),
                "Initial battery level should be 250.0 units");
    }

    @Test
    public void CleanSweepBatteryConsumptionBareToLowPileCleanTile() {
        double initialBattery = powerLevelCleanSweep.getBatteryLevel();
        powerLevelCleanSweep.traverseRight(powerLevelFloorPlanArr[powerLevelCleanSweep.getXPos()+1][powerLevelCleanSweep.getYPos()]);
        double expectedBatteryAfterMove = initialBattery - 1.5;
        assertEquals(expectedBatteryAfterMove, powerLevelCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepBatteryConsumptionBareToHighPileCleanTile() {
        double initialBattery = powerLevelCleanSweep.getBatteryLevel();
        powerLevelCleanSweep.traverseUp(powerLevelFloorPlanArr[powerLevelCleanSweep.getXPos()][powerLevelCleanSweep.getYPos()-1]);
        //System.out.println(powerLevelCleanSweep.getCurrentTile().getTypeStr());
        double expectedBatteryAfterMove = initialBattery - 2.0;
        assertEquals(expectedBatteryAfterMove, powerLevelCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepBatteryConsumptionBareToBareCleanTile() {
        double initialBattery = powerLevelCleanSweep.getBatteryLevel();
        powerLevelCleanSweep.traverseLeft(powerLevelFloorPlanArr[powerLevelCleanSweep.getXPos()-1][powerLevelCleanSweep.getYPos()]);
        //System.out.println(powerLevelCleanSweep.getCurrentTile().getTypeStr());
        double expectedBatteryAfterMove = initialBattery - 1.0;
        assertEquals(expectedBatteryAfterMove, powerLevelCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepBatteryConsumptionLowPileToLowPileCleanTile() {
        double initialBattery = lowPileCleanSweep.getBatteryLevel();
        lowPileCleanSweep.traverseLeft(lowPileFloorPlanArr[lowPileCleanSweep.getXPos()-1][lowPileCleanSweep.getYPos()]);
        //System.out.println(powerLevelCleanSweep.getCurrentTile().getTypeStr());
        double expectedBatteryAfterMove = initialBattery - 2.0;
        assertEquals(expectedBatteryAfterMove, lowPileCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepBatteryConsumptionHighPileToHighPileCleanTile() {
        double initialBattery = highPileCleanSweep.getBatteryLevel();
        highPileCleanSweep.traverseLeft(highPileFloorPlanArr[highPileCleanSweep.getXPos()-1][highPileCleanSweep.getYPos()]);
        //System.out.println(powerLevelCleanSweep.getCurrentTile().getTypeStr());
        double expectedBatteryAfterMove = initialBattery - 3.0;
        assertEquals(expectedBatteryAfterMove, highPileCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepBatteryConsumptionLowPileToHighPileCleanTile() {
        double initialBattery = lowHighPileCleanSweep.getBatteryLevel();
        lowHighPileCleanSweep.traverseRight(lowHighPileFloorPlanArr[lowHighPileCleanSweep.getXPos()+1][highPileCleanSweep.getYPos()]);
        //System.out.println(powerLevelCleanSweep.getCurrentTile().getTypeStr());
        double expectedBatteryAfterMove = initialBattery - 2.5;
        assertEquals(expectedBatteryAfterMove, lowHighPileCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepBatteryConsumptionBareToBareDirtyTile() {
        powerLevelFloorPlanArr[powerLevelCleanSweep.getXPos()-1][powerLevelCleanSweep.getYPos()].setDirtAmount(3);
        double initialBattery = powerLevelCleanSweep.getBatteryLevel();
        powerLevelCleanSweep.traverseLeft(powerLevelFloorPlanArr[powerLevelCleanSweep.getXPos()-1][powerLevelCleanSweep.getYPos()]);
        powerLevelCleanSweep.clean(powerLevelCleanSweep.getTile());
        //System.out.println(powerLevelCleanSweep.getCurrentTile().getTypeStr());
        double expectedBatteryAfterMove = initialBattery - 2.0;
        assertEquals(expectedBatteryAfterMove, powerLevelCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepBatteryConsumptionLowPileToLowPileDirtyTile() {
        lowPileFloorPlanArr[lowPileCleanSweep.getXPos()-1][lowPileCleanSweep.getYPos()].setDirtAmount(3);
        double initialBattery = lowPileCleanSweep.getBatteryLevel();
        lowPileCleanSweep.traverseLeft(lowPileFloorPlanArr[lowPileCleanSweep.getXPos()-1][lowPileCleanSweep.getYPos()]);
        lowPileCleanSweep.clean(lowPileCleanSweep.getTile());
        //System.out.println(powerLevelCleanSweep.getCurrentTile().getTypeStr());
        double expectedBatteryAfterMove = initialBattery - 4.0;
        assertEquals(expectedBatteryAfterMove, lowPileCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepBatteryConsumptionHighPileToHighPileDirtyTile() {
        highPileFloorPlanArr[highPileCleanSweep.getXPos()-1][highPileCleanSweep.getYPos()].setDirtAmount(3);
        double initialBattery = highPileCleanSweep.getBatteryLevel();
        highPileCleanSweep.traverseLeft(highPileFloorPlanArr[highPileCleanSweep.getXPos()-1][highPileCleanSweep.getYPos()]);
        highPileCleanSweep.clean(highPileCleanSweep.getTile());
        //System.out.println(powerLevelCleanSweep.getCurrentTile().getTypeStr());
        double expectedBatteryAfterMove = initialBattery - 6.0;
        assertEquals(expectedBatteryAfterMove, highPileCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepBatteryConsumptionBareToLowPileDirtyTile() {
        powerLevelFloorPlanArr[powerLevelCleanSweep.getXPos()+1][powerLevelCleanSweep.getYPos()].setDirtAmount(3);
        double initialBattery = powerLevelCleanSweep.getBatteryLevel();
        powerLevelCleanSweep.traverseRight(powerLevelFloorPlanArr[powerLevelCleanSweep.getXPos()+1][powerLevelCleanSweep.getYPos()]);
        powerLevelCleanSweep.clean(powerLevelCleanSweep.getTile());
        double expectedBatteryAfterMove = initialBattery - 3.5;
        assertEquals(expectedBatteryAfterMove, powerLevelCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepBatteryConsumptionBareToHighPileDirtyTile() {
        powerLevelFloorPlanArr[powerLevelCleanSweep.getXPos()][powerLevelCleanSweep.getYPos()-1].setDirtAmount(3);
        double initialBattery = powerLevelCleanSweep.getBatteryLevel();
        powerLevelCleanSweep.traverseUp(powerLevelFloorPlanArr[powerLevelCleanSweep.getXPos()][powerLevelCleanSweep.getYPos()-1]);
        powerLevelCleanSweep.clean(powerLevelCleanSweep.getTile());
        double expectedBatteryAfterMove = initialBattery - 5.0;
        assertEquals(expectedBatteryAfterMove, powerLevelCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepBatteryConsumptionLowPileToHighPileDirtyTile() {
        lowHighPileFloorPlanArr[lowHighPileCleanSweep.getXPos()+1][highPileCleanSweep.getYPos()].setDirtAmount(3);
        double initialBattery = lowHighPileCleanSweep.getBatteryLevel();
        lowHighPileCleanSweep.traverseRight(lowHighPileFloorPlanArr[lowHighPileCleanSweep.getXPos()+1][highPileCleanSweep.getYPos()]);
        lowHighPileCleanSweep.clean(lowHighPileCleanSweep.getTile());
        double expectedBatteryAfterMove = initialBattery - 5.5;
        assertEquals(expectedBatteryAfterMove, lowHighPileCleanSweep.getBatteryLevel());
    }

    @Test
    public void CleanSweepChargingTest() {
        bareFloorWithChargingStationFloorPlan.representFloorPlan();
        bareFloorWithChargingStationFloorPlan.representDirt();
        chargingCleanSweep.showBatteryPercentage();
        chargingCleanSweep.traverseRight(chargingCleanSweep.getTile().getRight());
        chargingCleanSweep.traverseUp(chargingCleanSweep.getTile().getTop());
        chargingCleanSweep.traverseLeft(chargingCleanSweep.getTile().getLeft());
        chargingCleanSweep.traverseLeft(chargingCleanSweep.getTile().getLeft());
        chargingCleanSweep.showBatteryPercentage();
        chargingCleanSweep.printPos();
        chargingCleanSweep.charge();
        chargingCleanSweep.showBatteryPercentage();
        double batteryAfterTraversals = chargingCleanSweep.getBatteryLevel();
        assertEquals(250.0,batteryAfterTraversals);
    }

    @Test
    public void TraversalAlgorithmTest() {
        System.out.println();
        traversalFloorPlan.representFloorPlan();
        System.out.println();
        traversalFloorPlan.representDirt();
        DepthFirstSearch traversalDFS = new DepthFirstSearch();
        traversalDFS.traverse(traversalCleanSweep.getTile(), traversalCleanSweep, traversalFloorPlanArr);
        System.out.println();
        traversalFloorPlan.representFloorPlan();
        System.out.println();
        traversalFloorPlan.representDirt();
        assertTrue(traversalFloorPlan.isClean());
    }

}