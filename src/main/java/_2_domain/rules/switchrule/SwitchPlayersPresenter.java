package _2_domain.rules.switchrule;

import _2_domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class SwitchPlayersPresenter {
    public void present(SwitchMovement movement, StringBuilderPresenter presenter) {
        String playerSwitch = format(". On %s there is %s, who returns to %s",
                movement.startPosition().name(),
                movement.switchedPlayer(),
                movement.finalPosition().name());

        presenter.append(playerSwitch);
    }
}
