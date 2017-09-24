package com.polytech.app5.fousfous;

public final class Position {

    public final int x;
    public final int y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "" + (char) ('A' + x) + y;
    }
}