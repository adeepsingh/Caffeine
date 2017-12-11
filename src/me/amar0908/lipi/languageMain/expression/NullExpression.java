package me.amar0908.lipi.languageMain.expression;

import java.io.IOException;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.Value;

public class NullExpression extends Expression {

    public NullExpression() {
        super(null, null);
    }

    @Override
    public Value evaluate() throws IOException, InvalidCodeException {
        return new Value(null);
    }
}