package com.polytech.app5.fousfous;

public final class Position {

    public final int x;
    public final int y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Index toLinear() {
        return new Index(x / 2 + y * (Board.BOARD_SIZE / 2));
    }

    @Override
    public String toString() {
        return "" + (char) ('A' + x) + y;
    }
}