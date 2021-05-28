package app.domain.movement;

import app.domain.presenter.StringBuilderPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

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
    void return_the_last_added_movement() {
        movements.add(movement1);
        movements.add(movement2);

        Movement movement = movements.last();

        assertThat(movement).isSameAs(movement2);
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