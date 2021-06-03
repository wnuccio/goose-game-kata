package app.domain.player;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Integer, Position> map;

    public Board() {
        this.map = new HashMap<>();
    }

    public Position position(int value) {
        if ( ! map.containsKey(value)) {
            addPosition(value, new Position(this, value, String.valueOf(value)));
        }
        return map.get(value);
    }

    public Position start() {
        return position(0);
    }

    public Position win() {
        return position(63);
    }

    void addPosition(int value, Position position) {
        this.map.put(value, position);
    }
}
