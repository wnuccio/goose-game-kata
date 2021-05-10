package usecase.move_player;

public class RepeatedMovementBuilder {
    private SimpleMovement previousMovement;
    private MovementType type;
    private int intermediatePosition;

    public RepeatedMovementBuilder(SimpleMovement previousMovement) {
        this.previousMovement = previousMovement;
    }

    public static RepeatedMovementBuilder after(SimpleMovement previousMovement) {
        return new RepeatedMovementBuilder(previousMovement);
    }

    public RepeatedMovementBuilder becauseOf(MovementType type) {
        this.type = type;
        return this;
    }

    public RepeatedMovementBuilder insteadOf(int intermediatePosition) {
        this.intermediatePosition = intermediatePosition;
        return this;
    }

    public Movement goToPosition(int finalPosition) {
        return new RepeatedMovement(previousMovement, type, intermediatePosition, finalPosition);
    }
}
