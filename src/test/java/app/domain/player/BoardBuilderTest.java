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
        boardWith = BoardBuilder.board()
                .sized(0, 1);
    }

    @Test
    void when_adds_a_position_gives_it_a_default_name_after_its_value() {
        Board board = boardWith
                .position(1)
                .build();

        assertThat(board.position(1).name()).isEqualTo("1");
        assertThat(board.position(0).rule).isEqualTo(NO_RULE);
    }

    @Test
    void build_a_board_with_position_having_special_name_and_rule() {
        PositionRule aRule = mock(PositionRule.class);
        Board board = boardWith
                .position(0, "start", aRule)
                .build();

        assertThat(board.position(0).name()).isEqualTo("start");
        assertThat(board.position(0).rule).isEqualTo(aRule);
    }
}