package goosegame.boundary.input;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static goosegame.boundary.input.Token.token;
import static goosegame.boundary.input.TokenType.*;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class Lexer {

    private static final List<List<TokenType>> allExpressions;

    static {
        allExpressions = asList(
                asList(ADD_PLAYER, PLAYER_NAME)
                ,asList(MOVE_PLAYER, PLAYER_NAME, DICE, DICE)
                ,asList(MOVE_PLAYER, PLAYER_NAME)
                ,asList(RESET_GAME)
                ,asList(STOP_GAME)
        );
    }

    public List<Token> tokenize(String text) {

        for(List<TokenType> regex: allExpressions) {
            List<Token> tokens = recognize(text, (TokenType[]) regex.toArray());
            if ( !tokens.isEmpty()) return tokens;
        }

        return emptyList();
    }

    private List<Token> recognize(String text, TokenType... terms) {
        String regExp = buildExpressionWithGroups(terms);
        Matcher matcher = Pattern.compile(regExp).matcher(text);
        return extractTokens(matcher, terms);
    }

    private List<Token> extractTokens(Matcher matcher, TokenType[] terms) {
        if ( ! matcher.find()) return emptyList();
        List<Token> result = new ArrayList<>();
        for (int i = 0; i< terms.length; i++) {
            result.add(token(terms[i], matcher.group(i+1)));
        }
        return result;
    }

    private String buildExpressionWithGroups(TokenType... terms) {
        StringBuilder b = new StringBuilder();
        for (TokenType t: terms) {
            b.append(format("(%s) ", t.regex()));
        }
        String result = b.toString();
        if (! result.isEmpty()) result = result.substring(0, result.length()-1);
        return result;
    }
}
