package usecase.move_player;

public class RepeatedMovementBuilder {
    private Movement previousMovement;
    private MovementType type;

    public RepeatedMovementBuilder(Movement previousMovement) {
        this.previousMovement = previousMovement;
    }

    public static RepeatedMovementBuilder after(Movement previousMovement) {
        return new RepeatedMovementBuilder(previousMovement);
    }

    public RepeatedMovementBuilder becauseOf(MovementType type) {
        this.type = type;
        return this;
    }

    public Movement goToPosition(int finalPosition) {
        return new RepeatedMovement(previousMovement, type, finalPosition);
    }
}
