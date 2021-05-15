package domain;

import java.util.List;

import static java.util.Arrays.asList;

public class Position {
    public static final int START = 0;
    public static final int BRIDGE = 6;
    public static final int BRIDGE_TARGET = 12;
    public static final int WIN_POSITION = 63;

    private final int value;

    public Position(int value) {
        this.value = value;
    }

    public static Position of(int value) {
        return new Position(value);
    }

    public boolean hasTheGoose() {
        List<Integer> positionsWithGoose = asList(5, 9, 14, 18, 23, 27);
        return positionsWithGoose.contains(value);
    }
}
