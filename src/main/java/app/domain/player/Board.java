package app.domain.player;

import java.util.HashMap;
import java.util.Map;

public class Board {
    Map<Integer, Position> map;

    public Board() {
        this.map = new HashMap<>();
    }

    public void setPositions(Map<Integer, Position> map) {
        this.map = map;
    }

    public Position position(int value) {
        if ( ! map.containsKey(value)) {
            put(value, new Position(this, value, String.valueOf(value)));
        }
        return map.get(value);
    }

    public Position start() {
        return position(0);
    }

    public Position win() {
        return position(63);
    }

    private void put(int value, Position position) {
        this.map.put(value, position);
    }
}
