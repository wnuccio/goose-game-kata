package app.domain.movement;

import app.domain.player.Movement;
import app.domain.player.PlayerObserver;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import java.util.LinkedList;

import static java.lang.String.format;

public class Movements implements PlayerObserver {

    private final LinkedList<Movement> movements = new LinkedList<>();
    private final StringBuilderPresenter presenter;

    public Movements(StringBuilderPresenter presenter) {
        this.presenter = presenter;
    }

    public void present(PlayerOnTurn playerOnTurn) {
        presenter.init();
        movements.forEach(movement -> movement.present(playerOnTurn, presenter));
        presentEventualVictory(playerOnTurn);
        presenter.writeOutput();
    }

    private void presentEventualVictory(PlayerOnTurn playerOnTurn) {
        if (playerOnTurn.hasWon()) {
            String playerWins = format(". %s Wins!!", playerOnTurn.name());
            presenter.append(playerWins);
        }
    }

    public Position penultimatePosition() {
        return movements.getLast().startPosition();
    }

    @Override
    public void playerPositionChanged(Movement movement) {
        movements.add(movement);
    }
}
