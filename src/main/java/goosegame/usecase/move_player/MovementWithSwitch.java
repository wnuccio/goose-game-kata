package goosegame.usecase.move_player;

import goosegame.domain.Position;

public class MovementWithSwitch implements Movement {
    private final String switchedPlayer;
    private final Position startPosition;
    private final Position finalPosition;

    public MovementWithSwitch(String switchedPlayer, Position startPosition, Position finalPosition) {
        this.switchedPlayer = switchedPlayer;
        this.startPosition = startPosition;
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
        movementPresenter.presentPlayerSwitching(this);
    }

    public String switchedPlayer() {
        return switchedPlayer;
    }
}
