package goosegame.usecase.move_player;

import goosegame.domain.Position;

import static goosegame.domain.Position.WIN;

public class BouncingMovement extends PresentableMovement {

    public BouncingMovement(Position finalPosition) { super(WIN, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentBouncing(this);
    }
}