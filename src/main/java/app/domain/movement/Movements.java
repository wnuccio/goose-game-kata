package app.domain.movement;

import app.domain.player.Movement;
import app.domain.player.PlayerObserver;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import java.util.LinkedList;

public class Movements implements PlayerObserver {

    private final LinkedList<Movement> movements = new LinkedList<>();
    private final StringBuilderPresenter presenter;

    public Movements(StringBuilderPresenter presenter) {
        this.presenter = presenter;
    }

    public void add(Movement movement) {
        movements.add(movement);
    }

    public void present(PlayerOnTurn playerOnTurn) {
        presenter.init();
        movements.forEach(movement -> movement.present(playerOnTurn, presenter));
        presenter.writeOutput();
    }

    public Position penultimatePosition() {
        return movements.getLast().startPosition();
    }

    @Override
    public void playerPositionChanged(Movement movement) {
        movements.add(movement);
    }
}
