package me.amar0908.lipi.languageMain.parser;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.block.Block;
import me.amar0908.lipi.languageMain.block.Return;
import me.amar0908.lipi.languageMain.expression.Expression;
import me.amar0908.lipi.languageMain.tokenizer.Tokenizer;

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
        // "Amar"

        tokenizer.nextToken(); // Skip the return token.
        return new Return(superBlock, Expression.parse(tokenizer, superBlock, null));
    }
}