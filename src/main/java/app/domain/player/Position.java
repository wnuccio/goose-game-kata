package app.domain.player;

import static app.domain.player.PositionRule.NO_RULE;
import static java.lang.Math.min;
import static java.lang.String.format;

public class Position {
    private final Board board;
    private final String name;
    private final int value;
    PositionRule rule;

    public Position(Board board, int value, String name) {
        this.value = value;
        this.board = board;
        this.name = name;
        this.rule = NO_RULE;
    }

    public boolean isWin() {
        return value == (board.win().value);
    }

    public Position plus(Dice dice) {
        int newValue = value + dice.total();
        int winValue = board.win().value;
        return board.position(min(newValue, winValue));
    }

    public Position minus(int i) {
        return board.position(value - i);
    }

    public int residualMovementFor(Dice dice) {
        int newValue = value + dice.total();
        int maxValue = board.win().value ;
        return newValue > maxValue ? newValue - maxValue : 0;
    }

    public String name() {
        return name;
    }

    public void applyAttachedRule(PlayerOnTurn playerOnTurn) {
        this.rule.applyTo(playerOnTurn);
    }

    public Position attachRule(PositionRule rule) {
        this.rule = rule;
        return this;
    }

    @Override
    public String toString() {
        return format("Position(%s)", value);
    }

    public boolean isEqualTo(Position otherPosition) {
        return this.board == otherPosition.board
                && this.value == otherPosition.value
                && this.name.equals(otherPosition.name)
                && this.rule == otherPosition.rule;
    }

    public int value() {
        return value;
    }

    public PositionRule rule() {
        return this.rule;
    }
}
