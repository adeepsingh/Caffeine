package me.amar0908.caffeine.languageMain;

import me.amar0908.caffeine.languageMain.PrimitiveType;
import me.amar0908.caffeine.languageMain.Type;

public enum PrimitiveType implements Type {

    BOOLEAN, DOUBLE, INTEGER, OBJECT, STRING, VOID;

    @Override
    public boolean equalsType(Type other) {
        return other instanceof PrimitiveType && (other == this || other == OBJECT || this == OBJECT);
    }
}