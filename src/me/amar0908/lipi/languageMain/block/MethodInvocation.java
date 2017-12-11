package me.amar0908.lipi.languageMain.block;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import me.amar0908.lipi.languageMain.*;
import me.amar0908.lipi.languageMain.Runtime;
import me.amar0908.lipi.languageMain.expression.Expression;
import me.amar0908.lipi.languageMain.system.MethodMeta;
import me.amar0908.lipi.languageMain.system.SystemClass;
import me.amar0908.lipi.languageMain.tokenizer.Token;

public class MethodInvocation extends ReadOnlyBlock implements Nameable {

    private Token invokableToken, methodToken, captureToken;
    private ArrayList<Expression> expressions;

    public MethodInvocation(Block superBlock, Token invokableToken, Token methodToken, ArrayList<Expression> expressions, Token captureToken) {
        super(superBlock);

        this.invokableToken = invokableToken;
        this.methodToken = methodToken;
        this.expressions = expressions;
        this.captureToken = captureToken;
    }

    @Override
    public void run() throws InvalidCodeException, IOException {
        List<Value> values = getValues();

        List<Type> types = values.stream()
                .map(Value::getType)
                .collect(Collectors.toList());

        Class clazz;

        if (invokableToken.getToken().equals("System")) {
            clazz = SystemClass.getInstance();
        }

        else if (invokableToken.getToken().equals("this")) {
            clazz = (Class) getBlockTree()[0];
        }

        else {
            clazz = Runtime.RUNTIME.getPogoClass(invokableToken.getToken());
        }

        if (clazz == null) {
            Optional<Variable> v = getSuperBlock().getVariable(invokableToken.getToken());

            if (!v.isPresent()) {
                throw new InvalidCodeException("Expected class or variable, found " + invokableToken + ".");
            }

            Variable var = v.get();

            if (var.getType() instanceof PrimitiveType) {
                throw new InvalidCodeException("Attempted to invoke method on primitive variable.");
            }

            if (var.getType() instanceof MethodMeta) {
                clazz = (MethodMeta) var.getValue();
            }

            else {
                clazz = (Class) var.getType();
            }
        }

        if (clazz == null) {
            throw new InvalidCodeException("Expected class or variable, got " + invokableToken.getToken() + ".");
        }

        Optional<Method> m = clazz.getMethod(methodToken.getToken(), types.toArray(new Type[types.size()]));

        if (!m.isPresent()) {
            throw new InvalidCodeException("Expected method, found " + methodToken + ".");
        }

        Value ret = m.get().invoke(values);

        if (captureToken != null && captureToken.getType() != Token.TokenType.EMPTY) {
            Optional<Variable> var = getSuperBlock().getVariable(captureToken.getToken());

            if (!var.isPresent()) {
                throw new InvalidCodeException("Expected capture variable, found " + captureToken + ".");
            }

            if (!var.get().getType().equalsType(ret.getType())) {
                throw new InvalidCodeException("Incompatible type for capture variable.");
            }

            var.get().setValue(ret.getValue());
        }
    }

    public List<Value> getValues() {
        return this.expressions.stream()
                .map(expression -> {
                    try {
                        return expression.evaluate();
                    }

                    catch (Exception e) {
                        System.err.println("getValues() Error!");
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(value -> value != null)
                .collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return methodToken.getToken();
    }

    @Override
    public String toString() {
        return getClass() + " invokableToken=" + invokableToken + " methodToken=" + methodToken + " captureToken=" + captureToken;
    }
}