package app.domain.player;

import app.domain.movement.Movements;
import app.domain.rules.switchrule.SwitchPlayersRule;

public class PlayerOnTurn {
    private final Player player;
    private final Dice dice;

    public PlayerOnTurn(Player player, Dice dice) {
        this.player = player;
        this.dice = dice;
    }

    public Dice dice() { return this.dice; }

    public Position position() {
        return player.position();
    }

    public void performTurn(Movements movements, SwitchPlayersRule switchPlayersRule) {
        player.addObserver(movements);
        player.moveByDiceConsideringBouncing(dice);
        player.applyRuleOnCurrentPosition(this);
        switchPlayersRule.apply(player, movements);
        movements.present(this);
    }

    public void applyMovement(Movement movement) {
        player.applyMovement(movement);
        player.applyRuleOnCurrentPosition(this);
    }

    public String name() { return player.name(); }

    public int firstDice() {
        return dice.first();
    }

    public int secondDice() {
        return dice.second();
    }

    public boolean hasWon() {
        return player.position().isWin();
    }
}
