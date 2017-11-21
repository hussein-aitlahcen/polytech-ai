
package com.polytech.app5.fousfous;

import java.util.*;
import java.util.function.*;
import com.polytech.app5.fousfous.play.*;

public interface Algorithm {

    Move findBestMove(final Heuristic heuristic, final Board board, final Player player);

    public static final double DEPTH_PER_MOVE = 0.33;
    public static final int MIN_DEPTH = 2;
    public static final int MAX_DEPTH = 9;

    public static final Algorithm TEUBE = (final Heuristic heuristic, final Board board, final Player player) -> {
        final Set<Move> moves = board.getPossibleMoves(player);
        System.out.println(Arrays.toString(moves.toArray()));
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        for(final Move move : moves) {
            final int score = board.playAndCancel(move, (__) -> heuristic.computeScore(board, player));
            System.out.println("computing move " + move + ", score=" + score + ", max=" + bestScore);
            if(score > bestScore || bestMove == null) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    };

    public static final Algorithm NEGAMAX = (final Heuristic heuristic, final Board board, final Player player) -> {
        final Set<Move> moves = board.getPossibleMoves(player);
        final int maxDepth = Integer.min(MAX_DEPTH, Integer.max(MIN_DEPTH, MAX_DEPTH - (int)Math.ceil(DEPTH_PER_MOVE * moves.size())));
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        for(final Move move : moves) {
            final int score = board.playAndCancel(move, (__) -> -negaMax(heuristic, board, player.opponent(), maxDepth, -1));
            System.out.println("computing move " + move + ", score=" + score + ", max=" + bestScore);
            if(score > bestScore || bestMove == null) {
                bestScore = score;
                bestMove = move;
            }
        }
        if(bestScore == Integer.MAX_VALUE) {
            System.out.println("WE WIN WITH THIS MOVE");
        }
        return bestMove;
    };

    public static int negaMax(final Heuristic heuristic, final Board board, final Player player, final int depth, final int signum) {
        final Set<Move> moves = board.getPossibleMoves(player);
        if(moves.size() == 0 || depth == 0) {
            return signum * heuristic.computeScore(board, player);
        }
        int bestScore = Integer.MIN_VALUE;
        for(final Move move: moves) {
            bestScore = Integer.max(bestScore, board.playAndCancel(move, (__) -> -negaMax(heuristic, board, player.opponent(), depth - 1, -signum)));
        }
        return bestScore;
    }
}
