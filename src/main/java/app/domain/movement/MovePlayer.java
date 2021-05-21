package app.domain.movement;

import app.domain.player.Players;

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
