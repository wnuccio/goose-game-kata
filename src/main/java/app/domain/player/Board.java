package app.domain.player;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.lang.String.format;

public class Board {
    private Map<Integer, Position> map;

    public Board() {
        this.map = new HashMap<>();
    }

    public Position position(int value) {
        if (! hasPosition(value)) throw new NoSuchElementException(format("Invalid position: %s", value));

        return map.get(value);
    }

    public Position start() {
        return position(0);
    }

    public Position win() {
        int max = map
                .keySet()
                .stream()
                .mapToInt(i -> i)
                .max()
                .orElseThrow(() -> new IllegalStateException("Empty board"));

        return position(max);
    }

    void addPosition(int value, Position position) {
        this.map.put(value, position);
    }

    public boolean hasPosition(int value) {
        return map.containsKey(value);
    }
}
