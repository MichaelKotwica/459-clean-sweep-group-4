package com.group4;

public class ChargingStation extends Tile{
    public ChargingStation(Tile leftNext, Tile rightNext, Tile topNext, Tile bottomNext, int xPos, int yPos) {
        super(leftNext, rightNext, topNext, bottomNext, xPos, yPos);
    }

    @Override
    public String toString() {
        return "Tile {\n" +
                "\tTile Location: (" + xPos +"," + yPos + ")\n" +
                "\tType: " + this.getTypeStr() + "\n" +
                "\tDirt Amount: " + getDirtAmount() + "\n" +
                "}";
    }

    @Override
    public void setDirtAmount(int dirtAmount) {
        this.dirtAmount = dirtAmount;
        this.cleanTile = this.dirtAmount <= 0;
    }

    @Override
    public void removeDirt(){
        this.dirtAmount -= 1;
        if (this.dirtAmount<0) this.dirtAmount = 0;
    }

    @Override
    public String getTypeStr() {
        return ("Charging Station");
    }
}
