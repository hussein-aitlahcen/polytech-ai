package com.polytech.app5.fousfous;

public final class Board {
    public static final int DEFAULT_SIZE = 8;

    public final int size;
    private final byte[] board;

    public Board() {
        this(DEFAULT_SIZE);
    }

    public Board(final int size) {
        this.size = size;
        this.board = new byte[size];
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
    
        cp(lp(A)) = (floor(lp(A) * 2 % boardSize + 1 * lp(A) / (boardSize / 2) % 2, lp(A) / (boardSize / 2))
                            = (8 * 2 % 8 + 1 * (8 / 4 % 2), 8 / 4)
                            = (0, 2)
    */
    public int getLinearPosition(final Position position) {
        return position.x / 2 + position.y * (this.size / 2);
    }

    public Position getPlanPosition(final int linearPosition) {
        return new Position((int) Math.floor(linearPosition * 2 % size + 1 * linearPosition / (this.size / 2) % 2),
                linearPosition / (this.size / 2));
    }
}