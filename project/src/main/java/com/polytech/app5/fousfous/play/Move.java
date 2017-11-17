package com.polytech.app5.fousfous.play;

import com.polytech.app5.fousfous.Board;
import com.polytech.app5.fousfous.Pawn;
import com.polytech.app5.fousfous.Position;

public final class Move implements Play {

    public static Move fromPattern(final String pattern) {
        final String[] positions = pattern.split("-");
        return new Move(new Position(positions[0]), new Position(positions[1]));
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof Move) {
            final Move move = (Move) obj;
            return move.from.equals(from) && move.to.equals(to);
        }
        return false;
    }

    @Override
    public String toString() {
        return from + "-" + to;
    }
}