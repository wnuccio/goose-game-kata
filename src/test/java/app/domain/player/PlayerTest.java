package app.domain.player;

import app.domain.movement.Dice;
import app.domain.movement.Movement;
import app.domain.rules.bouncing.BouncingMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PlayerTest {

    private Board board;
    private Player pippo;
    private PlayerObserver pippoObserver;
    private ArgumentCaptor<Movement> movement;

    @BeforeEach
    void setUp() {
        board = new Board();
        pippoObserver = mock(PlayerObserver.class);

        movement = ArgumentCaptor.forClass(Movement.class);
    }

    @Test
    void move_to_given_position_and_notify_movement() {
        pippo = new Player("Pippo", board.position(3));
        pippo.addObserver(pippoObserver);

        pippo.moveByDice(new Dice(2, 3));

        assertThat(pippo.position()).isEqualTo(board.position(8));

        verify(pippoObserver).playerPositionChanged(movement.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(3));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(8));
    }

    @Test
    void bounce_to_a_previous_position_when_over_win_and_notify_bouncing() {
        pippo = new Player("Pippo", board.position(61)); // max 63
        pippo.addObserver(pippoObserver);

        pippo.moveByDiceConsideringBouncing(new Dice(2, 3)); // 66

        assertThat(pippo.position()).isEqualTo(board.position(60));

        ArgumentCaptor<BouncingMovement> bouncing = ArgumentCaptor.forClass(BouncingMovement.class);

        InOrder inOrder = Mockito.inOrder(pippoObserver, pippoObserver);
        inOrder.verify(pippoObserver).playerPositionChanged(movement.capture());
        inOrder.verify(pippoObserver).playerBounced(bouncing.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(61));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(66));
        assertThat(bouncing.getValue().startPosition()).isEqualTo(board.position(63));
        assertThat(bouncing.getValue().finalPosition()).isEqualTo(board.position(60));
    }


}
