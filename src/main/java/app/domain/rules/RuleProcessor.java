package app.domain.rules;

import app.domain.movement.Movements;
import app.domain.movement.PlayerOnTurnFactory;
import app.domain.player.PlayerOnTurn;
import app.domain.rules.switchrule.SwitchPlayersRule;

public class RuleProcessor {
    private final SwitchPlayersRule switchPlayersRule;
    private PlayerOnTurnFactory playerOnTurnFactory;

    public RuleProcessor(SwitchPlayersRule switchPlayersRule, PlayerOnTurnFactory playerOnTurnFactory) {
        this.switchPlayersRule = switchPlayersRule;
        this.playerOnTurnFactory = playerOnTurnFactory;
    }

    public void computeMovementsFor(PlayerOnTurn playerOnTurn) {
        Movements movements = playerOnTurnFactory.createMovements();
        playerOnTurn.doTurn(movements, switchPlayersRule);
    }
}
