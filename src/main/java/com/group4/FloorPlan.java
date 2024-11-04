package com.group4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Random;

public class FloorPlan {

    Tile[][] floorPlan;
    final Random randomDirtAmount = new Random();
    final Random randomTileType = new Random();

    final int fixedDirtAmount = 1;

    final int floorPlanLength;
    final int floorPlanWidth;

    private static final Logger floorPlanLogger = LogManager.getLogger(FloorPlan.class.getName());

    public FloorPlan(int floorPlanLength, int floorPlanWidth) {
        this.floorPlanLength = floorPlanLength;
        this.floorPlanWidth = floorPlanWidth;
    }

    public Tile[][] createFloorPlan() {
        this.floorPlan = new Tile[floorPlanLength + 1][floorPlanWidth + 1];

        // Create Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                //System.out.println(i + ", " + j);

                int tileType = randomTileType.nextInt(4);
                //System.out.println(tileType);

                if (tileType == 0) {
                    floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
                } else if (tileType == 1) {
                    floorPlan[i][j] = new LowPileTile(null, null, null, null, i, j);
                } else if (tileType == 2) {
                    floorPlan[i][j] = new HighPileTile(null, null, null, null, i, j);
                } else {
                    floorPlan[i][j] = new Obstacle(null, null, null, null, i, j);
                }
            }
        }
        return floorPlan;
    }

    public Tile[][] createSampleFloorPlan() {
        this.floorPlan = new Tile[floorPlanLength + 1][floorPlanWidth + 1];

        // Guest Bedroom B
        floorPlanLogger.info("Guest Bedroom B");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                floorPlan[i][j] = new LowPileTile(null, null, null, null, i, j);
            }
        }
        floorPlan[0][1] = new Obstacle(null, null, null, null, 0, 1);
        floorPlan[1][1] = new Obstacle(null, null, null, null, 1, 1);
        floorPlan[5][0] = new Obstacle(null, null, null, null, 5, 0);

        // Top Middle Bathroom
        floorPlanLogger.info("Top Middle Bathroom");
        for (int i = 6; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }

        //Top Right Closet
        floorPlanLogger.info("Top Right Closet");
        for (int i = 12; i < 14; i++) {
            for (int j = 0; j < 3; j++) {
                floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }

        // End of Hallway Bathroom
        floorPlanLogger.info("End of Hallway Bathroom");
        for (int i = 6; i < 9; i++) {
            for (int j = 1; j < 2; j++) {
                floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }

        // Master Bedroom upper section
        floorPlanLogger.info("Master Bedroom");
        for (int i = 9; i < 12; i++) {
            for (int j = 1; j < 3; j++) {
                floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }

        // Master Bedroom main section
        for (int i = 9; i < floorPlanLength; i++) {
            for (int j = 3; j < floorPlanWidth; j++) {
                // Above High Pile Carpet
                if (j == 3) {
                    floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
                }
                // High Pile carpet section
                if (j >= 4 && j <= 8) {
                    if (i == 9 || i == 13) {
                        floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
                    } else {
                        floorPlan[i][j] = new HighPileTile(null, null, null, null, i, j);
                    }
                }
                // Below High Pile Carpet
                if (j == 9) {
                    floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
                }
            }
        }

        // Hallway
        floorPlanLogger.info("Hallway");
        for (int i = 6; i < 9; i++) {
            for (int j = 2; j < floorPlanWidth; j++) {
                if (i == 6 && j == 9) {
                    floorPlan[i][j] = new StairDeclineTile(null, null, null, null, i, j);
                } else {
                    floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
                }
            }
        }
        floorPlan[7][3] = new ChargingStation(null, null, null, null, 7, 3);

        // Left Closet on Left Side
        floorPlanLogger.info("Left Closet on Left Side");
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 4; j++) {
                floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }

        // Right Closet on Left Side
        floorPlanLogger.info("Right Closet on Left Side");
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 4; j++) {
                floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }
        
        // Left Closet between Guest Bedroom A and smaller closets
        floorPlanLogger.info("Left Closet Between Guest Bedroom A and Smaller Closets");
        for (int i = 0; i < 6; i++) {
            for (int j = 4; j < 5; j++) {
                floorPlan[i][j] = new BareFloorTile(null, null, null, null, i, j);
            }
        }

        // Guest Bedroom A
        floorPlanLogger.info("Guest Bedroom A");
        for (int i = 0; i < 6; i++) {
            for (int j = 5; j < floorPlanWidth; j++) {
                floorPlan[i][j] = new LowPileTile(null, null, null, null, i, j);
            }
        }

        floorPlan[0][5] = new ChargingStation(null, null, null, null, 0, 5);

        floorPlanLogger.info("createSampleFloorPlan Complete");

        return floorPlan;
    }

    public void addSampleFloorPlanWalls() {

        // Horizontal Walls
        addWall(floorPlan[0][2],floorPlan[0][3]);
        addWall(floorPlan[0][3],floorPlan[0][4]);
        addWall(floorPlan[0][4],floorPlan[0][5]);
        addWall(floorPlan[1][3],floorPlan[1][4]);
        addWall(floorPlan[1][4],floorPlan[1][5]);
        addWall(floorPlan[2][2],floorPlan[2][3]);
        addWall(floorPlan[2][3],floorPlan[2][4]);
        addWall(floorPlan[2][4],floorPlan[2][5]);
        addWall(floorPlan[3][2],floorPlan[3][3]);
        addWall(floorPlan[3][3],floorPlan[3][4]);
        addWall(floorPlan[4][2],floorPlan[4][3]);
        addWall(floorPlan[4][3],floorPlan[4][4]);
        addWall(floorPlan[4][4],floorPlan[4][5]);
        addWall(floorPlan[5][2],floorPlan[5][3]);
        addWall(floorPlan[5][3],floorPlan[5][4]);
        addWall(floorPlan[5][4],floorPlan[5][5]);
        addWall(floorPlan[6][0],floorPlan[6][1]);
        addWall(floorPlan[6][1],floorPlan[6][2]);
        addWall(floorPlan[7][0],floorPlan[7][1]);
        addWall(floorPlan[8][0],floorPlan[8][1]);
        addWall(floorPlan[8][1],floorPlan[8][2]);
        addWall(floorPlan[9][0],floorPlan[9][1]);
        addWall(floorPlan[11][0],floorPlan[11][1]);
        addWall(floorPlan[12][2],floorPlan[12][3]);
        addWall(floorPlan[13][2],floorPlan[13][3]);

        // Vertical Walls
        addWall(floorPlan[2][3],floorPlan[3][3]);
        addWall(floorPlan[5][0],floorPlan[6][0]);
        addWall(floorPlan[5][1],floorPlan[6][1]);
        addWall(floorPlan[5][4],floorPlan[6][4]);
        addWall(floorPlan[5][5],floorPlan[6][5]);
        addWall(floorPlan[5][7],floorPlan[6][7]);
        addWall(floorPlan[5][8],floorPlan[6][8]);
        addWall(floorPlan[5][9],floorPlan[6][9]);
        addWall(floorPlan[8][1],floorPlan[9][1]);
        addWall(floorPlan[8][3],floorPlan[9][3]);
        addWall(floorPlan[8][4],floorPlan[9][4]);
        addWall(floorPlan[8][5],floorPlan[9][5]);
        addWall(floorPlan[8][6],floorPlan[9][6]);
        addWall(floorPlan[8][7],floorPlan[9][7]);
        addWall(floorPlan[8][8],floorPlan[9][8]);
        addWall(floorPlan[8][9],floorPlan[9][9]);
        addWall(floorPlan[11][0],floorPlan[12][0]);
        addWall(floorPlan[11][2],floorPlan[12][2]);

    }

    public void connectFloorPlan() {

        // Connect Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {

                // Connect Next Right
                if((floorPlan[i+1][j]) != null) {
                    //System.out.println("test");
                    floorPlan[i][j].setRightNext(floorPlan[i+1][j]);

                    if (floorPlan[i][j].traversable()) {
                        floorPlan[i][j].neighbors.add(floorPlan[i+1][j]);
                    }


                    // Connect Next Left
                    if (floorPlan[i][j].getRight().xPos - 1 == floorPlan[i][j].xPos) {
                        //    System.out.println("test");
                        floorPlan[i+1][j].setLeftNext(floorPlan[i][j]);

                        if (floorPlan[i+1][j].traversable()) {
                            floorPlan[i+1][j].neighbors.add(floorPlan[i][j]);
                        }

                    }
                }

                // Connect Next Bottom
                if((floorPlan[i][j+1]) != null) {
                    //    System.out.println("test");
                    floorPlan[i][j].setBottomNext(floorPlan[i][j+1]);

                    if (floorPlan[i][j].traversable()) {
                        floorPlan[i][j].neighbors.add(floorPlan[i][j+1]);
                    }


                    // Connect Next Top
                    if (floorPlan[i][j].getBottom().yPos - 1 == floorPlan[i][j].yPos) {
                        //            System.out.println("test");
                        floorPlan[i][j+1].setTopNext(floorPlan[i][j]);

                        if (floorPlan[i][j+1].traversable()) {
                            floorPlan[i][j+1].neighbors.add(floorPlan[i][j]);
                        }

                    }
                }
            }
        }
    }

    public void addDirt() {
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                int dirtAmount = randomDirtAmount.nextInt(4);
                //floorPlan[i][j].setDirtAmount(dirtAmount);
                floorPlan[i][j].setDirtAmount(fixedDirtAmount);
                floorPlan[i][j].cleanTile = floorPlan[i][j].getDirtAmount() == 0;
            }
        }
    }

    public void addWall(Tile t1, Tile t2) {
        // Add Vertical Wall
        if (t1.yPos == t2.yPos) {
            if (t1.xPos == t2.xPos - 1) { // t2 is to the right of t1
                //System.out.println("(" + t2.xPos + "," + t2.yPos + ") is to the right of (" + t1.xPos + "," + t1.yPos + ")");
                //floorPlanLogger.info("({},{}) is to the right of ({},{})", t2.xPos, t2.yPos, t1.xPos, t1.yPos);
                addVerticalWall(t1, t2);
            } else {
                //System.out.println("(" + t2.xPos + "," + t2.yPos + ") is not next to the right of (" + t1.xPos + "," + t1.yPos + ")");
                //floorPlanLogger.warn("({},{}) is not next to the right of ({},{})", t2.xPos, t2.yPos, t1.xPos, t1.yPos);
            }

            if (t2.xPos + 1 == t1.xPos) { // t2 is to the left of t1
                //System.out.println("(" + t2.xPos + "," + t2.yPos + ") is to the left of (" + t1.xPos + "," + t1.yPos + ")");
                //floorPlanLogger.info("({},{}) is to the left of ({},{})", t2.xPos, t2.yPos, t1.xPos, t1.yPos);
                addVerticalWall(t2, t1);
            } else {
                //System.out.println("(" + t2.xPos + "," + t2.yPos + ") is not next to the left of (" + t1.xPos + "," + t1.yPos + ")");
                //floorPlanLogger.warn("({},{}) is not next to the left of ({},{})", t2.xPos, t2.yPos, t1.xPos, t1.yPos);
            }
        } else {
            //System.out.println("(" + t1.xPos + "," + t1.yPos + ") and (" + t2.xPos + "," + t2.yPos + ") are not on the same X axis to create a vertical wall");
            //floorPlanLogger.warn("({},{}) and ({},{}) are not on the same X axis to create a vertical wall", t1.xPos, t1.yPos, t2.xPos, t2.yPos);
        }

        // Add Horizontal Wall
        if (t1.xPos == t2.xPos) {
            if (t1.yPos == t2.yPos - 1) { // t2 is below of t1
                //System.out.println("(" + t2.xPos + "," + t2.yPos + ") is below (" + t1.xPos + "," + t1.yPos + ")");
                //floorPlanLogger.info("({},{}) is below ({},{})", t2.xPos, t2.yPos, t1.xPos, t1.yPos);
                addHorizontalWall(t1, t2);
            } else {
                //System.out.println("(" + t2.xPos + "," + t2.yPos + ") is not directly below (" + t1.xPos + "," + t1.yPos + ")");
                floorPlanLogger.warn("({},{}) is not directly below ({},{})", t2.xPos, t2.yPos, t1.xPos, t1.yPos);
            }

            if (t2.yPos + 1 == t1.yPos) { // t2 is above t1
                //System.out.println("(" + t2.xPos + "," + t2.yPos + ") is above (" + t1.xPos + "," + t1.yPos + ")");
                //floorPlanLogger.info("({},{}) is above ({},{})", t2.xPos, t2.yPos, t1.xPos, t1.yPos);
                addHorizontalWall(t2, t1);
            } else {
                //System.out.println("(" + t2.xPos + "," + t2.yPos + ") is not directly above (" + t1.xPos + "," + t1.yPos + ")");
                //floorPlanLogger.warn("({},{}) is not directly above ({},{})", t2.xPos, t2.yPos, t1.xPos, t1.yPos);
            }
        } else {
            //System.out.println("(" + t1.xPos + "," + t1.yPos + ") and (" + t2.xPos + "," + t2.yPos + ") are not on the same Y axis to create a horizontal wall");
            //floorPlanLogger.warn("({},{}) and ({},{}) are not on the same Y axis to create a horizontal wall", t1.xPos, t1.yPos, t2.xPos, t2.yPos);
        }

        if (t1.yPos != t2.yPos && t1.xPos != t2.xPos) {
            //floorPlanLogger.fatal("Cannot create wall between ({},{}) and ({},{})", t1.xPos, t1.yPos, t2.xPos, t2.yPos);
            //System.out.println("Cannot create wall between " + "(" + t1.xPos + "," + t1.yPos + ") and (" + t2.xPos + "," + t2.yPos + ")");
        }

        // Otherwise tiles are not next to each other
    }

    private void addVerticalWall(Tile t1, Tile t2) {
        t1.setRightNext(null);
        t1.neighbors.remove(t2);
        t2.setLeftNext(null);
        t2.neighbors.remove(t1);
        //System.out.println("Created vertical wall between tiles " + "(" + t1.xPos + "," + t1.yPos + ") and (" + t2.xPos + "," + t2.yPos + ")");
        //floorPlanLogger.info("Created vertical wall between tiles ({},{}) and ({},{})", t1.xPos, t1.yPos, t2.xPos, t2.yPos);
    }

    private void addHorizontalWall(Tile t1, Tile t2) {
        t1.setBottomNext(null);
        t1.neighbors.remove(t2);
        t2.setTopNext(null);
        t2.neighbors.remove(t1);
        //System.out.println("Created horizontal wall between tiles " + "(" + t1.xPos + "," + t1.yPos + ") and (" + t2.xPos + "," + t2.yPos + ")");
        //floorPlanLogger.info("Created horizontal wall between tiles ({},{}) and ({},{})", t1.xPos, t1.yPos, t2.xPos, t2.yPos);
    }

    public void printFloorPlan() {
        // Print Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                System.out.println(floorPlan[i][j].toString());
            }
        }
    }

    public void representFloorPlan() {

        String[][] floorPlanStr = new String[floorPlanWidth][floorPlanLength];

        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                if (floorPlan[i][j].getClass() == BareFloorTile.class) {
                    floorPlanStr[j][i] = " B ";
                }
                if (floorPlan[i][j].getClass() == LowPileTile.class) {
                    floorPlanStr[j][i] = " / ";
                }
                if (floorPlan[i][j].getClass() == HighPileTile.class) {
                    floorPlanStr[j][i] = " \\ ";
                }
                if (floorPlan[i][j].getClass() == Obstacle.class) {
                    floorPlanStr[j][i] = " O ";
                }
                if (floorPlan[i][j].getClass() == StairDeclineTile.class) {
                    floorPlanStr[j][i] = " S ";
                }
                if (floorPlan[i][j].getClass() == ChargingStation.class) {
                    floorPlanStr[j][i] = " C ";
                }
            }
        }
        for (String[] x : floorPlanStr) {
            for (String y : x) {
                System.out.print(y);
            }
            System.out.println();
        }

        System.out.println("\n                     Key:                        ");
        System.out.println("    B = Bare Floor          / = Low Pile Carpet  ");
        System.out.println("    S = Stairs              \\ = High Pile Carpet");
        System.out.println("    O = Obstacle            C = Charging Station   ");
    }

    public void representDirt() {
        String[][] dirtStr = new String[floorPlanWidth][floorPlanLength];
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                dirtStr[j][i] = String.valueOf(floorPlan[i][j].getDirtAmount());
                if(!floorPlan[i][j].traversable()) {
                    dirtStr[j][i] = "X";
                }
            }
        }
        for (String[] x : dirtStr) {
            for (String y : x) {
                System.out.format("%5s",y);
            }
            System.out.println();
        }
    }

}