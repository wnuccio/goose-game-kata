package goosegame.usecase.move_player;

public interface MovementPresenter {
    void presentMovement(MovementView movementView);

    void presentFirstMovement(FirstMovement movement);

    void presentJumpOnBridge(JumpOnBridgeMovement movement);

    void presentBouncing(BouncingMovement movement);

    void presentSwitchMovement(SwitchMovement movement);

    void presentGooseMovement(GooseMovement movement);
}
