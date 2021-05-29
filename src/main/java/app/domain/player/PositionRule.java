package app.domain.player;

public interface PositionRule {
    void applyTo(PlayerOnTurn playerOnTurn);
}
