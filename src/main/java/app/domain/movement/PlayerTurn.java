package app.domain.movement;

import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import java.util.LinkedList;

public class PlayerTurn {
    private final MoveCommand command;
    private final LinkedList<Movement> movements;

    public PlayerTurn(MoveCommand command) {
        this.command = command;
        this.movements = new LinkedList<>();

    }

    public String player() {
        return command.player();
    }

    public int diceTotal() {
        return command.diceTotal();
    }

    public void add(Movement movement) {
        movements.add(movement);
    }

    public LinkedList<Movement> movements() {
        return movements;
    }

    public Position previousPosition() {
        return movements.getLast().startPosition();
    }

    public void present(StringBuilderPresenter presenter) {
        presenter.init();
        for (Movement movement: movements()) {
            PlayerTurnView playerTurnView = new PlayerTurnView(
                    player(),
                    command.dice().first(),
                    command.dice().second());

            movement.present(presenter, playerTurnView);
        }
        presenter.writeOutput();
    }
}
