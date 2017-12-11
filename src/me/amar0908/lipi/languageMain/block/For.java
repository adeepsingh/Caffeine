package me.amar0908.lipi.languageMain.block;

import java.io.IOException;

import me.amar0908.lipi.languageMain.Endable;
import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.PrimitiveType;
import me.amar0908.lipi.languageMain.Value;
import me.amar0908.lipi.languageMain.expression.Expression;

public class For extends Block implements Endable {

    private Expression lower, upper;

    public For(Block superBlock, Expression lower, Expression upper) {
        super(superBlock);

        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public void run() throws InvalidCodeException, IOException {
        Value l = lower.evaluate(), u = upper.evaluate();

        double lVal = 0, uVal = 0;

        if (l.getType() == PrimitiveType.DOUBLE) {
            lVal = (double) l.getValue();
        }

        else if (l.getType() == PrimitiveType.INTEGER) {
            lVal = (int) l.getValue();
        }

        if (u.getType() == PrimitiveType.DOUBLE) {
            uVal = (double) u.getValue();
        }

        else if (u.getType() == PrimitiveType.INTEGER) {
            uVal = (int) u.getValue();
        }

        int direction = uVal > lVal ? 1 : -1;

        for (double i = lVal; i < uVal; i += direction) {
            if (getSuperBlock().hasVariable(lower.getLine())) {
                getSuperBlock().getVariable(lower.getLine()).get().setValue(i);
            }

            for (Block subBlock : getSubBlocks()) {
                subBlock.run();
            }
        }
    }

    @Override
    public String toString() {
        return getClass() + " lower=" + lower + " upper=" + upper;
    }
}