package app.domain.rules.bridge;

import app.domain.movement.PresentableMovement;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement(Position start, Position end) {
        super(start, end);
    }

    @Override
    public void present(PlayerOnTurn playerOnTurn, StringBuilderPresenter presenter) {
        new JumpOnBridgePresenter().present(presenter, playerOnTurn);
    }
}