package app.domain.movement;

import app.domain.player.Player;
import app.domain.player.Players;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import java.util.LinkedList;
import java.util.List;

public class PlayerOnTurn {
    private final Player player;
    private final Dice dice;
    private final LinkedList<Movement> movements;

    public PlayerOnTurn(Player player, Dice dice, LinkedList<Movement> movements) {
        this.player = player;
        this.dice = dice;
        this.movements = movements;
    }

    public String playerName() {
        return player.name();
    }

    public int firstDice() {
        return dice.first();
    }

    public int secondDice() {
        return dice.second();
    }

    public int diceTotal() {
        return dice.total();
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

    public Position positionOfPlayer() {
        return player.position();
    }

    public void add(Movement movement) {
        movements.add(movement);
    }

    public void applyMovement(Movement movement) {
        player.position(movement.finalPosition());
        movements.add(movement);
    }

    public List<Player> encounteredOpponents(Players allPlayers) {
        return allPlayers.opponentsOnSamePositionOf(player);
    }

    public boolean isOnTheGoose() {
        return positionOfPlayer().hasTheGoose();
    }
}
