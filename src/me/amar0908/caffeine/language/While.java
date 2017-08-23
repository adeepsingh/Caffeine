package me.amar0908.caffeine.language;

import me.amar0908.caffeine.language.Block;
import me.amar0908.caffeine.language.ConditionalBlock;
import me.amar0908.caffeine.language.Utils;
import me.amar0908.caffeine.language.ConditionalBlock.CompareOperation;

public class While extends ConditionalBlock {

    public While(Block superBlock, String aVal, String bVal, CompareOperation compareOp) {
        super(superBlock, aVal, bVal, compareOp);
    }

    @Override
    public void run() throws Utils.InvalidCodeException {
        if (compareOp == ConditionalBlock.CompareOperation.EQUALS) {
            double a, b;

            do {
                try {
                    a = Double.valueOf(Utils.implode(aVal, this));
                    b = Double.valueOf(Utils.implode(bVal, this));
                } catch (Exception e) {
                    throw new Utils.InvalidCodeException("Attempted to use " + compareOp.name().toLowerCase() + " on non-numbers.");
                }

                super.run();
            } while (a == b);
        } else if (compareOp == CompareOperation.NOTEQUALS) {
            double a, b;

            do {
                try {
                    a = Double.valueOf(Utils.implode(aVal, this));
                    b = Double.valueOf(Utils.implode(bVal, this));
                } catch (Exception e) {
                    throw new Utils.InvalidCodeException("Attempted to use " + compareOp.name().toLowerCase() + " on non-numbers.");
                }

                super.run();
            } while (a != b);
        } else if (compareOp == CompareOperation.GREATERTHAN) {
            double a, b;

            do {
                try {
                    a = Double.valueOf(Utils.implode(aVal, this));
                    b = Double.valueOf(Utils.implode(bVal, this));
                } catch (Exception e) {
                    throw new Utils.InvalidCodeException("Attempted to use " + compareOp.name().toLowerCase() + " on non-numbers.");
                }

                super.run();
            } while (a > b);
        } else if (compareOp == CompareOperation.LESSTHAN) {
            double a, b;

            do {
                try {
                    a = Double.valueOf(Utils.implode(aVal, this));
                    b = Double.valueOf(Utils.implode(bVal, this));
                } catch (Exception e) {
                    throw new Utils.InvalidCodeException("Attempted to use " + compareOp.name().toLowerCase() + " on non-numbers.");
                }

                super.run();
            } while (a < b);
        }
    }

    @Override
    public String toString() {
        return "While aVal=" + aVal + " bVal=" + bVal + " compareOp=" + compareOp.name();
    }
}
