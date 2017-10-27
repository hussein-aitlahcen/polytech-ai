package com.polytech.app5.fousfous;

public enum Player {
    WHITE(0, "W"), BLACK(1, "B"), NONE(-1, "-");

    public final String name;
    public final int id;

    private Player(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Player opponent() {
        return this.id == WHITE.id ? BLACK : WHITE;
    }

    public static Player fromPattern(final String name) {
        switch (name) {
        case "noir":
            return BLACK;
        case "blanc":
            return WHITE;
        default:
            return NONE;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}