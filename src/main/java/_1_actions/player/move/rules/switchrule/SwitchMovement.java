package _1_actions.player.move.rules.switchrule;

import _2_domain.player.Position;
import _2_domain.presenter.PlayerTurnView;
import _2_domain.presenter.PresentableMovement;
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
        new SwitchPlayersPresenter().present(this, presenter, playerTurnView);
    }
}
