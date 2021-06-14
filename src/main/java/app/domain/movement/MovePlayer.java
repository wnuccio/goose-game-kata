package app.domain.movement;

import app.domain.player.PlayerOnTurn;
import app.domain.rules.switchrule.SwitchPlayersRule;

public class MovePlayer {
    private MovementsRecorderFactory movementsRecorderFactory;
    private SwitchPlayersRule switchPlayersRule;

    public MovePlayer(MovementsRecorderFactory movementsRecorderFactory, SwitchPlayersRule switchPlayersRule) {
        this.movementsRecorderFactory = movementsRecorderFactory;
        this.switchPlayersRule = switchPlayersRule;
    }

    public void doMove(PlayerOnTurn playerOnTurn) {
        MovementsRecorder movementsRecorder = movementsRecorderFactory.createMovementsRecorder();
        playerOnTurn.performTurn(movementsRecorder, switchPlayersRule);
    }
}
