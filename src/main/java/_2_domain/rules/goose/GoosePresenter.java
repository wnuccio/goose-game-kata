package _2_domain.rules.goose;

import _2_domain.movement.PlayerTurnView;
import _2_domain.player.Position;
import _2_domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class GoosePresenter {
    public void present(Position finalPosition, StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        String playerMovesAgain = format(" %s moves again and goes to %s",
                playerTurnView.player(),
                finalPosition.name());

        presenter.append(playerMovesAgain);

    }
}
