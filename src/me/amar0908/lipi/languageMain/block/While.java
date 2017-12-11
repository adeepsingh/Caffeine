package me.amar0908.lipi.languageMain.block;

import java.io.IOException;

import me.amar0908.lipi.ide.Console;
import me.amar0908.lipi.languageMain.Condition;
import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.Runtime;

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