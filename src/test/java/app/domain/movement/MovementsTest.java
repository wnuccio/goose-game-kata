package app.domain.movement;

import app.domain.player.Movement;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class MovementsTest {

    private StringBuilderPresenter presenter;
    private Movements movements;
    private PlayerOnTurn playerOnTurn;
    private Movement movement1;
    private Movement movement2;

    @BeforeEach
    void setUp() {
        presenter = mock(StringBuilderPresenter.class);
        movements = new Movements(presenter);

        playerOnTurn = mock(PlayerOnTurn.class);

        movement1 = mock(Movement.class);
        movement2 = mock(Movement.class);
    }

    @Test
    void return_the_last_position_of_all_movements() {
        movements.add(movement1);
        movements.add(movement2);

        Position startPositionOfLastMovement = mock(Position.class);
        when(movement2.startPosition()).thenReturn(startPositionOfLastMovement);

        Position lastPosition = movements.penultimatePosition();

        assertThat(lastPosition).isEqualTo(startPositionOfLastMovement);
    }

    @Test
    void present_all_movements_in_order() {
        movements.add(movement1);
        movements.add(movement2);

        movements.present(playerOnTurn);

        InOrder inOrder = inOrder(presenter, movement1, movement2);
        inOrder.verify(presenter).init();
        inOrder.verify(movement1).present(playerOnTurn, presenter);
        inOrder.verify(movement2).present(playerOnTurn, presenter);
        inOrder.verify(presenter).writeOutput();
    }
}