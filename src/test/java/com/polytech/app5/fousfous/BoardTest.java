package com.polytech.app5.fousfous;

import com.polytech.app5.fousfous.play.Move;
import junit.framework.*;

public final class BoardTest extends TestCase {

    public void testPlayRemake() {
        final Board board = new Board();
        board.pushPlay(new Move(new Position(0, 0), new Position(5, 5)));
        System.out.println(board);
        board.popPlay();
        board.popPlay();
        System.out.println(board);
    }

    public void testPositionToLinear() {
        assertPositionToLinear(0, 0, 0);
        assertPositionToLinear(2, 0, 1);
        assertPositionToLinear(4, 0, 2);
        assertPositionToLinear(6, 0, 3);
        assertPositionToLinear(1, 1, 4);
        assertPositionToLinear(3, 1, 5);
        assertPositionToLinear(5, 1, 6);
        assertPositionToLinear(7, 1, 7);
        assertPositionToLinear(0, 2, 8);
        assertPositionToLinear(2, 2, 9);
        assertPositionToLinear(4, 2, 10);
        assertPositionToLinear(6, 2, 11);
        assertPositionToLinear(1, 3, 12);
        assertPositionToLinear(3, 3, 13);
        assertPositionToLinear(5, 3, 14);
        assertPositionToLinear(7, 3, 15);
        assertPositionToLinear(0, 4, 16);
    }

    public void assertPositionToLinear(final int x, final int y, final int index) {
        final Position cp = new Position(x, y);
        final Index lp = cp.toLinear();
        final Position lpToCp = lp.toPosition();
        assertEquals(lp.value, index);
        assertEquals(cp.x, lpToCp.x);
        assertEquals(cp.y, lpToCp.y);
    }
}