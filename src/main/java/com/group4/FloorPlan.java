package com.group4;

import java.util.Random;

public class FloorPlan {

    Tile[][] floorPlan;
    Random randomDirtAmount = new Random();
    Random randomTileType = new Random();

    int fixedDirtAmount = 25;

    int floorPlanLength;
    int floorPlanWidth;

    public FloorPlan(int floorPlanLength, int floorPlanWidth) {
        this.floorPlanLength = floorPlanLength;
        this.floorPlanWidth = floorPlanWidth;

    }

    public Tile[][] createFloorPlan() {
        this.floorPlan = new Tile[floorPlanLength + 1][floorPlanWidth + 1];

        // Create Empty Floor Plan
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
                    floorPlan[i][j] = new ObstacleTile(null, null, null, null, i, j);
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
            }
        }
    }

    public void printFloorPlan(int floorPlanLength, int floorPlanWidth, Tile[][] floorPlan) {
        // Print Floor Plan
        for (int i = 0; i < floorPlanLength; i++) {
            for (int j = 0; j < floorPlanWidth; j++) {

                System.out.println(floorPlan[i][j].toString());

            }
        }
    }

}