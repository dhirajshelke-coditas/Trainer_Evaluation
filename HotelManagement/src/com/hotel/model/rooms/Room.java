package com.hotel.model.rooms;

/**
 * HMS-103: Base class for all room types.
 */
public abstract class Room {

    private String roomNumber;
    private double pricePerNight;
    private boolean available;

    public Room(String roomNumber, double pricePerNight) {
        this.roomNumber    = roomNumber;
        this.pricePerNight = pricePerNight;
        this.available     = true;
    }

    public String  getRoomNumber()   { return roomNumber; }
    public double  getPricePerNight(){ return pricePerNight; }
    public boolean isAvailable()     { return available; }
    public void    setAvailable(boolean available) { this.available = available; }

    public abstract String getRoomType();

    @Override
    public String toString() {
        return String.format("%s[Number=%s, Price=%.2f, Available=%s]",
                getRoomType(), roomNumber, pricePerNight, available ? "Yes" : "No");
    }
}

