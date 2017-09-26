package com.polytech.app5.fousfous;

import com.polytech.app5.fousfous.play.Play;

import java.util.Arrays;
import java.util.Stack;

public final class Board {
    public static final int BOARD_SIZE = 8;
    public static final int PAWN_PER_LINE = 4;
    public static final int TOTAL_PAWN = BOARD_SIZE * PAWN_PER_LINE;

    public final Pawn[] game;
    private final Stack<Play> plays;

    public Board() {
        this.game = new Pawn[TOTAL_PAWN];
        this.plays = new Stack<Play>();
        for (int i = 0; i < TOTAL_PAWN; i++) {
            if (i < TOTAL_PAWN / 2) {
                this.game[i] = new Pawn(Player.WHITE);
            } else {
                this.game[i] = new Pawn(Player.BLACK);
            }
        }
    }

    /* 
        boardSize = 8 // 8*8
    
        Classic position
        
        - - - - - - - - 7
        - - - - - - - -
        - - - - - - - -
        - - - - - - - -
        - - - - - - - -
        - - - - - - - A
        - - - - - - - -
        - - - - - - - - 0y
        7             0x
    
        Linear position
        0                                                             31
        - - - - - - - - A - - - - - - - - - - - - - - - - - - - - - - -
    
        ### Position
        
        cp(A) = classic(position(A)) = (0, 2)
    
        lp(A) = linear(position(A)) = 8
    
        ### Equation
        
        lp(cp(A)) = floor(x(cp(A)) / 2) + y(cp(A)) * (boardSize / 2)
                            = (0 / 2) + 2 * (8 / 2) 
                            = 8
    
        cp(lp(A)) = (floor(lp(A) * 2 % boardSize + lp(A) / (boardSize / 2) % 2, lp(A) / (boardSize / 2))
                            = (8 * 2 % 8 + 1 * (8 / 4 % 2), 8 / 4)
                            = (0, 2)
    */

    public Pawn get(final Index index) {
        return this.game[index.value];
    }

    public Pawn set(final Index index, final Pawn pawn) {
        final Pawn old = this.get(index);
        this.game[index.value] = pawn;
        return old;
    }

    public void pushPlay(final Play play) {
        play.apply(this);
        System.out.println("push play: " + play);
        this.plays.push(play);
    }

    public void popPlay() {
        final Play play = this.plays.pop();
        System.out.println("pop play: " + play);
        play.unapply(this);
    }

    public String toString() {
        return Arrays.toString(this.game);
    }
}