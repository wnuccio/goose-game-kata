package app.domain.movement;

import app.domain.player.Players;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import java.util.LinkedList;

public class PlayerTurn {
    private final Players players;
    private final MoveCommand command;
    private final LinkedList<Movement> movements;

    public PlayerTurn(Players players, MoveCommand command) {
        this.players = players;
        this.command = command;
        this.movements = new LinkedList<>();
    }

    public String player() {
        return command.player();
    }

    public int firstDice() {
        return command.dice().first();
    }

    public int secondDice() {
        return command.dice().second();
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
        movements().forEach(movement -> movement.present(presenter, this));
        presenter.writeOutput();
    }

    public Position positionOfPlayer() {
        return players.positionOf(player());
    }

    public void setPositionOfPlayer(Position position) {
        players.setPositionOf(player(), position);
    }
}
