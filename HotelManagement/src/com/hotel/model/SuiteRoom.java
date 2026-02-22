package com.hotel.model;

import com.hotel.model.rooms.Room;

public class SuiteRoom extends Room {

    public SuiteRoom(String roomNumber) {
        super(roomNumber, 5500.00);
    }

    @Override
    public String getRoomType() { 
        return "SuiteRoom"; 
    }
}

