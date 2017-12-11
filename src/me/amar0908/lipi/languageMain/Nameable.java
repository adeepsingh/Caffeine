package me.amar0908.lipi.languageMain;

public interface Nameable {

    public String getName();

    /*
    If a class implements Nameable, it needs to have a toString() that includes "name=" + name.
     */
    public String toString();
}