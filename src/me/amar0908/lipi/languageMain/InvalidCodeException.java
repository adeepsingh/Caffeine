package me.amar0908.lipi.languageMain;

public class InvalidCodeException extends Exception {

    public InvalidCodeException(String message) {
        super(message);
    }

    public InvalidCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}