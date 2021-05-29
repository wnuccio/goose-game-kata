package app.domain.movement;

import app.domain.player.Player;
import app.domain.player.PlayerObserver;
import app.domain.player.Players;
import app.domain.player.Position;

import java.util.List;

public class PlayerOnTurn implements PlayerObserver {
    private final Player player;
    private final Dice dice;
    private final Movements movements;

    public PlayerOnTurn(Player player, Dice dice, Movements movements) {
        this.player = player;
        this.dice = dice;
        this.movements = movements;
    }

    public String name() {
        return player.name();
    }

    public Dice dice() { return this.dice; }

    public Position position() {
        return player.position();
    }

    public int firstDice() {
        return dice.first();
    }

    public int secondDice() {
        return dice.second();
    }

    public void start() {
        this.player.addObserver(this);
    }

    public void moveByDice() {
        player.moveByDiceConsideringBouncing(dice);
        player.applyRuleOnCurrentPosition(this);
    }

    public void applyMovement(Movement movement) {
        player.applyMovement(movement);
        player.applyRuleOnCurrentPosition(this);
    }

    public void add(Movement movement) {
        movements.add(movement);
    }

    public List<Player> encounteredOpponents(Players allPlayers) {
        return allPlayers.opponentsOnSamePositionOf(player);
    }

    public Position previousPosition() {
        return movements.last().startPosition();
    }

    @Override
    public void playerPositionChanged(Movement movement) {
        movements.add(movement);
    }

    public void present() {
        movements.present(this);
    }
}
