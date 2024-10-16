package com.group4;

public class Obstacle extends Tile{
    public Obstacle(Tile leftNext, Tile rightNext, Tile topNext, Tile bottomNext, int xPos, int yPos) {
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
        return;
    }
    public void removeDirt(){
        return;
    }
    @Override
    public Boolean traversible(){
        return false;
    }

    @Override
    public String getTypeStr() {
        return ("Obstacle");
    }
}
