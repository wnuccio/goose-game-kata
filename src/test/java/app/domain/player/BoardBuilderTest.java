package app.domain.player;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static app.domain.player.BoardForTests.boardEndingOn;
import static app.domain.player.BoardForTests.standardBoard;
import static app.domain.player.PositionRule.NO_RULE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BoardBuilderTest {

    private BoardBuilder boardBuilder = new BoardBuilder();

    @Test
    void creates_a_board_with_just_zero_position_as_default() {
        Board board = boardBuilder.build();

        assertThat(board.hasPosition(0)).isTrue();
        assertThat(board.start()).isEqualTo(board.position(0));
        assertThat(board.position(0)).isEqualTo(board.win());
    }

    @Test
    void add_automatically_all_positions_between_0_and_the_maximum_value_specified_position() {
        Board board = boardEndingOn(2);

        assertThat(board.hasPosition(0)).isTrue();
        assertThat(board.hasPosition(1)).isTrue();
        assertThat(board.hasPosition(2)).isTrue();
        assertThat(board.hasPosition(3)).isFalse();
    }

    @Test
    void creates_a_standard_board_with_positions_0_63() {
        Board board = standardBoard();

        for (int i=0; i<=63; i++) {
            assertThat(board.hasPosition(i)).isTrue();
        }
    }

    @Test
    void throws_an_exception_when_asked_for_a_not_configured_position() {
        Board board = boardEndingOn(2);

        Assertions.assertThrows(NoSuchElementException.class, () -> board.position(3));
    }

    @Test
    void adds_a_position_from_a_position_builder() {
        PositionBuilder positionBuilder = mock(PositionBuilder.class);
        when(positionBuilder.build()).thenReturn(new Position(null, 3, ""));

        Board board = boardBuilder
                .withPosition(positionBuilder)
                .build();

        assertThat(board.hasPosition(3)).isTrue();
    }

    @Test
    void when_adds_a_position_gives_it_a_default_name_after_its_value() {
        Board board = boardEndingOn(1);

        assertThat(board.position(1).name()).isEqualTo("1");
        assertThat(board.position(0).rule).isEqualTo(NO_RULE);
    }
}