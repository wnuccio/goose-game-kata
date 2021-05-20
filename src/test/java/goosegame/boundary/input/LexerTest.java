package goosegame.boundary.input;

import org.junit.jupiter.api.Test;

import java.util.List;

import static goosegame.boundary.input.Token.token;
import static goosegame.boundary.input.TokenType.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {

    private final Lexer lexer = new Lexer();

    @Test
    void recognize_add_player_command() {
        List<Token> tokens;

        tokens = lexer.tokenize("add player Pippo");
        assertEquals(asList(token(ADD_PLAYER), token(PLAYER_NAME, "Pippo")), tokens);

        tokens = lexer.tokenize("add player Pluto");
        assertEquals(asList(token(ADD_PLAYER), token(PLAYER_NAME, "Pluto")), tokens);
    }

    @Test
    void recognize_move_player_with_dice_command() {
        List<Token> tokens = lexer.tokenize("move Pippo 1 2");

        assertEquals(asList(
                token(MOVE_PLAYER),
                token(PLAYER_NAME, "Pippo"),
                token(DICE, "1"),
                token(DICE, "2")),
                tokens);
    }

    @Test
    void recognize_move_player_command() {
        List<Token> tokens = lexer.tokenize("move Pluto");

        assertEquals(asList(
                token(MOVE_PLAYER),
                token(PLAYER_NAME, "Pluto")),
                tokens);

    }

    @Test
    void recognize_reset_command() {
        List<Token> tokens = lexer.tokenize("reset game");

        assertEquals(asList(token(RESET_GAME)), tokens);
    }

    @Test
    void recognize_stop_command() {
        List<Token> tokens = lexer.tokenize("stop game");

        assertEquals(asList(token(STOP_GAME)), tokens);
    }
}
