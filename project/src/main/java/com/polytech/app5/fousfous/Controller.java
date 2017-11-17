package com.polytech.app5.fousfous;

import com.polytech.app5.fousfous.play.*;
import com.polytech.app5.fousfous.*;
import fousfous.*;
import java.util.*;
import java.util.stream.*;

public final class Controller implements IJoueur {

    private final Heuristic heuristic;
    private final Algorithm algorithm;
    private final Board board;
    private final PlateauFousFous game;
    private Player player;

    public Controller() {
        this(Heuristic.BASIC_WIN_LOSE, Algorithm.NEGAMAX);
    }

    public Controller(final Heuristic heuristic, final Algorithm algorithm) {
        this.heuristic = heuristic;
        this.algorithm = algorithm;
        this.board = new Board();
        this.game = new PlateauFousFous(this.board);
    }

    public void initJoueur(final int id) {
        this.player = Player.fromId(id);
    }

    public int getNumJoueur() {
        return this.player.id;
    }

    public String choixMouvement() {
        final Move bestMove = algorithm.findBestMove(this.heuristic, this.board, this.player);
        this.board.pushPlay(bestMove);
        return bestMove.toString();
    }

    public void declareLeVainqueur(final int colour) {
    }

    public void mouvementEnnemi(final String coup) {
        this.game.play(coup, this.player.opponent().fullName);
    }

    public String binoName() {
        return "Hussein & MaAargöwxXx";
    }
}
