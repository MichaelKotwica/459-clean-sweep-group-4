package com.group4;

public abstract class Tile {
    private Tile leftNext;
    private Tile rightNext;
    private Tile topNext;
    private Tile bottomNext;

    public Tile surfaceType;

    protected String typeStr;

    public int xPos;
    public int yPos;
    public int dirtAmount;
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
    public abstract String toString();

    public abstract void setDirtAmount(int dirtAmount);

    public abstract String getTypeStr();

    public abstract void removeDirt();

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
