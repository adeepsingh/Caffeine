package me.amar0908.lipi.languageMain.parser;

import static me.amar0908.lipi.languageMain.tokenizer.Regex.IDENTIFIER_OR_LITERAL;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.block.Block;
import me.amar0908.lipi.languageMain.block.For;
import me.amar0908.lipi.languageMain.expression.Expression;
import me.amar0908.lipi.languageMain.tokenizer.PreProcessedTokenizer;
import me.amar0908.lipi.languageMain.tokenizer.Token;
import me.amar0908.lipi.languageMain.tokenizer.Tokenizer;

public class ForParser extends Parser<For> {

    public ForParser() {
        super(For.class);
    }

    @Override
    public boolean shouldParseLine(String line) {
        return line.matches("for \\(" + IDENTIFIER_OR_LITERAL + " " + IDENTIFIER_OR_LITERAL + "\\)");
    }

    @Override
    public For parse(Block superBlock, Tokenizer tokenizer) throws InvalidCodeException {
        // (1 10)

        tokenizer.nextToken(); // Skip the for token.

        if (!tokenizer.nextToken().getToken().equals("(")) {
            throw new InvalidCodeException("For statement does not begin with opening parenthesis.");
        }

        Token lower = tokenizer.nextToken(), upper = tokenizer.nextToken();

        if (!tokenizer.nextToken().getToken().equals(")")) {
            throw new InvalidCodeException("For statement does not end with closing parenthesis.");
        }

        return new For(superBlock, Expression.parse(new PreProcessedTokenizer(lower), superBlock, null), Expression.parse(new PreProcessedTokenizer(upper), superBlock, null));
    }
}
