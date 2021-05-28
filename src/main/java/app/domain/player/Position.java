package app.domain.player;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class Position {
    private final Board board;
    String name;
    final int value;

    Position(int value, Board board, String name) {
        this.value = value;
        this.board = board;
        this.name = name;
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
        return this.equals(board.win());
    }

    public boolean isBeyondWin() {
        return value > board.win().value;
    }

    public Position bounced() {
        if (! isBeyondWin()) return this;

        int bounced = board.win().value - (value - board.win().value);
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
        return name;
    }

}