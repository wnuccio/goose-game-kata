package app.domain.rules;

import app.domain.movement.PlayerOnTurn;
import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.goose.GooseRule;
import app.domain.rules.switchrule.SwitchPlayersRule;

public class RuleProcessor {
    private final JumpOnBridgeRule jumpOnBridgeRule;
    private final GooseRule gooseRule;
    private final SwitchPlayersRule switchPlayersRule;

    public RuleProcessor(JumpOnBridgeRule jumpOnBridgeRule,
                         GooseRule gooseRule,
                         SwitchPlayersRule switchPlayersRule) {

        this.jumpOnBridgeRule = jumpOnBridgeRule;
        this.gooseRule = gooseRule;
        this.switchPlayersRule = switchPlayersRule;
    }

    public void computeMovementsFor(PlayerOnTurn playerOnTurn) {
        playerOnTurn.start();
        playerOnTurn.moveByDice();

        jumpOnBridgeRule.apply(playerOnTurn);
        gooseRule.apply(playerOnTurn);
        switchPlayersRule.apply(playerOnTurn);
    }
}
