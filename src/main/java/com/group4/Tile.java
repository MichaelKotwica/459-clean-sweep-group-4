package com.group4;

public class Tile {
    private Tile leftNext;
    private Tile rightNext;
    private Tile topNext;
    private Tile bottomNext;

    public int xPos;
    public int yPos;
    private int dirtAmount;
    public boolean cleanTile;

    public Tile(Tile leftNext, Tile rightNext, Tile topNext, Tile bottomNext, int xPos, int yPos) {
        this.leftNext = leftNext;
        this.rightNext = rightNext;
        this.topNext = topNext;
        this.bottomNext = bottomNext;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public String toString() {
        return "Tile {\n" +
                "\t(" + xPos +"," + yPos + ")" +/*
                "\n\tRightNext = " + rightNext +
                ",\n\trightNext = " + rightNext +
                ",\n\ttopNext = " + topNext +
                ",\n\tbottomNext = " + bottomNext +*/
                "Dirt Amount: " + getDirtAmount() +
                "\n}";
    }

    public void setDirtAmount(int dirtAmount) {
        this.dirtAmount = dirtAmount;
        this.cleanTile = this.dirtAmount <= 0;
    }

    public int getDirtAmount() {
        return dirtAmount;
    }

    public Tile getLeft() {
        return leftNext;
    }

    public Tile getRight() {
        return rightNext;
    }

    public Tile getTop() {
        return topNext;
    }

    public Tile getBottom() {
        return bottomNext;
    }

    public void setLeftNext(Tile leftNext) {
        this.leftNext = leftNext;
    }

    public void setRightNext(Tile rightNext) {
        this.rightNext = rightNext;
    }

    public void setTopNext(Tile topNext) {
        this.topNext = topNext;
    }

    public void setBottomNext(Tile bottomNext) {
        this.bottomNext = bottomNext;
    }

}