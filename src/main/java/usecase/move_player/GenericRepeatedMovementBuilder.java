package usecase.move_player;

public class GenericRepeatedMovementBuilder {
    private SimpleMovement previousMovement;
    private MovementType type;
    private int intermediatePosition;

    public GenericRepeatedMovementBuilder(SimpleMovement previousMovement) {
        this.previousMovement = previousMovement;
    }

    public static GenericRepeatedMovementBuilder after(SimpleMovement previousMovement) {
        return new GenericRepeatedMovementBuilder(previousMovement);
    }

    public GenericRepeatedMovementBuilder becauseOf(MovementType type) {
        this.type = type;
        return this;
    }

    public GenericRepeatedMovementBuilder insteadOf(int intermediatePosition) {
        this.intermediatePosition = intermediatePosition;
        return this;
    }

    public Movement goToPosition(int finalPosition) {
        return new RepeatedMovement(previousMovement, type) {
            @Override
            public int intermediatePosition() {
                return intermediatePosition;
            }

            @Override
            public int finalPosition() {
                return finalPosition;
            }
        };
    }
}
