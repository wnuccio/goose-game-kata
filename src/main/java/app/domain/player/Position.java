package app.domain.player;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class Position {
    public static final Position START = Position.position(0);
    public static final Position BRIDGE = Position.position(6);
    public static final Position BRIDGE_TARGET = Position.position(12);
    public static final Position WIN = Position.position(63);

    private final int value;

    public Position(int value) {
        this.value = value;
    }

    public static Position position(int value) {
        return new Position(value);
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
        return this.equals(WIN);
    }

    public boolean isOverTheVictory() {
        return value > WIN.value;
    }

    public Position bounced() {
        if (! isOverTheVictory()) return this;

        int bounced = WIN.value - (value - WIN.value);
        return Position.position(bounced);
    }

    @Override
    public String toString() {
        return "Position{" +
                "value=" + value +
                '}';
    }

    public String value() {
        return String.valueOf(value);
    }

    public Position plus(int i) {
        return Position.position(value + i);
    }
}
