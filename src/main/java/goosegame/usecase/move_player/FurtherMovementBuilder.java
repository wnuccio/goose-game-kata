package goosegame.usecase.move_player;

import goosegame.domain.Position;

public class FurtherMovementBuilder {
    private MovementType type;
    private Position startPosition;

    public static FurtherMovementBuilder furtherMovement() {
        return new FurtherMovementBuilder();
    }

    public FurtherMovementBuilder becauseOf(MovementType type) {
        this.type = type;
        return this;
    }

    public FurtherMovementBuilder from(Position startPosition) {
        this.startPosition = startPosition;
        return this;
    }

    public Movement goTo(Position finalPosition) {
        return new FurtherMovement(startPosition, type, finalPosition);
    }
}
