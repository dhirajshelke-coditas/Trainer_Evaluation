package com.hotel.model;

import com.hotel.model.rooms.Room;

public class StandardRoom extends Room {

    public StandardRoom(String roomNumber) {
        super(roomNumber, 1500.00);
    }

    @Override
    public String getRoomType() { 
        return "StandardRoom"; 
    }
}

