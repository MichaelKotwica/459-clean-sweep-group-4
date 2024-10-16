package com.group4;

public abstract class Tile {
    protected Tile leftNext;
    protected Tile rightNext;
    protected Tile topNext;
    protected Tile bottomNext;
    protected String typeStr;

    protected int xPos;
    protected int yPos;
    protected int dirtAmount;

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

    //removed setting type str

    public int getDirtAmount() {
        return dirtAmount;
    }
    public Boolean tileClean(){
        if(this.dirtAmount == 0) return True;
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
