package app.domain;

import app.domain.movement.PlayerOnTurn;

public interface PositionRule {
    void applyTo(PlayerOnTurn playerOnTurn);
}
