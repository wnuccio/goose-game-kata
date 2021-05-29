package app.domain.player;

import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.goose.GooseRule;

import java.util.HashMap;

public class Board {
    private final HashMap<Integer, Position> map;

    public Board() {
        this.map = new HashMap<>();
        put(new Position(0, this, "Start"));
        put(new Position(5, this, "5, The Goose.").attachRule(new GooseRule()));
        put(new Position(6, this, "The Bridge").attachRule(new JumpOnBridgeRule(this)));
        put(new Position(9, this, "9, The Goose.").attachRule(new GooseRule()));
        put(new Position(14, this, "14, The Goose.").attachRule(new GooseRule()));
        put(new Position(18, this, "18, The Goose.").attachRule(new GooseRule()));
        put(new Position(23, this, "23, The Goose.").attachRule(new GooseRule()));
        put(new Position(27, this, "27, The Goose.").attachRule(new GooseRule()));
        put(new Position(63, this, "63"));
    }

    public Position position(int value) {
        if (map.containsKey(value)) return map.get(value);
        String name = value <= 63 ? String.valueOf(value) : "63";
        put(new Position(value, this, name));
        return map.get(value);
    }

    public Position start() {
        return position(0);
    }

    public Position win() {
        return position(63);
    }

    private void put(Position position) {
        this.map.putIfAbsent(position.value, position);
    }
}
