package com.group4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Deque;
import java.util.LinkedList;

public class DepthFirstSearch {

    private static final Logger DFSLogger = LogManager.getLogger(DepthFirstSearch.class.getName());

    public void traverse(Tile startTile, CleanSweep cleanSweep, Tile[][] floorPlanArr) {
        Deque<Tile> stack = new LinkedList<>();
        stack.push(startTile);
        cleanSweep.clean(startTile);
        while (!stack.isEmpty()) {
            Tile current = stack.pop();
            if (!current.isVisited()) {
                current.setVisited(true);

                // Do what I want to do here
                if (current.traversable()) {

                    if (adjacentTile(cleanSweep, current)) {

                        if (cleanSweep.getTile().bottomNext == current && current.traversable()) {
                            cleanSweep.traverseDown(current);
                            cleanSweep.clean(current);
                            //current.setDirtAmount(0);
                        }

                        if (cleanSweep.getTile().topNext == current && current.traversable()) {
                            cleanSweep.traverseUp(current);
                            cleanSweep.clean(current);
                            //current.setDirtAmount(0);
                        }

                        if (cleanSweep.getTile().leftNext == current && current.traversable()) {
                            cleanSweep.traverseLeft(current);
                            cleanSweep.clean(current);
                            //current.setDirtAmount(0);
                        }
                        if (cleanSweep.getTile().rightNext == current && current.traversable()) {
                            cleanSweep.traverseRight(current);
                            cleanSweep.clean(current);
                            //current.setDirtAmount(0);
                        }
                    } else {
/*
                        System.out.println("(" + current.xPos + "," + current.yPos + ") " +
                                "not adjacent to (" + cleanSweep.getTile().xPos + "," + cleanSweep.getTile().yPos + ") ");
*/
                        //DFSLogger.info("({},{}) not adjacent to ({},{}) ", current.xPos, current.yPos, cleanSweep.getTile().xPos, cleanSweep.getTile().yPos);

                        cleanSweep.moveToPosition(current.xPos, current.yPos, floorPlanArr);

                        //current.setDirtAmount(0);
                        cleanSweep.clean(current);
                    }
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
        return cleanSweep.getTile().rightNext == current;
    }
}
