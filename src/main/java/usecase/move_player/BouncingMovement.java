package usecase.move_player;

import static domain.Position.WIN_POSITION;

public class BouncingMovement extends RepeatedMovement implements Movement {

    public BouncingMovement(SimpleMovement previousMovement) {
        super(previousMovement);
    }

    @Override
    public int finalPosition() {
        return WIN_POSITION - (previousMovement.finalPosition() - WIN_POSITION);
    }

    @Override
    public boolean isBouncing() {
        return true;
    }

    @Override
    public int intermediatePosition() {
        return WIN_POSITION;
    }
}