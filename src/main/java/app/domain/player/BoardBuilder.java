package app.domain.player;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.lang.String.valueOf;

public class BoardBuilder {

    private List<Integer> values;
    private Board board;

    public BoardBuilder() {
        this.board = new Board();
        this.values = new ArrayList<>();
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
        values.add(value);
        board.addPosition(value, new Position(board, value, valueOf(value)));
        return this;
    }

    public BoardBuilder withPosition(int value, String name) {
        values.add(value);
        board.addPosition(value, new Position(board, value, name));
        return this;
    }

    public BoardBuilder withPosition(int value, String name, PositionRule rule) {
        values.add(value);
        board.addPosition(value, new Position(board, value, name).attachRule(rule));
        return this;
    }

    public Board build() {
        if (!board.hasPosition(0)) this.withPosition(0);
        int max = values.stream()
                .mapToInt(v -> v)
                .max()
                .getAsInt();

        for (int i = 0; i < max; i++) {
            if (!board.hasPosition(i)) this.withPosition(i);
        }
        return board;
    }
}
