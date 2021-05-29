package app.domain.rules;

import app.domain.movement.PlayerOnTurn;
import app.domain.rules.switchrule.SwitchPlayersRule;

public class RuleProcessor {
    private final SwitchPlayersRule switchPlayersRule;

    public RuleProcessor(SwitchPlayersRule switchPlayersRule) {

        this.switchPlayersRule = switchPlayersRule;
    }

    public void computeMovementsFor(PlayerOnTurn playerOnTurn) {
        playerOnTurn.start();
        playerOnTurn.moveByDice();

        switchPlayersRule.apply(playerOnTurn);
    }
}
