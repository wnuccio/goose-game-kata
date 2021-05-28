package app.domain.player;

import app.domain.movement.Dice;
import app.domain.movement.Movement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

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
        pippo = new Player("Pippo", board.position(3));
        pippoObserver = mock(PlayerObserver.class);
        pippo.addObserver(pippoObserver);

        movement = ArgumentCaptor.forClass(Movement.class);
    }

    @Test
    void move_to_given_position_and_notify_movement() {
        pippo.moveByDice(new Dice(2, 3));

        assertThat(pippo.position()).isEqualTo(board.position(8));

        verify(pippoObserver).notifyMovement(movement.capture());

        assertThat(movement.getValue().startPosition()).isEqualTo(board.position(3));
        assertThat(movement.getValue().finalPosition()).isEqualTo(board.position(8));
    }


}