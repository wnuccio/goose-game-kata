package usecase.move_player;

import domain.Position;

public class MovementWithSwitch implements Movement {
    private final String switchedPlayer;
    private final Movement mainMovement;

    public MovementWithSwitch(String switchedPlayer, Movement mainMovement) {
        this.switchedPlayer = switchedPlayer;
        this.mainMovement = mainMovement;
    }

    @Override
    public Position startPosition() {
        return mainMovement.startPosition();
    }

    @Override
    public Position finalPosition() {
        return mainMovement.finalPosition();
    }

    @Override
    public boolean hasPreviousMovement() {
        return false;
    }

    @Override
    public Movement previousMovement() {
        return null;
    }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentPlayerSwitching(this);
    }

    public String switchedPlayer() {
        return switchedPlayer;
    }
}
