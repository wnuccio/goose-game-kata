package goosegame.usecase.move_player;

import goosegame.domain.Position;

public class FirstMovement extends PresentableMovement {

    public FirstMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) { movementPresenter.presentFirstMovement(this); }
}