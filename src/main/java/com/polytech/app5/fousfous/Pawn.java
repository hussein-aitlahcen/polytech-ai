package com.polytech.app5.fousfous;

public final class Pawn {

    public static final Pawn PLACEHOLDER = new Pawn(Player.NONE);

    public final Player owner;

    public Pawn(final Player owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "player=" + owner;
    }
}