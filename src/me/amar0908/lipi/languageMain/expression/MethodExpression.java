package me.amar0908.lipi.languageMain.expression;

import java.io.IOException;
import java.util.List;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.Value;
import me.amar0908.lipi.languageMain.block.Method;

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