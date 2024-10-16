package com.group4;

public class BareFloorTile extends Tile {

    public BareFloorTile(Tile leftNext, Tile rightNext, Tile topNext, Tile bottomNext, int xPos, int yPos) {
        super(leftNext, rightNext, topNext, bottomNext, xPos, yPos);
    }

    @Override
    public String toString() {
        return "Tile {\n" +
                "\tTile Location: (" + xPos +"," + yPos + ")\n" +
                "\tType: " + getTypeStr() + "\n" +
                "\tDirt Amount: " + getDirtAmount() + "\n" +
                "}";
    }

    @Override
    public void setDirtAmount(int dirtAmount) {
        this.dirtAmount = dirtAmount;
        this.cleanTile = this.dirtAmount <= 0;
    }
    public void removeDirt(){
        this.dirtAmount -= 3;
        if (this.dirtAmount<0) this.dirtAmount = 0;
    }

    @Override
    public String getTypeStr() {
        return "Bare Floor";
    }


}
