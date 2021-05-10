package usecase.move_player;

import java.util.function.Supplier;

public class GenericRepeatedMovementBuilder {
    private SimpleMovement previousMovement;
    private MovementType type;
    private int intermediatePosition;

    public GenericRepeatedMovementBuilder(SimpleMovement previousMovement) {
        this.previousMovement = previousMovement;
    }

    public GenericRepeatedMovementBuilder becauseOf(MovementType type) {
        this.type = type;
        return this;
    }

    public GenericRepeatedMovementBuilder insteadOf(int intermediatePosition) {
        this.intermediatePosition = intermediatePosition;
        return this;
    }

    public Movement goToPosition(Supplier<Integer> finalPositionFunction) {
        return new GenericRepeatedMovement(previousMovement, type, intermediatePosition, finalPositionFunction);
    }

    public Movement goToPosition(int finalPosition) {
        return goToPosition(() -> finalPosition);
    }
}
