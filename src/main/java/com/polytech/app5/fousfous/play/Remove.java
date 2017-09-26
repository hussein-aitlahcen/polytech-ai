package com.polytech.app5.fousfous.play;

import com.polytech.app5.fousfous.Board;
import com.polytech.app5.fousfous.Pawn;
import com.polytech.app5.fousfous.Position;

public final class Remove implements Play {

    public final Position position;
    public final Pawn pawn;

    public Remove(final Position position, final Pawn pawn) {
        this.position = position;
        this.pawn = pawn;
    }

    public void apply(final Board board) {
        board.set(this.position.toLinear(), Pawn.PLACEHOLDER);
    }

    public void unapply(final Board board) {
        board.set(this.position.toLinear(), this.pawn);
    }

    public String toString() {
        return "(remove: position=" + this.position + ", pawn=" + this.pawn + ")";
    }
}