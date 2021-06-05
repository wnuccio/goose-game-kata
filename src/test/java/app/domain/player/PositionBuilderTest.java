package app.domain.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static app.domain.player.PositionRule.NO_RULE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class PositionBuilderTest {

    private Board board;
    private PositionBuilder positionBuilder;

    @BeforeEach
    void setUp() {
        board = new Board();
        positionBuilder = new PositionBuilder(board);
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
    void build_a_position_with_default_attributes() {
        Position position = positionBuilder.build();

        assertThat(position.isEqualTo(new Position(board, 0,"0").attachRule(NO_RULE))).isTrue();
    }
}