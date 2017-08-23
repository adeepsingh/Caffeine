package me.amar0908.caffeine.languageMain.parser;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.block.Return;
import me.amar0908.caffeine.languageMain.expression.Expression;
import me.amar0908.caffeine.languageMain.tokenizer.Tokenizer;

public class ReturnParser extends Parser<Return> {

    public ReturnParser() {
        super(Return.class);
    }

    @Override
    public boolean shouldParseLine(String line) {
        return line.matches("return .*");
    }

    @Override
    public Return parse(Block superBlock, Tokenizer tokenizer) throws InvalidCodeException {
        // "Noah"

        tokenizer.nextToken(); // Skip the return token.
        return new Return(superBlock, Expression.parse(tokenizer, superBlock, null));
    }
}