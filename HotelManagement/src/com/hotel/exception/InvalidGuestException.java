package com.hotel.exception;

/**
 * HMS-104: Thrown when guest data is invalid or a duplicate guest is detected.
 */
public class InvalidGuestException extends Exception {

    public InvalidGuestException(String message) {
        super(message);
    }
}
