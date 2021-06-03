package app.domain.player;

import java.util.ArrayList;
import java.util.List;

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

    public static Board standardBoard() {
        return board()
                .withPosition(63)
                .build();
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
        addAllPositionsBetweenZeroAndMax();
        return board;
    }

    private void addAllPositionsBetweenZeroAndMax() {
        for (int i = 0; i <= board.maxPositionValue(); i++) {
            addPositionIfAbsent(i);
        }
    }

    private void addPositionIfAbsent(int i) {
        if (board.hasPosition(i)) return;

        this.withPosition(i);
    }
}
