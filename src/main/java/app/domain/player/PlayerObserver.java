package app.domain.player;

import app.domain.movement.Movement;

public interface PlayerObserver {
    void notifyMovement(Movement movement);
}
