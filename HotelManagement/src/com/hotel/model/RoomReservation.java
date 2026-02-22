package com.hotel.model;

import java.time.LocalDate;

import com.hotel.model.reservations.Reservation;

public class RoomReservation extends Reservation {

    private String roomNumber;
    private int numberOfNights;
    private double pricePerNight;

    public RoomReservation(String reservationId, String guestId, LocalDate reservationDate, String roomNumber,int numberOfNights, double pricePerNight) {
        super(reservationId, guestId, reservationDate);
        this.roomNumber  = roomNumber;
        this.numberOfNights = numberOfNights;
        this.pricePerNight  = pricePerNight;
    }

    public String getRoomNumber()    { 
        return roomNumber; 
    }
    public int getNumberOfNights(){ 
        return numberOfNights;
    }

    @Override
    public String getReservationType() { 
        return "RoomReservation"; 
    }

    @Override
    public double getTotalCost() { 
        return pricePerNight * numberOfNights; 
    }
}


