package app.domain.player;

import app.domain.movement.Movements;
import app.domain.rules.switchrule.SwitchPlayersRule;

import java.util.List;

public class PlayerOnTurn {
    private final Player player;
    private final Dice dice;

    public PlayerOnTurn(Player player, Dice dice) {
        this.player = player;
        this.dice = dice;
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

    public void performTurn(Movements movements, SwitchPlayersRule switchPlayersRule) {
        player.addObserver(movements);
        player.moveByDiceConsideringBouncing(dice);
        player.applyRuleOnCurrentPosition(this);
        switchPlayersRule.apply(this, movements);
        movements.present(this);
    }

    public void applyMovement(Movement movement) {
        player.applyMovement(movement);
        player.applyRuleOnCurrentPosition(this);
    }

    public List<Player> opponentsOnSamePosition(Players allPlayers) {
        return allPlayers.opponentsOnSamePositionOf(player);
    }
}
