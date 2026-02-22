package com.hotel.exception;

public class GuestNotFoundException extends Exception {

    public GuestNotFoundException(String guestId) {
        super("Guest not found with ID: " + guestId);
    }
}
