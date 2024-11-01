package com.group4;

import java.util.Deque;
import java.util.LinkedList;

public class DepthFirstSearch {

    public void traverse (Tile startTile, CleanSweep cleanSweep, FloorPlan floorPlan) {
        Deque<Tile> stack = new LinkedList<>();
        stack.push(startTile);
        cleanSweep.clean(startTile);
        while (!stack.isEmpty()) {
            Tile current = stack.pop();
            if (!current.isVisited()) {
                current.setVisited(true);

                // Do what I want to do here
                if (adjacentTile(cleanSweep, current)) {
                    if (cleanSweep.getTile().bottomNext == current) {
                        cleanSweep.traverseDown(current);
                        //cleanSweep.clean(current);
                        current.setDirtAmount(0);
                    }

                    if (cleanSweep.getTile().topNext == current) {
                        cleanSweep.traverseUp(current);
                        //cleanSweep.clean(current);
                        current.setDirtAmount(0);
                    }

                    if (cleanSweep.getTile().leftNext == current) {
                        cleanSweep.traverseLeft(current);
                        //cleanSweep.clean(current);
                        current.setDirtAmount(0);
                    }
                    if (cleanSweep.getTile().rightNext == current) {
                        cleanSweep.traverseRight(current);
                        //cleanSweep.clean(current);
                        current.setDirtAmount(0);
                    }
                } else {
                    System.out.println("(" + current.xPos + "," + current.yPos + ") " +
                            "not adjacent to ("+ cleanSweep.getTile().xPos + "," + cleanSweep.getTile().yPos + ") ");
                    //current.setDirtAmount(0);
                }

//                System.out.println("========================================================================");
//                floorPlan.representDirt();
//                System.out.println("========================================================================");

                //System.out.println(current.getxPos() + "," + current.getyPos());


                current.getNeighbors().forEach(stack::push);
            }

        }
    }

    boolean adjacentTile(CleanSweep cleanSweep, Tile current) {
        if (cleanSweep.getTile().bottomNext == current) {
            return true;
        }
        if (cleanSweep.getTile().topNext == current) {
            return true;
        }
        if (cleanSweep.getTile().leftNext == current) {
            return true;
        }
        if (cleanSweep.getTile().rightNext == current) {
            return true;
        }
        return false;
    }
}
