package me.amar0908.lipi.languageMain.tokenizer;

import java.util.regex.Pattern;

import me.amar0908.lipi.languageMain.tokenizer.LIPITokenizer;
import me.amar0908.lipi.languageMain.tokenizer.Regex;
import me.amar0908.lipi.languageMain.tokenizer.Token;
import me.amar0908.lipi.languageMain.tokenizer.TokenData;
import me.amar0908.lipi.languageMain.tokenizer.Tokenizer;

public class LIPITokenizer extends Tokenizer {

    public LIPITokenizer(String str) {
        super(str);

        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.COMPARISON + ")"), Token.TokenType.TOKEN));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.CONDITIONAL_OPERATOR + ")"), Token.TokenType.TOKEN));

        for (String t : new String[] { "=", "\\(", "\\)", "\\.", "\\,", "->" }) {
            registerTokenData(new TokenData(Pattern.compile("^(" + t + ")"), Token.TokenType.TOKEN));
        }

        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.BOOLEAN_LITERAL + ")"), Token.TokenType.BOOLEAN_LITERAL));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.IDENTIFIER + ")"), Token.TokenType.IDENTIFIER));
        registerTokenData(new TokenData(Pattern.compile("^(//.*)"), Token.TokenType.EMPTY));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.PROPERTY + ")"), Token.TokenType.PROPERTY));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.STRING_LITERAL + ")"), Token.TokenType.STRING_LITERAL));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.INTEGER_LITERAL + ")"), Token.TokenType.INTEGER_LITERAL));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.DOUBLE_LITERAL + ")"), Token.TokenType.DOUBLE_LITERAL));
    }

    @Override
    public LIPITokenizer clone() {
        return new LIPITokenizer(str);
    }
}