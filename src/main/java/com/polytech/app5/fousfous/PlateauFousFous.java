package com.polytech.app5.fousfous;

import com.polytech.app5.fousfous.play.Move;
import java.util.*;
import java.util.stream.Collectors;

public final class PlateauFousFous implements Partie1 {

    private Board board;

    public PlateauFousFous() {
        this.board = new Board();
    }

    public boolean estValide(final String movementPattern, final String playerPattern) {
        final Move move = Move.fromPattern(movementPattern);
        final Player player = Player.fromPattern(playerPattern);
        final List<Move> attacks = board.getPossibleAttacks(player, move.from);
        for (Move attack : attacks) {
            if (attack.equals(move)) {
                return true;
            }
        }
        return board.getPossibleAttacks(player, move.to).size() > 0;
    }

    public boolean finDePartie() {
        final Map<Player, List<Pawn>> pawnByPlayer = Arrays.stream(board.game)
                .collect(Collectors.groupingBy(pawn -> pawn.owner));
        return !pawnByPlayer.containsKey(Player.BLACK) || !pawnByPlayer.containsKey(Player.WHITE);
    }

    public void setFromFile(String fileName) {

    }

    public void saveToFile(String fileName) {

    }

    public String[] mouvementsPossibles(final String playerPattern) {
        final Player player = Player.fromPattern(playerPattern);
        final Set<Move> possibleMoves = board.getPossibleMoves(player);
        return Arrays.asList(possibleMoves.stream().map(move -> move.toString()))
                .toArray(new String[possibleMoves.size()]);
    }

    public void play(final String movementPattern, final String playerPattern) {
        final Player player = Player.fromPattern(playerPattern);
        final Move move = Move.fromPattern(movementPattern);
        board.pushPlay(move);
    }
}