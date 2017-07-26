package me.amar0908.caffeine.language.system_methods;

import me.amar0908.caffeine.language.Block;
import me.amar0908.caffeine.language.Method.Visibility;
import me.amar0908.caffeine.language.Utils;
import me.amar0908.caffeine.language.Variable;

abstract class SystemMethod implements Utils.Invokable {

    private final String name;

    SystemMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public abstract void invoke(Block b, String[] params, Variable receiver) throws Utils.InvalidCodeException;

    @Override
    public Visibility getVisibility() {
        return Visibility.PUBLIC;
    }

    @Override
    public String toString() {
        return "SystemMethod name=" + getName();
    }
}
