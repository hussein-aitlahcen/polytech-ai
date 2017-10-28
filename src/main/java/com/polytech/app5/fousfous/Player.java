package com.polytech.app5.fousfous;

public enum Player {
    WHITE(0, 'b'), BLACK(1, 'n'), NONE(-1, '-');

    public final char name;
    public final int id;

    private Player(final int id, final char name) {
        this.id = id;
        this.name = name;
    }

    public Player opponent() {
        return this.id == WHITE.id ? BLACK : WHITE;
    }

    public static Player fromName(final char name) {
        for (final Player player : values()) {
            if (player.name == name) {
                return player;
            }
        }
        return NONE;
    }

    public static Player fromPattern(final String pattern) {
        switch (pattern) {
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
        return this.name + "";
    }
}