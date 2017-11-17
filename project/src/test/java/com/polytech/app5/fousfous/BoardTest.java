package com.polytech.app5.fousfous;

import com.polytech.app5.fousfous.play.Move;
import junit.framework.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;

public final class BoardTest extends TestCase {

    public void testSaveFormat() throws IOException {
        final String filePath = "./game.txt";
        final Board board = new Board();
        final String savedData = board.save();
        Files.write(Paths.get(filePath), savedData.getBytes());
        System.out.println(savedData);
        final List<String> loadedData = Files.readAllLines(Paths.get(filePath));
        final Board reloaded = Board.fromSave(loadedData);
        System.out.println(reloaded.toString());
        assertEquals(board.toString(), reloaded.toString());
    }

    public void testPossibleMoves() {
        final Board board = new Board();
        System.out.println(board);
        System.out.println(Arrays.toString(board.getPossibleMoves(Player.WHITE).toArray()));
    }

    public void testPlayRemake() {
        final Board board = new Board();
        final String initialState = board.toString();
        board.pushPlay(new Move(new Position("A1"), new Position("B2")));
        board.popPlay();
        board.popPlay();
        assertEquals(initialState, board.toString());
    }

    public void testPositionPattern() {
        assertPositionPattern("A1", 0, 0);
        assertPositionPattern("A2", 0, 1);
        assertPositionPattern("C5", 2, 4);
        assertPositionPattern("H8", 7, 7);
        assertPositionPattern("H1", 7, 0);
    }

    public void assertPositionPattern(final String pattern, final int x, final int y) {
        final Position position = new Position(pattern);
        assertEquals(x, position.x);
        assertEquals(y, position.y);
    }

    public void testPositionToLinear() {
        assertPositionToLinear(1, 0, 0);
        assertPositionToLinear(3, 0, 1);
        assertPositionToLinear(5, 0, 2);
        assertPositionToLinear(7, 0, 3);
        assertPositionToLinear(0, 1, 4);
        assertPositionToLinear(2, 1, 5);
        assertPositionToLinear(4, 1, 6);
        assertPositionToLinear(6, 1, 7);
        assertPositionToLinear(1, 2, 8);
        assertPositionToLinear(3, 2, 9);
        assertPositionToLinear(5, 2, 10);
        assertPositionToLinear(7, 2, 11);
        assertPositionToLinear(0, 3, 12);
        assertPositionToLinear(2, 3, 13);
        assertPositionToLinear(4, 3, 14);
        assertPositionToLinear(6, 3, 15);
        assertPositionToLinear(1, 4, 16);
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
