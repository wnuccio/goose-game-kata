package app.domain.rules.goose;

import app.domain.movement.PlayerTurn;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class GoosePresenter {
    public void present(Position finalPosition, StringBuilderPresenter presenter, PlayerTurn playerTurn) {
        String playerMovesAgain = format(" %s moves again and goes to %s",
                playerTurn.player(),
                finalPosition.name());

        presenter.append(playerMovesAgain);

    }
}
