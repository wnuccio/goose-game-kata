package goosegame.lexer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static goosegame.lexer.TokenType.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {

    private final Lexer lexer = new Lexer();

    @Test
    void recognize_add_player_command() {
        List<Token> tokens;

        tokens = lexer.tokenize("add player Pippo");
        assertEquals(asList(Token.token(TokenType.ADD_PLAYER), Token.token(TokenType.PLAYER_NAME, "Pippo")), tokens);

        tokens = lexer.tokenize("add player Pluto");
        assertEquals(asList(Token.token(TokenType.ADD_PLAYER), Token.token(TokenType.PLAYER_NAME, "Pluto")), tokens);
    }

    @Test
    void recognize_move_player_with_dice_command() {
        List<Token> tokens = lexer.tokenize("move Pippo 1 2");

        assertEquals(asList(
                Token.token(MOVE_PLAYER),
                Token.token(PLAYER_NAME, "Pippo"),
                Token.token(DICE, "1"),
                Token.token(DICE, "2")),
                tokens);
    }

    @Test
    void recognize_move_player_command() {
        List<Token> tokens = lexer.tokenize("move Pluto");

        assertEquals(asList(
                Token.token(MOVE_PLAYER),
                Token.token(PLAYER_NAME, "Pluto")),
                tokens);

    }

    @Test
    void recognize_reset_command() {
        List<Token> tokens = lexer.tokenize("reset game");

        assertEquals(Arrays.asList(Token.token(RESET_GAME)), tokens);
    }

    @Test
    void recognize_stop_command() {
        List<Token> tokens = lexer.tokenize("stop game");

        assertEquals(Arrays.asList(Token.token(STOP_GAME)), tokens);
    }
}
