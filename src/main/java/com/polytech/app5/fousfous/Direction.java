package com.polytech.app5.fousfous;

import java.util.Arrays;
import java.util.List;

public enum Direction {

    SE(1, 1), SW(-1, 1), NE(1, -1), NW(-1, -1);

    public final int x;
    public final int y;

    private Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public List<Direction> getPerpendicular() {
        if (this == SE || this == NW) {
            return Arrays.asList(SW, NE);
        }
        return Arrays.asList(SE, NW);
    }
}