package me.amar0908.caffeine.languageMain.expression;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.Value;
import me.amar0908.caffeine.languageMain.block.Method;

import java.io.IOException;
import java.util.List;

public class MethodExpression extends Expression {

    private Method method;
    private List<Value> values;

    public MethodExpression(Method method, List<Value> values) {
        super(null, null);

        this.method = method;
        this.values = values;
    }

    @Override
    public Value evaluate() throws IOException, InvalidCodeException {
        return method.invoke(values);
    }
}