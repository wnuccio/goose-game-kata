package app.domain.rules;

import app.domain.movement.PlayerOnTurn;
import app.domain.rules.goose.GooseRule;
import app.domain.rules.switchrule.SwitchPlayersRule;

public class RuleProcessor {
    private final GooseRule gooseRule;
    private final SwitchPlayersRule switchPlayersRule;

    public RuleProcessor(GooseRule gooseRule,
                         SwitchPlayersRule switchPlayersRule) {

        this.gooseRule = gooseRule;
        this.switchPlayersRule = switchPlayersRule;
    }

    public void computeMovementsFor(PlayerOnTurn playerOnTurn) {
        playerOnTurn.start();
        playerOnTurn.moveByDice();

        gooseRule.apply(playerOnTurn);
        switchPlayersRule.apply(playerOnTurn);
    }
}
