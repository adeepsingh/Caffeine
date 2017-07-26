package me.amar0908.caffeine.languageMain.block;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.expression.Expression;

import java.io.IOException;

/**
 * Represents a return value from a method.
 * At runtime, when one of these is encountered, the method stops whereever it is and returns the value.
 */
public class Return extends ReadOnlyBlock {

    private Expression expression;

    public Return(Block superBlock, Expression expression) {
        super(superBlock);

        this.expression = expression;
    }

    @Override
    public void run() throws InvalidCodeException, IOException {
        Method method = (Method) getBlockTree()[1]; // [0] is the class, [1] is the method.
        method.setReturnValue(expression.evaluate());
    }

    @Override
    public String toString() {
        return getClass() + " expression=" + expression;
    }
}