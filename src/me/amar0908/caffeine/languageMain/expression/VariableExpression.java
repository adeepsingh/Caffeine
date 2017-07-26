package me.amar0908.caffeine.languageMain.expression;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.Utils;
import me.amar0908.caffeine.languageMain.Value;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.tokenizer.PreProcessedTokenizer;
import me.amar0908.caffeine.languageMain.tokenizer.Token;

import java.io.IOException;

public class VariableExpression extends Expression {

    private Token token;

    public VariableExpression(Token token, Block block) {
        super(new PreProcessedTokenizer(token), block);

        this.token = token;
    }

    @Override
    public Value evaluate() throws IOException, InvalidCodeException {
        return Utils.parseToken(token, block);
    }
}