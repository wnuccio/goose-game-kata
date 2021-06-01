package app.domain.player;

import static java.lang.String.format;
import static java.lang.String.valueOf;

public class BoardBuilder {

    private Board board;

    public BoardBuilder() {
        this.board = new Board();
    }

    public static BoardBuilder board() {
        return new BoardBuilder();
    }

    public BoardBuilder sized(int start, int end) {
        if (start < 0 || start > end) throw new IllegalArgumentException(format("Invalid size: %s, %s", start, end));

        for (int i=start; i <= end; i++) {
            position(i);
        }
        return this;
    }

    public BoardBuilder position(int value) {
        board.map.put(value, new Position(board, value, valueOf(value)));
        return this;
    }

    public BoardBuilder position(int value, String name, PositionRule rule) {
        board.map.put(value, new Position(board, value, name).attachRule(rule));
        return this;
    }

    public Board build() {
        return board;
    }
}
