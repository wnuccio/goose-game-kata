package usecase.move_player;

public class MovementWithSwitch implements Movement {
    private String switchedPlayer;
    private Movement mainMovement;

    public MovementWithSwitch(String switchedPlayer, Movement mainMovement) {
        this.switchedPlayer = switchedPlayer;
        this.mainMovement = mainMovement;
    }

    @Override
    public int startPosition() {
        return mainMovement.startPosition();
    }

    @Override
    public int finalPosition() {
        return mainMovement.finalPosition();
    }

    @Override
    public int diceTotal() {
        return mainMovement.diceTotal();
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
