package me.amar0908.caffeine.languageMain.block;

import me.amar0908.caffeine.languageMain.*;
import me.amar0908.caffeine.languageMain.Runtime;
import me.amar0908.caffeine.languageMain.expression.Expression;
import me.amar0908.caffeine.languageMain.tokenizer.Token;
import me.amar0908.caffeine.languageMain.tokenizer.Tokenizer;

import java.io.IOException;
import java.util.Optional;

public class VariableDeclaration extends ReadOnlyBlock implements Nameable {

    private Token typeToken, nameToken;
    private Tokenizer tokenizer;

    public VariableDeclaration(Block superBlock, Token typeToken, Token nameToken, Tokenizer tokenizer, Token... propertyTokens) {
        super(superBlock, propertyTokens);

        this.typeToken = typeToken;
        this.nameToken = nameToken;
        this.tokenizer = tokenizer;
    }

    @Override
    public void run() throws InvalidCodeException, IOException {
        Type type = Type.match(typeToken.getToken());

        if (type == null) {
            throw new InvalidCodeException("Expected type, got " + typeToken + ".");
        }

        if (type == PrimitiveType.VOID) {
            throw new InvalidCodeException("Attempted to instantiate variable with type void.");
        }

        Variable variable = new Variable(getSuperBlock(), nameToken.getToken(), type);
        Expression expression = Expression.parse(tokenizer, this, variable);

        if (expression.evaluate().getValue() != null) {
            variable.setValue(expression.evaluate().getValue());
        }

        for (Token token : getPropertyTokens()) {
            Property property = Runtime.RUNTIME.getPogoClass(token.getToken().substring(token.getToken().indexOf('@') + 1));

            if (property == null) {
                throw new InvalidCodeException("Expected property, found " + token.getToken().substring(token.getToken().indexOf('@') + 1) + ".");
            }

            addProperty(property);

            Optional<Method> method = property.getMethod("applyToVariable");

            if (!method.isPresent()) {
                throw new InvalidCodeException("Property is not applicable to variables.");
            }

            method.get().run();
        }

        getSuperBlock().addVariable(variable);
    }

    @Override
    public String getName() {
        return nameToken.getToken();
    }

    public String getType() {
        return typeToken.getToken();
    }

    @Override
    public String toString() {
        return getClass() + " typeToken=" + typeToken + " nameToken=" + nameToken;
    }
}
