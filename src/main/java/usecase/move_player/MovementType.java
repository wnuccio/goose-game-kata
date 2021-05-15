package usecase.move_player;

interface PresentationFunction {
    void apply(FurtherMovement furtherMovement, MovementPresenter movementPresenter);
}

public enum MovementType {
    BOUNCING((furtherMovement, movementPresenter) -> movementPresenter.presentBouncing(furtherMovement)),
    JUMP_ON_BRIDGE((furtherMovement, movementPresenter) -> movementPresenter.presentJumpOnBridge(furtherMovement)),
    REPEAT_ON_GOOSE((furtherMovement, movementPresenter) -> movementPresenter.presentRepeatOnGoose(furtherMovement));

    private PresentationFunction presentationFunction;

    MovementType(PresentationFunction presentationFunction) {
        this.presentationFunction = presentationFunction;
    }

    public void presentMovement(FurtherMovement furtherMovement, MovementPresenter movementPresenter) {
        presentationFunction.apply(furtherMovement, movementPresenter);
    }
}
