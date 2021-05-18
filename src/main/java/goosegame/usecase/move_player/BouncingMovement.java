package goosegame.usecase.move_player;

import goosegame.domain.Position;

public class BouncingMovement extends PresentableMovement {

    public BouncingMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentBouncing(this);
    }
}