package me.amar0908.caffeine.languageMain.expression;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.Value;

import java.io.IOException;

public class NullExpression extends Expression {

    public NullExpression() {
        super(null, null);
    }

    @Override
    public Value evaluate() throws IOException, InvalidCodeException {
        return new Value(null);
    }
}