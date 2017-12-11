package me.amar0908.lipi.languageMain.block;

import java.io.IOException;
import java.util.Arrays;

import me.amar0908.lipi.languageMain.Condition;
import me.amar0908.lipi.languageMain.Endable;
import me.amar0908.lipi.languageMain.InvalidCodeException;

public abstract class ConditionalBlock extends Block implements Endable {

    private Condition[] conditions;

    public ConditionalBlock(Block superBlock, Condition... conditions) {
        super(superBlock);

        this.conditions = conditions;
    }

    public boolean areConditionsTrue() throws InvalidCodeException, IOException {
        for (Condition condition : conditions) {
            if (!condition.isConditionTrue()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return getClass() + " conditions=" + Arrays.toString(conditions);
    }
}