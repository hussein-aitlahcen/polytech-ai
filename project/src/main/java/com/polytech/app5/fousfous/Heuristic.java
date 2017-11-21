package com.polytech.app5.fousfous;

import com.polytech.app5.fousfous.play.*;

import java.util.stream.*;
import java.util.*;

public interface Heuristic {

    int computeScore(final Board board, final Player player);

    public static final Heuristic BASIC_WIN_LOSE = (board, player) -> {
        if(board.winner() == player.opponent()) {
            return Integer.MIN_VALUE;
        }
        else if(board.winner() == player) {
            return Integer.MAX_VALUE;
        }
        final int numberOfPawn = board.getNumberOfPawns(player.opponent());
        final int alignmentFactor = board.getAlignmentFactor(player);
        final Stream<Move> possibleEnemyMoves = board.getPossibleMoves(player.opponent()).stream();
        final Stream<Move> possibleAllyMoves = board.getPossibleMoves(player).stream();
        final int possibleEnemyAttacks = (int)possibleEnemyMoves.filter(board::isAttackMovement).count();
        final int possibleAllyAttaks = (int)possibleAllyMoves.filter(board::isAttackMovement).count();
        return scoreGrade(-numberOfPawn, alignmentFactor, possibleAllyAttaks, possibleEnemyAttacks);
    };


    public static int scoreGrade(final int... scores) {
        int score = 0;
        int power = (int)Math.pow(10, scores.length);
        for(int i = 0; i < scores.length; i++) {
            score += scores[i] * power;
            power = (int)Math.floor(power / 10);
        }
        return score;
    }
}
