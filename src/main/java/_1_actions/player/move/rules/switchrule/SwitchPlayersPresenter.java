package _1_actions.player.move.rules.switchrule;

import _2_domain.presenter.PlayerTurnView;
import _2_domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class SwitchPlayersPresenter {
    public void present(SwitchMovement movement, StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        String playerSwitch = format(". On %s there is %s, who returns to %s",
                presenter.positionName(movement.startPosition()),
                movement.switchedPlayer(),
                presenter.positionName(movement.finalPosition()));

        presenter.append(playerSwitch);
    }
}
