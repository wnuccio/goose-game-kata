package app.domain.player;

import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.goose.GooseRule;

import java.util.HashMap;

import static app.domain.player.PositionRule.NO_RULE;

public class Board {
    private final HashMap<Integer, Position> map;

    public Board() {
        this.map = new HashMap<>();
        position( 0, "Start", NO_RULE);
        position(5, "5, The Goose.", new GooseRule());
        position(6, "The Bridge", new JumpOnBridgeRule(this));
        position(9, "9, The Goose.", new GooseRule());
        position(14, "14, The Goose.", new GooseRule());
        position(18, "18, The Goose.", new GooseRule());
        position(23, "23, The Goose.", new GooseRule());
        position(27, "27, The Goose.", new GooseRule());
        position(63, "63", NO_RULE);
    }

    private void position(int value, String name, PositionRule rule) {
        put(new Position(this, value, name).attachRule(rule));
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
