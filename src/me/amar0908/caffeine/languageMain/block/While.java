package me.amar0908.caffeine.languageMain.block;

import me.amar0908.caffeine.ide.Console;
import me.amar0908.caffeine.languageMain.Condition;
import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.Runtime;

import java.io.IOException;

public class While extends ConditionalBlock {

    public While(Block superBlock, Condition... conditions) {
        super(superBlock, conditions);
    }

    @Override
    public void run() throws InvalidCodeException, IOException {
        Runtime.RUNTIME.print("run() called on " + toString(), Console.MessageType.OUTPUT);

        while (areConditionsTrue()) {
            Runtime.RUNTIME.print("While loop about to go around once.", Console.MessageType.OUTPUT);

            for (Block subBlock : getSubBlocks()) {
                subBlock.run();
            }
        }

        Runtime.RUNTIME.print("While loop finished.", Console.MessageType.OUTPUT);
    }
}