package me.amar0908.lipi.languageMain.expression;

import java.io.IOException;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.Utils;
import me.amar0908.lipi.languageMain.Value;
import me.amar0908.lipi.languageMain.block.Block;
import me.amar0908.lipi.languageMain.tokenizer.PreProcessedTokenizer;
import me.amar0908.lipi.languageMain.tokenizer.Token;

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