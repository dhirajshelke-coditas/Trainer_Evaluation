package com.hotel.service;

import com.hotel.exception.RoomNotFoundException;
import com.hotel.model.DeluxeRoom;
import com.hotel.model.StandardRoom;
import com.hotel.model.SuiteRoom;


import java.util.ArrayList;
import java.util.List;

import com.hotel.model.rooms.Room;

public class RoomService {

    private final List<Room> rooms = new ArrayList<>();

    public RoomService() {
        // Pre-populate sample rooms
        rooms.add(new StandardRoom("101"));
        rooms.add(new StandardRoom("102"));
        rooms.add(new StandardRoom("103"));
        rooms.add(new DeluxeRoom("201"));
        rooms.add(new DeluxeRoom("202"));
        rooms.add(new SuiteRoom("301"));
        rooms.add(new SuiteRoom("302"));
    }

   
     //Finds a room by room number.
  
    public Room getRoom(String roomNumber) throws RoomNotFoundException {
        for (Room r : rooms) {
            if (r.getRoomNumber().equalsIgnoreCase(roomNumber)) {
                return r;
            }
        }
        throw new RoomNotFoundException(roomNumber);
    }



    public void bookRoom(String roomNumber) throws RoomNotFoundException {
        Room room = getRoom(roomNumber);
        if (!room.isAvailable()) {
            throw new RoomNotFoundException("Room " + roomNumber + " is already booked.");
        }
        room.setAvailable(false);
        System.out.println("Room " + roomNumber + " booked successfully.");
    }

     
    public void releaseRoom(String roomNumber) throws RoomNotFoundException {
        Room room = getRoom(roomNumber);
        room.setAvailable(true);
        System.out.println("Room " + roomNumber + " is now available.");
    }

   
     
    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

   
    
    public List<Room> getAvailableRooms() {
        List<Room> available = new ArrayList<>();
        for (Room r : rooms) {
            if (r.isAvailable()) available.add(r);
        }
        return available;
    }
}

