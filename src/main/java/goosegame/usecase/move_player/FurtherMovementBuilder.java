package goosegame.usecase.move_player;

import goosegame.domain.Position;

public class FurtherMovementBuilder {
    private final Movement previousMovement;
    private MovementType type;

    public FurtherMovementBuilder(Movement previousMovement) {
        this.previousMovement = previousMovement;
    }

    public static FurtherMovementBuilder after(Movement previousMovement) {
        return new FurtherMovementBuilder(previousMovement);
    }

    public FurtherMovementBuilder becauseOf(MovementType type) {
        this.type = type;
        return this;
    }

    public Movement goToPosition(Position finalPosition) {
        return new FurtherMovement(previousMovement.finalPosition(), type, finalPosition);
    }
}
