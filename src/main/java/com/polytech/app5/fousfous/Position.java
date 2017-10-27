package com.polytech.app5.fousfous;

public final class Position {

    public final int x;
    public final int y;

    public Position(final String pattern) {
        this(pattern.charAt(0) - 'A', Integer.parseInt(pattern.charAt(1) + "") - 1);
    }

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public Position next(final Direction direction, final int step) {
        return next(direction.x * step, direction.y * step);
    }

    public Position next(final int x, final int y) {
        return new Position(this.x + x, this.y + y);
    }

    public boolean isValid() {
        return x >= 0 && x < Board.BOARD_SIZE && y >= 0 && y < Board.BOARD_SIZE;
    }

    public Index toLinear() {
        return new Index(x / 2 + y * (Board.BOARD_SIZE / 2));
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof Position) {
            final Position position = (Position) obj;
            return position.x == x && position.y == y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + (char) ('A' + x) + (y + 1);
    }
}