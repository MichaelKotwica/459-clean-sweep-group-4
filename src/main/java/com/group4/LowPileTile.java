package com.group4;

public class LowPileTile extends Tile {
    public LowPileTile(Tile leftNext, Tile rightNext, Tile topNext, Tile bottomNext, int xPos, int yPos) {
        super(leftNext, rightNext, topNext, bottomNext, xPos, yPos);
    }

    @Override
    public void setDirtAmount(int dirtAmount) {
        this.dirtAmount = dirtAmount;
        this.cleanTile = this.dirtAmount <= 0;
    }

    @Override
    public Boolean traversable() {
        return true;
    }

    @Override
    public String getTypeStr() {
        return "Low Pile";
    }

}
