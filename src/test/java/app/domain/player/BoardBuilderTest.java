package app.domain.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static app.domain.player.PositionRule.NO_RULE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class BoardBuilderTest {

    private BoardBuilder boardWith;

    @BeforeEach
    void setUp() {
        boardWith = BoardBuilder.board().withWinPosition(1);
    }

    @Test
    void creates_a_board_with_just_zero_position_as_default() {
        Board board = BoardBuilder.board().build();

        assertThat(board.hasPosition(0)).isTrue();
        assertThat(board.start()).isEqualTo(board.position(0));
        assertThat(board.position(0)).isEqualTo(board.winPosition());
    }

    @Test
    void add_automatically_all_positions_between_0_and_the_maximum_value_specified_position() {
        Board board = BoardBuilder.board()
                .withPosition(2)
                .build();

        assertThat(board.hasPosition(0)).isTrue();
//        assertThat(board.hasPosition(1)).isTrue();
        assertThat(board.hasPosition(2)).isTrue();
//        assertThat(board.hasPosition(3)).isFalse();
    }

    @Test
    void when_adds_a_position_gives_it_a_default_name_after_its_value() {
        Board board = boardWith
                .withPosition(1)
                .build();

        assertThat(board.position(1).name()).isEqualTo("1");
        assertThat(board.position(0).rule).isEqualTo(NO_RULE);
    }

    @Test
    void build_a_board_with_position_having_special_name_and_rule() {
        PositionRule aRule = mock(PositionRule.class);
        Board board = boardWith
                .withPosition(0, "start", aRule)
                .build();

        assertThat(board.position(0).name()).isEqualTo("start");
        assertThat(board.position(0).rule).isEqualTo(aRule);
    }
}