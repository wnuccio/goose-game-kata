package usecase.move_player;

public interface MovementPresenter {
    void presentMovement(MovementView movementView);

    void presentSimpleMovement(SimpleMovement movement);

    void presentJumpOnBridge(FurtherMovement movement);

    void presentBouncing(FurtherMovement movement);

    void presentPlayerSwitching(MovementWithSwitch movement);

    void presentRepeatOnGoose(FurtherMovement movement);
}
