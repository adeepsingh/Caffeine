package me.amar0908.lipi.languageMain.block;

import me.amar0908.lipi.languageMain.block.RootBlock;

public class Property extends RootBlock {

    private String name;

    public Property(String name) {
        super(null);

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        // We don't want to do anything here. Methods will be automatically called.
    }

    @Override
    public String toString() {
        return getClass() + " name=" + name;
    }
}
