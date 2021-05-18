package goosegame.usecase.move_player;

import goosegame.domain.Position;

public class GooseMovement extends PresentableMovement {

    public GooseMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentGooseMovement(this);
    }
}