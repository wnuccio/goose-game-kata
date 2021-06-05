package app.domain.player;

import static java.lang.String.valueOf;

public class BoardBuilder {

    private Board board;

    public BoardBuilder() {
        this.board = new Board();
    }

    public static BoardBuilder board() {
        return new BoardBuilder();
    }

    public PositionBuilder addPosition(int value) {
        PositionBuilder positionBuilder = new PositionBuilder(this, this.board);
        positionBuilder.withValue(value);
        return positionBuilder;
    }

    public BoardBuilder withPosition(PositionBuilder positionBuilder) {
        Position position = positionBuilder.build();
        board.addPosition(position.value(), position);
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

    private void addPositionIfAbsent(int value) {
        if (board.hasPosition(value)) return;

        board.addPosition(value, new Position(board, value, valueOf(value)));
    }
}
