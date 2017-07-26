package me.amar0908.caffeine.languageMain.block;

import me.amar0908.caffeine.languageMain.Condition;
import me.amar0908.caffeine.languageMain.Endable;
import me.amar0908.caffeine.languageMain.InvalidCodeException;

import java.io.IOException;
import java.util.Arrays;

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