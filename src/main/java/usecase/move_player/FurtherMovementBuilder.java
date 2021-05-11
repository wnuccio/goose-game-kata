package usecase.move_player;

public class FurtherMovementBuilder {
    private Movement previousMovement;
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

    public Movement goToPosition(int finalPosition) {
        return new FurtherMovement(previousMovement, type, finalPosition);
    }
}
