package com.polytech.app5.fousfous;

public final class Play {
    public final Position from;
    public final Position to;

    public Play(final Position from, final Position to) {
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return from + "-" + to;
    }
}