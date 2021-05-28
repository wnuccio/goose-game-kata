package app.domain.movement;

import app.domain.presenter.StringBuilderPresenter;

import java.util.LinkedList;

public class Movements {

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

    public Movement last() {
        return movements.getLast();
    }
}
