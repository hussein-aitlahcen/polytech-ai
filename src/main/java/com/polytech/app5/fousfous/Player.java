package com.polytech.app5.fousfous;

public enum Player {
    WHITE(-1, 'b', "blanc"), BLACK(1, 'n', "noir"), NONE(0, '-', "none");

    public final char name;
    public final int id;
    public final String fullName;

    private Player(final int id, final char name, final String fullName) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
    }

    public Player opponent() {
        return this.id == WHITE.id ? BLACK : WHITE;
    }

    public static Player fromId(final int id) {
        for(final Player player: values()) {
            if(player.id == id) {
                return player;
            }
        }
        return NONE;
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
