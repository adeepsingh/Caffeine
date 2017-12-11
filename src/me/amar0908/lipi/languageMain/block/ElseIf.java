package me.amar0908.lipi.languageMain.block;

import me.amar0908.lipi.languageMain.Condition;
import me.amar0908.lipi.languageMain.block.Block;
import me.amar0908.lipi.languageMain.block.ConditionalBlock;

public class ElseIf extends ConditionalBlock {

    public ElseIf(Block superBlock, Condition... conditions) {
        super(superBlock, conditions);
    }

    @Override
    public void run() {
        // We don't want to do anything because the If will run this.
    }
}
