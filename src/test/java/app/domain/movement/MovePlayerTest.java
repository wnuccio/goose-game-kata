package app.domain.movement;

import app.domain.player.Board;
import app.domain.player.Players;
import app.domain.presenter.StringBuilderPresenter;
import app.domain.rules.RuleProcessor;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MovePlayerTest {
    private final Players players = new Players();
    private final StringBuilderPresenter presenter = mock(StringBuilderPresenter.class);
    RuleProcessor ruleProcessor = mock(RuleProcessor.class);
    private final Board board = new Board();
    private final MovePlayer movePlayer = new MovePlayer(players, ruleProcessor, presenter);

    @Test
    void computes_a_movement_list_and_present_them_all() {
        players.addNewPlayerOnPosition("Pippo", new Board().start());
        MoveCommand moveCommand = move("Pippo", 4, 3);

        movePlayer.acceptCommand(moveCommand);

        verify(ruleProcessor).computeMovementsFor(any());
        verify(presenter).init();
        verify(presenter).writeOutput();
    }

    private MoveCommand move(String player, int first, int second) {
        return new MoveCommand(player, Dice.dice(first, second));
    }
}