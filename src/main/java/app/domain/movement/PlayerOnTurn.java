package app.domain.movement;

import app.domain.player.Player;
import app.domain.player.Players;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerOnTurn {
    private final Player player;
    private final MoveCommand command;
    private final LinkedList<Movement> movements;

    public PlayerOnTurn(Player player, MoveCommand command, LinkedList<Movement> movements) {
        this.player = player;
        this.command = command;
        this.movements = movements;
    }

    public String playerName() {
        return player.name();
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
        movements.forEach(movement -> movement.present(presenter, this));
        presenter.writeOutput();
    }

    private Player player() {
        return player;
    }

    public Position positionOfPlayer() {
        return player().position();
    }

    public void applyMovement(Movement movement) {
        player.position(movement.finalPosition());
        movements.add(movement);
    }

    public List<String> encounteredOpponents(Players allPlayers) {
        List<String> result = allPlayers
                .all()
                .stream()
                .filter(aPlayer -> aPlayer.position().equals(positionOfPlayer()))
                .map(Player::name)
                .collect(Collectors.toList());

        result.remove(playerName());
        return result;
    }

    public boolean isOnTheGoose() {
        return positionOfPlayer().hasTheGoose();
    }
}
