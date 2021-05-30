package app.domain.player;

import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.goose.GooseRule;

import java.util.HashMap;

public class Board {
    private final HashMap<Integer, Position> map;

    public Board() {
        this.map = new HashMap<>();
        put(new Position(this, 0, "Start"));
        put(new Position(this, 5, "5, The Goose.").attachRule(new GooseRule()));
        put(new Position(this, 6, "The Bridge").attachRule(new JumpOnBridgeRule(this)));
        put(new Position(this, 9, "9, The Goose.").attachRule(new GooseRule()));
        put(new Position(this, 14, "14, The Goose.").attachRule(new GooseRule()));
        put(new Position(this, 18, "18, The Goose.").attachRule(new GooseRule()));
        put(new Position(this, 23, "23, The Goose.").attachRule(new GooseRule()));
        put(new Position(this, 27, "27, The Goose.").attachRule(new GooseRule()));
        put(new Position(this, 63, "63"));
    }

    public Position position(int value) {
        if ( ! map.containsKey(value)) {
            put(new Position(this, value, String.valueOf(value)));
        }
        return map.get(value);
    }

    public Position start() {
        return position(0);
    }

    public Position win() {
        return position(63);
    }

    private void put(Position position) {
        this.map.put(position.value, position);
    }
}
