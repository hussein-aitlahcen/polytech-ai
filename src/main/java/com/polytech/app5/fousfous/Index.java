package com.polytech.app5.fousfous;

public final class Index {
    public final int value;

    public Index(final int value) {
        this.value = value;
    }

    public Position toPosition() {
        final int boardHalfSize = Board.BOARD_SIZE / 2;
        return new Position((int) Math.floor(this.value * 2 % Board.BOARD_SIZE + this.value / boardHalfSize % 2),
                this.value / boardHalfSize);
    }
}