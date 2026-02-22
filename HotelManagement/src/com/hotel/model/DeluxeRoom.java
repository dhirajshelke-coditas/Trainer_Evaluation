package com.hotel.model;

import com.hotel.model.rooms.Room;

public class DeluxeRoom extends Room {

    public DeluxeRoom(String roomNumber) {
        super(roomNumber, 3000.00);
    }

    @Override
    public String getRoomType() { return "DeluxeRoom"; }
}