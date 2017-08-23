package me.amar0908.caffeine.languageMain.block;

import me.amar0908.caffeine.ide.Console;
import me.amar0908.caffeine.languageMain.*;
import me.amar0908.caffeine.languageMain.Runtime;
import me.amar0908.caffeine.languageMain.tokenizer.Token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Constructor extends Block {

    private Parameter[] parameters;

    public Constructor(Block superBlock, Parameter[] parameters, Token... propertyTokens) {
        super(superBlock, propertyTokens);

        this.parameters = parameters;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    @Override
    public void run() throws InvalidCodeException, IOException {
        invoke(new ArrayList<>());
    }

    public void invoke(List<Value> values) throws InvalidCodeException, IOException {
        me.amar0908.caffeine.languageMain.Runtime.RUNTIME.print("invoke() called on constructor.", Console.MessageType.OUTPUT);
        Runtime.RUNTIME.print("Constructor superblock is " + getSuperBlock(), Console.MessageType.OUTPUT);

        if (values.size() != parameters.length) {
            throw new InvalidCodeException("Invalid number of parameters specified.");
        }

        for (int i = 0; i < parameters.length && i < values.size(); i++) {
            Parameter p = parameters[i];
            Value v = values.get(i);

            if (!v.getType().equals(p.getMatchedType())) {
                throw new InvalidCodeException("Type mismatch for parameter " + p.getName() + ". Type is " + v.getType() + ". Should be " + p.getUnmatchedType() + ".");
            }

            addVariable(new Variable(this, p.getName(), p.getMatchedType(), v.getValue()));
        }

        for (Token token : getPropertyTokens()) {
            Property property = Runtime.RUNTIME.getPogoClass(token.getToken().substring(token.getToken().indexOf('@') + 1));

            if (property == null) {
                throw new InvalidCodeException("Expected property, found " + token.getToken().substring(token.getToken().indexOf('@') + 1) + ".");
            }

            addProperty(property);

            Optional<Method> method = property.getMethod("applyToConstructor");

            if (!method.isPresent()) {
                throw new InvalidCodeException("Property is not applicable to constructors.");
            }

            method.get().run();
        }

        for (Block block : getSubBlocks()) {
            block.run();
        }
    }

    @Override
    public String toString() {
        return getClass() + " parameters=" + Arrays.toString(parameters);
    }
}