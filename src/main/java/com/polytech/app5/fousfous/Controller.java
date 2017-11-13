package com.polytech.app5.fousfous;

import fousfous.*;

public final class Controller implements IJoueur {

    private final PlateauFousFous game;
    private Player player;

    public Controller() {
        this.game = new PlateauFousFous();
    }

    public void initJoueur(int id) {
        this.player = Player.fromId(id);
    }

    public int getNumJoueur() {
        return this.player.id;
    }

    public String choixMouvement() {
        final String[] possibleMoves = this.game.mouvementsPossibles(this.player.fullName);
        System.out.println("PLAYING");
        System.out.println(game);
        return possibleMoves[0];
    }

    public void declareLeVainqueur(int colour) {
    }

    public void mouvementEnnemi(String coup) {
    }

    public String binoName() {
        return "Hussein & MaAargöwxXx";
    }
}
