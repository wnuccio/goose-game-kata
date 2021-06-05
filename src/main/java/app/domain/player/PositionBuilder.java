package app.domain.player;

import app.domain.rules.goose.GooseRule;

import static app.domain.player.PositionRule.NO_RULE;
import static java.lang.String.valueOf;

public class PositionBuilder {
    private int value;
    private String name;
    private PositionRule rule;
    private Board board;
    private BoardBuilder boardBuilder;

    public PositionBuilder(BoardBuilder boardBuilder, Board board) {
        this.boardBuilder = boardBuilder;
        this.board = board;
        this.value = 0;
        this.name = "0";
        this.rule = NO_RULE;
    }

    public PositionBuilder withValue(int value) {
        this.value = value;
        return this.withName(valueOf(value));
    }

    public PositionBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PositionBuilder withRule(PositionRule rule) {
        this.rule = rule;
        return this;
    }

    public BoardBuilder andRule(PositionRule rule) {
        this.withRule(rule);
        this.boardBuilder.withPosition(this);
        return this.boardBuilder;
    }

    public BoardBuilder andNoRule() {
        return this.andRule(NO_RULE);
    }

    public Position build() {
        return new Position(board, value, name).attachRule(rule);
    }

    public PositionBuilder withGoose() {
        return this
            .withName(value + ", The Goose")
            .withRule(new GooseRule());
    }

    public BoardBuilder havingGoose() {
        return boardBuilder.withPosition(this.withGoose());
    }
}
