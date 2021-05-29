package app.domain.rules;

import app.domain.movement.Movements;
import app.domain.movement.MovementsFactory;
import app.domain.player.PlayerOnTurn;
import app.domain.rules.switchrule.SwitchPlayersRule;

public class RuleProcessor {
    private final SwitchPlayersRule switchPlayersRule;
    private MovementsFactory movementsFactory;

    public RuleProcessor(SwitchPlayersRule switchPlayersRule, MovementsFactory movementsFactory) {
        this.switchPlayersRule = switchPlayersRule;
        this.movementsFactory = movementsFactory;
    }

    public void computeMovementsFor(PlayerOnTurn playerOnTurn) {
        Movements movements = movementsFactory.createMovements();
        playerOnTurn.doTurn(movements, switchPlayersRule);
    }
}
