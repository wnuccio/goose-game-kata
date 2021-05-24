package _2_domain.player;

import java.util.List;
import java.util.Objects;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

public class Position {
    private final Board board;
    private final int value;

    Position(int value, Board board) {
        this.value = value;
        this.board = board;
    }

    public boolean hasTheGoose() {
        List<Integer> positionsWithGoose = asList(5, 9, 14, 18, 23, 27);
        return positionsWithGoose.contains(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return value == position.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public boolean isWin() {
        return this.equals(board.winPosition());
    }

    public boolean isBeyondWin() {
        return value > board.winPosition().value;
    }

    public Position bounced() {
        if (! isBeyondWin()) return this;

        int bounced = board.winPosition().value - (value - board.winPosition().value);
        return board.position(bounced);
    }

    @Override
    public String toString() {
        return "Position{" +
                "value=" + value +
                '}';
    }

    public Position plus(int i) {
        return board.position(value + i);
    }

    public String name() {
        if (this.isBeyondWin()) return valueOf(board.winPosition().value);
        if (this.equals(board.start())) return "Start";
        if (this.equals(board.bridge())) return "The Bridge";

        String gooseSuffix = this.hasTheGoose() ? ", The Goose." : "";
        return value + gooseSuffix;
    }

}
