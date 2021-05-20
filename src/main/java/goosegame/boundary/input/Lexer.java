package goosegame.boundary.input;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static goosegame.boundary.input.Token.token;
import static goosegame.boundary.input.TokenType.ADD_PLAYER;
import static goosegame.boundary.input.TokenType.PLAYER_NAME;
import static java.lang.String.format;
import static java.util.Arrays.asList;

public class Lexer {

    public List<Token> tokenize(String text) {
        String regExp = format("(%s) (%s)", ADD_PLAYER.regExp(), PLAYER_NAME.regExp());
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(text);

        if ( ! matcher.find()) return Collections.emptyList();

        return asList(token(ADD_PLAYER), token(PLAYER_NAME, matcher.group(2)));
    }
}
