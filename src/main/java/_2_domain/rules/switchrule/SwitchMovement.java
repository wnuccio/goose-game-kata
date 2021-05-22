package _2_domain.rules.switchrule;

import _2_domain.movement.PlayerTurnView;
import _2_domain.movement.PresentableMovement;
import _2_domain.player.Position;
import _2_domain.presenter.StringBuilderPresenter;

public class SwitchMovement extends PresentableMovement {
    private final String switchedPlayer;

    public SwitchMovement(String switchedPlayer, Position startPosition, Position finalPosition) {
        super(startPosition, finalPosition);
        this.switchedPlayer = switchedPlayer;
    }

    public String switchedPlayer() {
        return switchedPlayer;
    }

    @Override
    public void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        new SwitchPlayersPresenter().present(this, presenter);
    }
}
