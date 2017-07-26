package me.amar0908.caffeine.languageMain.tokenizer;

import java.util.regex.Pattern;

import me.amar0908.caffeine.languageMain.tokenizer.Token;
import me.amar0908.caffeine.languageMain.tokenizer.TokenData;
import me.amar0908.caffeine.languageMain.tokenizer.Tokenizer;

public class StringTokenizer extends Tokenizer {

    public StringTokenizer(String str) {
        super(str);

        registerTokenData(new TokenData(Pattern.compile("^(\\{[^{]*\\})"), Token.TokenType.IDENTIFIER));
        registerTokenData(new TokenData(Pattern.compile("^([^\\{]*)"), Token.TokenType.STRING_PART));
    }
}