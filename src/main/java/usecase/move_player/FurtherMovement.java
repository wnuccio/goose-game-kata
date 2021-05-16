package usecase.move_player;


import domain.Position;

public class FurtherMovement implements Movement {
    protected Movement previousMovement;
    private final MovementType type;
    private final Position finalPosition;

    public FurtherMovement(Movement previousMovement, MovementType type, Position finalPosition) {
        this.previousMovement = previousMovement;
        this.type = type;
        this.finalPosition = finalPosition;
    }

    @Override
    public Position startPosition() {
        return previousMovement.startPosition();
    }

    @Override
    public Position finalPosition() {
        return finalPosition;
    }

    @Override
    public void present(MovementPresenter movementPresenter) {
        type.presentMovement(this, movementPresenter);
    }
}
