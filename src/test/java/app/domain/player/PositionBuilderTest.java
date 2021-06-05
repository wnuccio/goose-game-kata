package app.domain.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static app.domain.player.PositionRule.NO_RULE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PositionBuilderTest {

    private Board board;
    private PositionBuilder positionBuilder;

    @BeforeEach
    void setUp() {
        board = new Board();
        positionBuilder = new PositionBuilder(null, board);
    }

    @Test
    void build_a_position_with_specified_attributes() {
        int aValue = 7;
        String aName = "aName";
        PositionRule aRule = mock(PositionRule.class);

        Position position = positionBuilder
                .withValue(aValue)
                .withName(aName)
                .withRule(aRule)
                .build();

        assertThat(position.isEqualTo(new Position(board, aValue, aName).attachRule(aRule))).isTrue();
    }

    @Test
    void adds_the_position_to_the_board_builder_after_a_rule_is_specified() {
        BoardBuilder boardBuilder = mock(BoardBuilder.class);
        PositionRule anyRule = mock(PositionRule.class);
        positionBuilder = new PositionBuilder(boardBuilder, board);

        positionBuilder.andRule(anyRule);

        verify(boardBuilder).withPosition(positionBuilder);
    }

    @Test
    void build_a_position_with_default_attributes() {
        Position position = positionBuilder.build();

        assertThat(position.isEqualTo(new Position(board, 0,"0").attachRule(NO_RULE))).isTrue();
    }

    @Test
    void build_a_position_with_a_stringified_value_as_name() {
        Position position = positionBuilder
                .withValue(5)
                .build();

        assertThat(position.name()).isEqualTo("5");
    }

    @Test
    void build_a_position_by_overriding_the_default_name() {
        Position position = positionBuilder
                .withValue(5)
                .withName("5, The Goose")
                .build();

        assertThat(position.name()).isEqualTo("5, The Goose");
    }
}