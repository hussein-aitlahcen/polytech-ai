package com.polytech.app5.fousfous;

public interface Partie1 {
    public void setFromFile(final String fileName);

    public void saveToFile(final String fileName);

    public boolean estValide(final String move, final String player);

    public String[] mouvementsPossibles(final String player);

    public void play(final String move, final String player);

    public boolean finDePartie();
}