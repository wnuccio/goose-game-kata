package goosegame.boundary.input;

import org.junit.jupiter.api.Test;

import java.util.List;

import static goosegame.boundary.input.Token.token;
import static goosegame.boundary.input.TokenType.ADD_PLAYER;
import static goosegame.boundary.input.TokenType.PLAYER_NAME;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {

    @Test
    void recognize_add_player_command() {
        Lexer lexer = new Lexer();
        List<Token> tokens;

        tokens = lexer.tokenize("add player Pippo");
        assertEquals(asList(token(ADD_PLAYER), token(PLAYER_NAME, "Pippo")), tokens);

        tokens = lexer.tokenize("add player Pluto");
        assertEquals(asList(token(ADD_PLAYER), token(PLAYER_NAME, "Pluto")), tokens);
    }
}
