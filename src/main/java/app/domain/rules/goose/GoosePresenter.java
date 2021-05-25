package app.domain.rules.goose;

import app.domain.movement.PlayerOnTurn;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class GoosePresenter {
    public void present(Position finalPosition, StringBuilderPresenter presenter, PlayerOnTurn playerOnTurn) {
        String playerMovesAgain = format(" %s moves again and goes to %s",
                playerOnTurn.player(),
                finalPosition.name());

        presenter.append(playerMovesAgain);

    }
}
