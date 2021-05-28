package app.domain.rules;

import app.domain.player.Board;
import app.domain.player.Players;
import app.domain.rules.first.FirstMovement;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;

class RuleProcessorTest {
    Board board = new Board();
    Players players = new Players();
    RuleProcessor ruleProcessor = new RuleProcessor(board, players);
    private ArgumentCaptor<FirstMovement> firstMovement;

    @BeforeEach
    void setUp() {
        firstMovement = ArgumentCaptor.forClass(FirstMovement.class);

    }

//    @Test
//    void repeat_movement_on_bouncing() {
//        Player pippo = new Player("Pippo", board.position(62));
//        players.add(pippo);
//
//        PlayerOnTurn player = mock(PlayerOnTurn.class);
//        ruleProcessor.computeMovementsFor(player);
//
//        ArgumentCaptor<BouncingMovement> movement = ArgumentCaptor.forClass(BouncingMovement.class);
//
//        verify(player).move();
//        verify(player).applyMovement(firstMovement.capture());
//        verify(player).applyMovement(movement.capture());
//    }
//
//    @Test
//    void repeat_movement_on_the_bridge() {
//        Player pippo = new Player("Pippo", board.position(4));
//        players.add(pippo);
//        PlayerOnTurn turn = new PlayerOnTurn(pippo, dice(1, 1), new LinkedList<>());
//
//        ruleProcessor.computeMovementsFor(turn);
//
//        assertThat(turn.movements().size()).isEqualTo(2);
//        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
//        assertThat(turn.movements().get(1) instanceof JumpOnBridgeMovement).isTrue();
//    }
//
//    @Test
//    void repeat_movement_on_the_goose() {
//        Player pippo = new Player("Pippo", board.position(3));
//        players.add(pippo);
//
//        PlayerOnTurn turn = new PlayerOnTurn(pippo, dice(1, 1), new LinkedList<>());
//        ruleProcessor.computeMovementsFor(turn);
//
//        assertThat(turn.movements().size()).isEqualTo(2);
//        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
//        assertThat(turn.movements().get(1) instanceof GooseMovement).isTrue();
//    }
//
//    @Test
//    void switch_players_on_encounter() {
//        Player pippo = new Player("Pippo", board.position(15));
//        players.add(pippo);
//        players.add(new Player("Pluto", board.position(17)));
//
//        PlayerOnTurn turn = new PlayerOnTurn(pippo, dice(1, 1), new LinkedList<>());
//        ruleProcessor.computeMovementsFor(turn);
//
//        assertThat(turn.movements().size()).isEqualTo(2);
//        assertThat(turn.movements().get(0) instanceof FirstMovement).isTrue();
//        assertThat(turn.movements().get(1) instanceof SwitchMovement).isTrue();
//    }
}