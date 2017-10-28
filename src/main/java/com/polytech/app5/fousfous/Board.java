package com.polytech.app5.fousfous;

import com.polytech.app5.fousfous.play.Move;
import com.polytech.app5.fousfous.play.Play;
import java.util.*;

public final class Board {
    public static final int BOARD_SIZE = 8;
    public static final int PAWN_PER_LINE = 4;
    public static final int TOTAL_PAWN = BOARD_SIZE * PAWN_PER_LINE;

    public static Board fromSave(List<String> lines) {
        final Pawn[][] game2d = new Pawn[BOARD_SIZE][BOARD_SIZE];
        for (final String line : lines) {
            if (!line.startsWith("%")) {
                final String[] data = line.split(" ");
                final int y = Integer.parseInt(data[0]) - 1;
                final String content = data[1];
                for (int x = 0; x < content.length(); x++) {
                    final Position position = new Position(x, y);
                    final Pawn pawn = new Pawn(Player.fromName(content.charAt(x)));
                    game2d[position.x][position.y] = pawn;
                }
            }
        }
        return from2d(game2d);
    }

    public static Board from2d(Pawn[][] game2d) {
        final Pawn[] flatGame = new Pawn[TOTAL_PAWN];
        for (int i = 0; i < TOTAL_PAWN; i++) {
            final Position position = new Index(i).toPosition();
            flatGame[i] = game2d[position.x][position.y];
        }
        return new Board(flatGame);
    }

    public final Pawn[] game;
    private final Stack<Play> plays;

    public Board(Pawn[] game) {
        this.plays = new Stack<Play>();
        this.game = game;
    }

    public Board() {
        this(new Pawn[TOTAL_PAWN]);
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
        this.plays.push(play);
    }

    public void popPlay() {
        final Play play = this.plays.pop();
        play.unapply(this);
    }

    public List<Move> getPossibleThreats(final Player player, final Position position, final Direction direction) {
        final List<Move> possibleThreats = new ArrayList<Move>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            final int step = i + 1;
            final Position nextPosition = position.next(direction, step);
            if (!nextPosition.isValid())
                break;
            final Pawn pawn = this.get(nextPosition);
            if (pawn.owner != Player.NONE) {
                break;
            }
            for (final Direction perpendicular : direction.getPerpendicular()) {
                final Move possibleAttack = this.getPossibleAttack(player, nextPosition, perpendicular);
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
            final Pawn pawn = this.get(nextPosition);
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
        final List<Move> attacks = new ArrayList<Move>(
                Arrays.asList(this.getPossibleAttack(player, position, Direction.SE),
                        this.getPossibleAttack(player, position, Direction.SW),
                        this.getPossibleAttack(player, position, Direction.NE),
                        this.getPossibleAttack(player, position, Direction.NW)));
        attacks.removeIf(attack -> attack == null);
        return attacks;
    }

    public Set<Move> getPossibleMoves(final Player player) {
        final HashSet<Move> possibleMoves = new HashSet<Move>();
        for (int i = 0; i < game.length; i++) {
            final Index index = new Index(i);
            final Pawn pawn = this.get(index);
            if (pawn.owner == player) {
                final Position position = index.toPosition();
                System.out.println("i=" + i + ", pos=" + position + ", player=" + player + ", pawn=" + pawn);
                final List<Move> possibleAttacks = this.getPossibleAttacks(player, position);
                if (possibleAttacks.size() > 0) {
                    possibleMoves.addAll(possibleAttacks);
                } else {
                    for (final Direction direction : Direction.values()) {
                        possibleMoves.addAll(this.getPossibleThreats(player, position, direction));
                    }
                }
            }
        }
        return possibleMoves;
    }

    public Pawn[][] transform2d() {
        final Pawn[][] game2d = new Pawn[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < game2d.length; i++) {
            final Pawn[] line = game2d[i];
            for (int j = 0; j < line.length; j++) {
                line[j] = Pawn.PLACEHOLDER;
            }
        }
        for (int i = 0; i < this.game.length; i++) {
            final Position position = new Index(i).toPosition();
            final Pawn pawn = this.get(position);
            game2d[position.x][position.y] = pawn;
        }
        return game2d;
    }

    public String save() {
        final StringBuilder sb = new StringBuilder();
        final Pawn[][] game2d = this.transform2d();
        sb.append("% ABCDEFGH\n");
        for (int i = 0; i < game2d.length; i++) {
            final int lineNumber = i + 1;
            sb.append(lineNumber).append(" ");
            for (int j = 0; j < game2d[i].length; j++) {
                sb.append(game2d[i][j]);
            }
            sb.append(" ").append(lineNumber);
            sb.append("\n");
        }
        sb.append("% ABCDEFGH\n");
        return sb.toString();
    }

    public String toString() {
        return Arrays.toString(this.game);
    }
}