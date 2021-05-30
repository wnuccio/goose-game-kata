package app.domain.movement;

import app.domain.player.PlayerOnTurn;
import app.domain.rules.switchrule.SwitchPlayersRule;

public class MovePlayer {
    private MovementsFactory movementsFactory;
    private SwitchPlayersRule switchPlayersRule;

    public MovePlayer(MovementsFactory movementsFactory, SwitchPlayersRule switchPlayersRule) {
        this.movementsFactory = movementsFactory;
        this.switchPlayersRule = switchPlayersRule;
    }

    public void doMove(PlayerOnTurn playerOnTurn) {
        Movements movements = movementsFactory.createMovements();
        playerOnTurn.performTurn(movements, switchPlayersRule);
    }
}
