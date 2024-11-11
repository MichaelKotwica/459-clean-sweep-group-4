package com.group4;

public class Obstacle extends Tile {
    public Obstacle(Tile leftNext, Tile rightNext, Tile topNext, Tile bottomNext, int xPos, int yPos) {
        super(leftNext, rightNext, topNext, bottomNext, xPos, yPos);
    }

    @Override
    public void setDirtAmount(int dirtAmount) {
        this.dirtAmount = 0;
    }


    @Override
    public Boolean traversable() {
        return false;
    }

    @Override
    public String getTypeStr() {
        return ("Obstacle");
    }

}
