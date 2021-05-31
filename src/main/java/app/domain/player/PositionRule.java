package app.domain.player;

public interface PositionRule {
    PositionRule NO_RULE = playerOnTurn -> {};

    void applyTo(PlayerOnTurn playerOnTurn);
}
