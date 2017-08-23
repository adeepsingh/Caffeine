package me.amar0908.caffeine.languageMain.block;

import me.amar0908.caffeine.languageMain.Condition;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.block.ConditionalBlock;

public class ElseIf extends ConditionalBlock {

    public ElseIf(Block superBlock, Condition... conditions) {
        super(superBlock, conditions);
    }

    @Override
    public void run() {
        // We don't want to do anything because the If will run this.
    }
}
