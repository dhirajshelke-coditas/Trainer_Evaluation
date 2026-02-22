package com.hotel.exception;

public class RoomNotFoundException extends Exception {

    public RoomNotFoundException(String roomNumber) {
        super("Room not found: " + roomNumber);
    }
}