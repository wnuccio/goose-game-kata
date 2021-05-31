package app.domain.player;

import static java.lang.String.format;

public class Position {
    private final Board board;
    String name;
    final int value;
    private PositionRule rule;

    public Position(Board board, int value, String name) {
        this.value = value;
        this.board = board;
        this.name = name;
        this.rule = playerOnTurn -> {};
    }

    public boolean isWin() {
        return value == (board.win().value);
    }

    public boolean isBeyondWin() {
        return value > board.win().value;
    }

    public Position plus(Dice dice) {
        int newValue = value + dice.total();
        int winValue = board().win().value;
        if (newValue > winValue) {
            newValue = winValue - (newValue - winValue);
        }
        return board.position(newValue);
    }

    public boolean isBeyondWinFor(Dice dice) {
        return residualMovementFor(dice) > 0;
    }

    public int residualMovementFor(Dice dice) {
        int newValue = value + dice.total();
        int maxValue = board.win().value ;
        return newValue > maxValue ? newValue - maxValue : 0;
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
    public String toString() {
        return format("Position(%s)", value);
    }
}
