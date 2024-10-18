package com.group4;

public class StairDeclineTile extends Tile {
    public StairDeclineTile(Tile leftNext, Tile rightNext, Tile topNext, Tile bottomNext, int xPos, int yPos) {
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
        this.dirtAmount = 0;
    }

    @Override
    public String getTypeStr() {
        return "Stair / Decline";
    }

    @Override
    public void removeDirt() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean traversable() {
        return false;
    }
}
