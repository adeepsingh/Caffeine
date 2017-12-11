package me.amar0908.lipi.languageMain.expression;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import me.amar0908.lipi.languageMain.*;
import me.amar0908.lipi.languageMain.block.Block;
import me.amar0908.lipi.languageMain.block.Class;
import me.amar0908.lipi.languageMain.block.Constructor;
import me.amar0908.lipi.languageMain.tokenizer.Token;
import me.amar0908.lipi.languageMain.tokenizer.Tokenizer;

public class InstantiationExpression extends Expression {

    private Variable variable;

    public InstantiationExpression(Variable variable, Tokenizer tokenizer, Block block) {
        super(tokenizer, block);

        this.variable = variable;
    }

    @Override
    public Value evaluate() throws IOException, InvalidCodeException {
        if (!tokenizer.hasNextToken()) {
            return new Value(null);
        }

        Token firstToken = tokenizer.nextToken();

        if (firstToken.getToken().equals("new")) {
            if (!tokenizer.nextToken().getToken().equals("(")) {
                throw new InvalidCodeException("Variable declaration does not contain opening parenthesis.");
            }

            if (variable.getType() instanceof PrimitiveType) {
                throw new InvalidCodeException("Attempted to instantiate primitive type with new.");
            }

            else {
                me.amar0908.lipi.languageMain.block.Class clazz = ((Class) variable.getType()).clone();
                Optional<Constructor> c = clazz.getConstructor(tokenizer.clone(), block);

                if (c.isPresent()) {
                    ArrayList<Value> values = new ArrayList<>();

                    while (tokenizer.hasNextToken()) {
                        Token token = tokenizer.nextToken();

                        if (token.getToken().equals(",")) {
                            continue;
                        } else if (token.getToken().equals(")")) {
                            break;
                        } else {
                            values.add(Utils.parseToken(token, block));
                        }
                    }

                    c.get().invoke(values);
                }

                else {
                    throw new InvalidCodeException("Could not find constructor for given parameters.");
                }

                variable.setValue(clazz);
            }
        }

        return variable;
    }
}
