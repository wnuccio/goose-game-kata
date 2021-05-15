package usecase.move_player;

import domain.Dice;
import domain.Position;

public class SimpleMovement implements Movement {
    int startPosition;
    Dice dice;

    public static MovementBuilder movement() {
        return new MovementBuilder();
    }

    @Override
    public int startPosition() {
        return startPosition;
    }

    @Override
    public int diceTotal() {
        return dice.total();
    }

    @Override
    public int finalPosition() {
        return startPosition + dice.total();
    }

    public boolean isVictory() {
        return finalPosition() == Position.WIN_POSITION;
    }

    @Override
    public boolean hasPreviousMovement() {
        return false;
    }

    @Override
    public Movement previousMovement() {
        throw new IllegalStateException();
    }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentSimpleMovement(this);
    }


}