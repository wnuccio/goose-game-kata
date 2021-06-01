package app.config;

import app.domain.player.Board;
import app.domain.player.Position;
import app.domain.player.PositionRule;
import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.goose.GooseRule;

import java.util.HashMap;
import java.util.Map;

import static app.domain.player.PositionRule.NO_RULE;

public class BoardConfiguration {
    private Board board;
    private Map<Integer, Position> map;

    public Board buildBoard() {
        map = new HashMap<>();
        board = new Board();

        position( 0, "Start", NO_RULE);
        position(5, "5, The Goose.", new GooseRule());
        position(6, "The Bridge", new JumpOnBridgeRule(this.board));
        position(9, "9, The Goose.", new GooseRule());
        position(14, "14, The Goose.", new GooseRule());
        position(18, "18, The Goose.", new GooseRule());
        position(23, "23, The Goose.", new GooseRule());
        position(27, "27, The Goose.", new GooseRule());
        position(63, "63", NO_RULE);

        board.setPositions(map);
        return board;
    }

    private void position(int value, String name, PositionRule rule) {
        put(value, new Position(this.board, value, name).attachRule(rule));
    }

    private void put(int value, Position position) {
        this.map.put(value, position);
    }
}
