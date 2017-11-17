package com.polytech.app5.fousfous;

import com.polytech.app5.fousfous.play.*;

import java.util.*;

public interface Heuristic {

    int computeScore(final Board board, final Player player);

    public static final Heuristic BASIC_WIN_LOSE = (board, player) -> {
        if(board.winner() == player.opponent()) {
            return Integer.MIN_VALUE + 1;
        }
        else if(board.winner() == player) {
            return Integer.MAX_VALUE - 1;
        }
        final int numberOfPawn = board.getNumberOfPawns(player.opponent());
        return -numberOfPawn;
    };

}
