package app.domain.player;

import static app.domain.player.PositionRule.NO_RULE;

public class PositionBuilder {
    private int value;
    private String name;
    private PositionRule rule;
    private Board board;

    public PositionBuilder(Board board) {
        this.board = board;
        this.value = 0;
        this.name = "0";
        this.rule = NO_RULE;
    }

    public PositionBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    public PositionBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PositionBuilder withRule(PositionRule rule) {
        this.rule = rule;
        return this;
    }

    public Position build() {
        return new Position(board, value, name).attachRule(rule);
//        this.boardBuilder.addPosition(value, position);
    }
}
