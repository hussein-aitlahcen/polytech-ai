package com.polytech.app5.fousfous.play;

import com.polytech.app5.fousfous.Board;
import com.polytech.app5.fousfous.Pawn;
import com.polytech.app5.fousfous.Position;

public final class Move implements Play {
    public final Position from;
    public final Position to;

    public Move(final Position from, final Position to) {
        this.from = from;
        this.to = to;
    }

    public void apply(final Board board) {
        apply(board, this.from, this.to);
    }

    public void unapply(final Board board) {
        apply(board, this.to, this.from);
    }

    private void apply(final Board board, final Position a, final Position b) {
        final Pawn pawn = board.get(a.toLinear());
        board.set(a.toLinear(), Pawn.PLACEHOLDER);
        final Pawn target = board.get(b.toLinear());
        if (target != Pawn.PLACEHOLDER) {
            board.pushPlay(new Remove(b, target));
        }
        board.set(b.toLinear(), pawn);
    }

    public String toString() {
        return "(move: " + from + "-" + to + ")";
    }
}