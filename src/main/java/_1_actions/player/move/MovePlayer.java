package _1_actions.player.move;

import _1_actions.player.move.presenter.MovementPresenter;
import _1_actions.player.move.presenter.MovementView;
import _1_actions.player.move.rules.RuleProcessor;
import _2_domain.MoveCommand;
import _2_domain.Movement;
import _2_domain.Players;

import java.util.List;

public class MovePlayer {
    private final Players players;
    private final RuleProcessor ruleProcessor;
    private final MovementPresenter presenter;

    public MovePlayer(Players players, RuleProcessor ruleProcessor, MovementPresenter presenter) {
        this.players = players;
        this.ruleProcessor = ruleProcessor;
        this.presenter = presenter;
    }

    public void acceptCommand(MoveCommand command) {
        if ( ! players.contains(command.player())) {
            return;
        }

        List<Movement> movements = ruleProcessor.fromCommand(command);
        presenter.presentMovement(buildMovementViewFrom(command, movements));
    }

    private MovementView buildMovementViewFrom(MoveCommand command, List<Movement> movements) {
        return new MovementView(
                movements,
                command.player(),
                command.dice().first(),
                command.dice().second()
        );
    }
}
