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

    public BoardBuilder withWinPosition(int winPosition) {
        if (winPosition < 0) throw new IllegalArgumentException(format("Invalid end position: %s", winPosition));

        for (int i = 0; i <= winPosition; i++) {
            withPosition(i);
        }
        return this;
    }

    public BoardBuilder withPosition(int value) {
        board.addPosition(value, new Position(board, value, valueOf(value)));
        return this;
    }

    public BoardBuilder withPosition(int value, String name) {
        board.addPosition(value, new Position(board, value, name));
        return this;
    }

    public BoardBuilder withPosition(int value, String name, PositionRule rule) {
        board.addPosition(value, new Position(board, value, name).attachRule(rule));
        return this;
    }

    public Board build() {
        return board;
    }
}
