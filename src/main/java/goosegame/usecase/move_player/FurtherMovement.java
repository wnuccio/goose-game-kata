package goosegame.usecase.move_player;


import goosegame.domain.Position;

public class FurtherMovement implements Movement {
    private final Position startPosition;
    private final MovementType type;
    private final Position finalPosition;

    public FurtherMovement(Position startPosition, MovementType type, Position finalPosition) {
        this.startPosition = startPosition;
        this.type = type;
        this.finalPosition = finalPosition;
    }

    @Override
    public Position startPosition() {
        return startPosition;
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
