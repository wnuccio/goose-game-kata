package app.domain.rules.goose;

import app.domain.movement.PlayerTurnView;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class GoosePresenter {
    public void present(Position finalPosition, StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        String playerMovesAgain = format(" %s moves again and goes to %s",
                playerTurnView.player(),
                finalPosition.name());

        presenter.append(playerMovesAgain);

    }
}
