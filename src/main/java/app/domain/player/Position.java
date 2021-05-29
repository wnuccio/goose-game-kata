package app.domain.player;

import app.domain.PositionRule;
import app.domain.movement.Dice;
import app.domain.movement.PlayerOnTurn;

import java.util.Objects;

public class Position {
    private final Board board;
    String name;
    final int value;
    private PositionRule rule;

    Position(int value, Board board, String name) {
        this.value = value;
        this.board = board;
        this.name = name;
        this.rule = playerOnTurn -> {};
    }

    public boolean isWin() {
        return this.equals(board.win());
    }

    public boolean isBeyondWin() {
        return value > board.win().value;
    }

    public Position plus(Dice dice) {
        return board.position(value + dice.total());
    }

    public Position bounced() {
        if (! isBeyondWin()) return this;

        int bounced = board.win().value - (value - board.win().value);
        return board.position(bounced);
    }

    public String name() {
        return name;
    }

    public Board board() {
        return board;
    }

    public void applyAttachedRule(PlayerOnTurn playerOnTurn) {
        this.rule.applyTo(playerOnTurn);
    }

    public Position attachRule(PositionRule rule) {
        this.rule = rule;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return value == position.value;
    }

    @Override
    public String toString() {
        return "Position{" +
                "value=" + value +
                '}';
    }
}
