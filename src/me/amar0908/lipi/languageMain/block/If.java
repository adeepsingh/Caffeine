package me.amar0908.lipi.languageMain.block;

import java.io.IOException;
import java.util.ArrayList;

import me.amar0908.lipi.ide.Console;
import me.amar0908.lipi.languageMain.Condition;
import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.Runtime;

public class If extends ConditionalBlock {

    private ArrayList<ElseIf> elseIfs;
    private Else elze;

    public If(Block superBlock, Condition... conditions) {
        super(superBlock, conditions);

        this.elseIfs = new ArrayList<>();
    }

    public ElseIf addElseIf(ElseIf elseIf) {
        elseIfs.add(elseIf);
        return elseIf;
    }

    public void setElse(Else elze) {
        this.elze = elze;
    }

    @Override
    public void run() throws InvalidCodeException, IOException {
        Runtime.RUNTIME.print("run() called on " + toString(), Console.MessageType.OUTPUT);
        Runtime.RUNTIME.print("areConditionsTrue() -> " + areConditionsTrue(), Console.MessageType.OUTPUT);

        if (areConditionsTrue()) {
            for (Block subBlock : getSubBlocks()) {
                subBlock.run();
            }
        }

        else {
            for (ElseIf elseIf : elseIfs) {
                if (elseIf.areConditionsTrue()) {
                    for (Block subBlock : elseIf.getSubBlocks()) {
                        subBlock.run();
                    }
                    return;
                }
            }

            if (elze != null) {
                for (Block subBlock : elze.getSubBlocks()) {
                    subBlock.run();
                }
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " elseIfsSize=" + elseIfs.size() + " hasElse=" + (elze != null);
    }
}