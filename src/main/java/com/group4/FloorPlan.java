package com.group4;

import java.util.Random;

public class FloorPlan {

    Tile[][] floorPlan;
    final Random randomDirtAmount = new Random();
    final Random randomTileType = new Random();

    //final int fixedDirtAmount = 25;

    final int floorPlanLength;
    final int floorPlanWidth;

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

    public void connectFloorPlan() {

        // Connect Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {

                // Connect Next Right
                if((floorPlan[i+1][j]) != null) {
                    //System.out.println("test");
                    floorPlan[i][j].setRightNext(floorPlan[i+1][j]);

                    // Connect Next Left
                    if (floorPlan[i][j].getRight().xPos - 1 == floorPlan[i][j].xPos) {
                        //    System.out.println("test");
                        floorPlan[i+1][j].setLeftNext(floorPlan[i][j]);
                    }
                }

                // Connect Next Bottom
                if((floorPlan[i][j+1]) != null) {
                    //    System.out.println("test");
                    floorPlan[i][j].setBottomNext(floorPlan[i][j+1]);

                    // Connect Next Top
                    if (floorPlan[i][j].getBottom().yPos - 1 == floorPlan[i][j].yPos) {
                        //            System.out.println("test");
                        floorPlan[i][j+1].setTopNext(floorPlan[i][j]);
                    }
                }
            }
        }
    }

    public void addDirt() {
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                int dirtAmount = randomDirtAmount.nextInt(10);
                floorPlan[i][j].setDirtAmount(dirtAmount);
                floorPlan[i][j].cleanTile = floorPlan[i][j].getDirtAmount() == 0;
            }
        }
    }

    public void addWall(Tile t1, Tile t2) {
        // Add Vertical Wall
        if (t1.yPos == t2.yPos) {
            if (t1.xPos == t2.xPos - 1) { // t2 is to the right of t1
                System.out.println("(" + t2.xPos + "," + t2.yPos + ") is to the right of (" + t1.xPos + "," + t1.yPos + ")");
                addVerticalWall(t1, t2);
            } else {
                System.out.println("(" + t2.xPos + "," + t2.yPos + ") is not next to the right of (" + t1.xPos + "," + t1.yPos + ")");
            }

            if (t2.xPos + 1 == t1.xPos) { // t2 is to the left of t1
                System.out.println("(" + t2.xPos + "," + t2.yPos + ") is to the left of (" + t1.xPos + "," + t1.yPos + ")");
                addVerticalWall(t2, t1);
            } else {
                System.out.println("(" + t2.xPos + "," + t2.yPos + ") is not next to the left of (" + t1.xPos + "," + t1.yPos + ")");
            }
        } else {
            System.out.println("(" + t1.xPos + "," + t1.yPos + ") and (" + t2.xPos + "," + t2.yPos + ") are not on the same X axis to create a vertical wall");
        }

        // Add Horizontal Wall
        if (t1.xPos == t2.xPos) {
            if (t1.yPos == t2.yPos - 1) { // t2 is below of t1
                System.out.println("(" + t2.xPos + "," + t2.yPos + ") is below (" + t1.xPos + "," + t1.yPos + ")");
                addHorizontalWall(t1, t2);
            } else {
                System.out.println("(" + t2.xPos + "," + t2.yPos + ") is not directly below (" + t1.xPos + "," + t1.yPos + ")");
            }

            if (t2.yPos + 1 == t1.yPos) { // t2 is above t1
                System.out.println("(" + t2.xPos + "," + t2.yPos + ") is above (" + t1.xPos + "," + t1.yPos + ")");
                addHorizontalWall(t2, t1);
            } else {
                System.out.println("(" + t2.xPos + "," + t2.yPos + ") is not directly above (" + t1.xPos + "," + t1.yPos + ")");
            }
        } else {
            System.out.println("(" + t1.xPos + "," + t1.yPos + ") and (" + t2.xPos + "," + t2.yPos + ") are not on the same Y axis to create a horizontal wall");
        }

        if (t1.yPos != t2.yPos && t1.xPos != t2.xPos) {
            System.out.println("Cannot create wall between " + "(" + t1.xPos + "," + t1.yPos + ") and (" + t2.xPos + "," + t2.yPos + ")");
        }

        // Otherwise tiles are not next to each other
    }

    private void addVerticalWall(Tile t1, Tile t2) {
        t1.setRightNext(null);
        t2.setLeftNext(null);
        System.out.println("Created vertical wall between tiles " + "(" + t1.xPos + "," + t1.yPos + ") and (" + t2.xPos + "," + t2.yPos + ")");
    }

    private void addHorizontalWall(Tile t1, Tile t2) {
        t1.setBottomNext(null);
        t2.setTopNext(null);
        System.out.println("Created horizontal wall between tiles " + "(" + t1.xPos + "," + t1.yPos + ") and (" + t2.xPos + "," + t2.yPos + ")");
    }

    public void printFloorPlan() {
        // Print Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {
                System.out.println(floorPlan[i][j].toString());
            }
        }
    }

}