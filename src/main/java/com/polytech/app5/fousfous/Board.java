package com.polytech.app5.fousfous;

import com.polytech.app5.fousfous.play.Move;
import com.polytech.app5.fousfous.play.Play;
import java.util.*;

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
            final Index index = new Index(i);
            final Position position = index.toPosition();
            if (position.y % 2 == 0) {
                this.game[i] = new Pawn(Player.WHITE);
            } else {
                this.game[i] = new Pawn(Player.BLACK);
            }
        }
    }

    public Pawn get(final int x, final int y) {
        return get(new Position(x, y));
    }

    public Pawn get(final Position position) {
        return get(position.toLinear());
    }

    public Pawn get(final int index) {
        return this.game[index];
    }

    public Pawn get(final Index index) {
        return get(index.value);
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

    public List<Move> getPossibleThreats(final Player player, final Position position, final Direction direction) {
        final List<Move> possibleThreats = new ArrayList<Move>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            final int step = i + 1;
            final Position nextPosition = position.next(direction, step);
            if (!nextPosition.isValid())
                break;
            final Pawn pawn = get(nextPosition);
            if (pawn.owner != Player.NONE) {
                break;
            }
            for (Direction perpendicular : direction.getPerpendicular()) {
                final Move possibleAttack = getPossibleAttack(player, nextPosition, perpendicular);
                if (possibleAttack != null) {
                    possibleThreats.add(new Move(position, nextPosition));
                }
            }
        }
        return possibleThreats;
    }

    public Move getPossibleAttack(final Player player, final Position position, final Direction direction) {
        Move attack = null;
        int i = 0;
        while (attack == null && i < Board.BOARD_SIZE) {
            final int step = i + 1;
            final Position nextPosition = position.next(direction, step);
            if (!nextPosition.isValid())
                break;
            final Pawn pawn = get(nextPosition);
            if (pawn.owner == player) {
                break;
            }
            if (pawn.owner == player.opponent()) {
                attack = new Move(position, nextPosition);
            }
            i++;
        }
        return attack;
    }

    public List<Move> getPossibleAttacks(final Player player, final Position position) {
        final List<Move> attacks = new ArrayList<Move>(Arrays.asList(getPossibleAttack(player, position, Direction.SE),
                getPossibleAttack(player, position, Direction.SW), getPossibleAttack(player, position, Direction.NE),
                getPossibleAttack(player, position, Direction.NW)));
        attacks.removeIf(attack -> attack == null);
        return attacks;
    }

    public Set<Move> getPossibleMoves(final Player player) {
        final HashSet<Move> possibleMoves = new HashSet<Move>();
        for (int i = 0; i < game.length; i++) {
            final Index index = new Index(i);
            final Pawn pawn = get(index);
            if (pawn.owner == player) {
                final Position position = index.toPosition();
                final List<Move> possibleAttacks = getPossibleAttacks(player, position);
                if (possibleAttacks.size() > 0) {
                    possibleMoves.addAll(possibleAttacks);
                } else {
                    possibleMoves.addAll(getPossibleThreats(player, position, Direction.NE));
                    possibleMoves.addAll(getPossibleThreats(player, position, Direction.NW));
                    possibleMoves.addAll(getPossibleThreats(player, position, Direction.SE));
                    possibleMoves.addAll(getPossibleThreats(player, position, Direction.SW));
                }
            }
        }
        return possibleMoves;
    }

    public String toString() {
        return Arrays.toString(this.game);
    }
}