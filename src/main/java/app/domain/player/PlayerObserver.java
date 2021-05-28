package app.domain.player;

import app.domain.movement.Movement;
import app.domain.rules.bouncing.BouncingMovement;

public interface PlayerObserver {
    void playerPositionChanged(Movement movement);

    void playerBounced(BouncingMovement movement);
}
