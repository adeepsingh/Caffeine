package me.amar0908.lipi.languageMain.tokenizer;

import java.util.regex.Pattern;

import me.amar0908.lipi.languageMain.tokenizer.Token;

public class TokenData {

    private Pattern pattern;
    private Token.TokenType tokenType;

    public TokenData(Pattern pattern, Token.TokenType tokenType) {
        this.pattern = pattern;
        this.tokenType = tokenType;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Token.TokenType getTokenType() {
        return tokenType;
    }

    @Override
    public String toString() {
        return getClass() + " pattern=" + pattern + " tokenType=" + tokenType;
    }
}
