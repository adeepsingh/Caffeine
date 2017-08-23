package me.amar0908.caffeine.languageMain;

public class InvalidCodeException extends Exception {

    public InvalidCodeException(String message) {
        super(message);
    }

    public InvalidCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}